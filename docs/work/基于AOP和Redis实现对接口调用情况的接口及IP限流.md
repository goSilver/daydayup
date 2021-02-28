

[toc]

### 需求描述

1. 项目中有许多接口，现在我们需要实现一个功能对接口调用情况进行统计，主要功能如下：
   * 需求一：实现对每个接口，每天的调用次数做记录；
   * 需求二：如果某次调用抛出了异常信息，则记录下异常信息；
   * 需求三：限流，限制单个IP一天内对一个接口的调用次数。

### 概要设计

1. 因为需要对每个接口的调用情况进行统计，所以选择AOP来实现，将Controller层抽象为一个切面

   * @Before 执行业务操作前进行限流判断；

   * @AfterReturn 如果正常返回则调用次数加1；
   * @AfterThrowing 如果抛出异常则记录异常信息。

   如果将这些信息写入数据库的话会对每个接口带来额外的操作数据库的开销，影响接口响应时间，且此类记录信息较多，所以此处选择Redis将这些信息缓存下来。

   

2. Redis设计

   * 对于需求一，我们需要记录三个信息：1、调用的接口名；2、调用的日期（精确到天）；3、调用次数。所以此处Redis的key使用Hash结构，数据结构如下：key = 接口URI、key = 调用日期（到天）、value = 调用次数（初始值为1，没一次调用后自增1）。
   * 对于需求二，需要记录的信息有：1、调用的接口名；2、异常发生时间（精确到毫秒）；3、异常信息。因为需求一的key已经设置成了接口URI，所以此处选择使用URI + 后缀“_exception”的形式来代表异常信息的key。所以此需求Redis的数据结构设计如下（仍然使用Hash结构）：key = URI + “\_exception”、key = 异常发生时间（精确到毫秒）、value = 异常信息。
   * 对于需求三，我们需要记录的信息有：1、调用的接口名；2、ip地址；3、调用时间；4、调用次数。此需求需要记录的信息较多，但是我们可以将信息1、信息2、信息3组合起来拼接成一个唯一的key即可，将调用时间的维度精确到天且设置key的过期时间为一天，这样的一个key即可代表单个IP一天时间内访问了哪些接口。所以Redis的数据结构设计如下：key = URI + ip +date（精确到天）、value = 调用次数。

###  代码实现

   ```java
   /**
    * 接口调用情况监控
    * 1、监控单个接口一天内的调用次数
    * 2、如果抛出异常，则记录异常信息及发生时间
    * 3、对单个IP进行限流，每天对每个接口的调用次数有限
    *
    * @author csh
    * @date 2019/10/30
    */
   @Aspect
   @Component
   public class ApiCallAdvice {
   
       @Resource
       private RedisTemplate redisTemplate;
       @Resource
       private StringRedisTemplate stringRedisTemplate;
   
       private static final String FORMAT_PATTERN_DAY = "yyyy-MM-dd";
       private static final String FORMAT_PATTERN_MILLS = "yyyy-MM-dd HH:mm:ss:SSS";
   
       /**
        * 真正执行业务操作前先进行限流的验证
        * 限制维度为：一天内单个IP的访问次数
        * key = URI + IP + date（精确到天）
        * value = 调用次数
        */
       @Before("execution(* com.pagoda.erp.platform.controller.*.*(..))")
       public void before() {
           // 接收到请求，记录请求内容
           ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
           //获取请求的request
           HttpServletRequest request = attributes.getRequest();
   
           String uri = request.getRequestURI();
           String date = dateFormat(FORMAT_PATTERN_DAY);
           String ip = getRequestIp(request);
   
           if (StringUtils.isEmpty(ip)) {
               throw new BusinessException("IP不能为空。");
           }
           // URI+IP+日期 构成以天为维度的key
           String ipKey = uri + "_" + ip + "_" + date;
           if (redisTemplate.hasKey(ipKey)) {
               if (Integer.parseInt(redisTemplate.opsForValue().get(ipKey).toString()) > 10000) {
                   throw new BusinessException("访问失败，已超过访问次数。");
               }
               redisTemplate.opsForValue().increment(ipKey, 1);
           } else {
               stringRedisTemplate.opsForValue().set(ipKey, "1", 1L, TimeUnit.DAYS);
           }
       }
   
       /**
        * 如果有返回结果，代表一次调用，则对应接口的调用次数加一，统计维度为天
        * （Redis使用Hash结构）
        * key = URI
        * key = date （精确到天）
        * value = 调用次数
        */
       @AfterReturning("execution(* com.pagoda.erp.platform.controller.*.*(..))")
       public void afterReturning() {
           // 接收到请求，记录请求内容
           ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
           //获取请求的request
           HttpServletRequest request = attributes.getRequest();
   
           String uri = request.getRequestURI();
           String date = dateFormat(FORMAT_PATTERN_DAY);
           if (redisTemplate.hasKey(uri)) {
               redisTemplate.boundHashOps(uri).increment(date, 1);
           } else {
               redisTemplate.boundHashOps(uri).put(date, 1);
           }
       }
   
       /**
        * 如果调用抛出异常，则缓存异常信息（Redis使用Hash结构）
        * key = URI + “_exception”
        * key = time (精确到毫秒的时间)
        * value = exception 异常信息
        *
        * @param ex 异常信息
        */
       @AfterThrowing(value = "execution(* com.pagoda.erp.platform.controller.*.*(..))", throwing = "ex")
       public void afterThrowing(Exception ex) {
           ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
           HttpServletRequest request = attributes.getRequest();
   
           String uri = request.getRequestURI() + "_exception";
           String time = dateFormat(FORMAT_PATTERN_MILLS);
           String exception = ex.getMessage();
   
           redisTemplate.boundHashOps(uri).put(time, exception);
       }
   
       private String getRequestIp(HttpServletRequest request) {
           // 获取请求IP
           String ip = request.getHeader("x-forwarded-for");
           if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip) || "null".equals(ip)) {
               ip = "" + request.getHeader("Proxy-Client-IP");
           }
           if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip) || "null".equals(ip)) {
               ip = "" + request.getHeader("WL-Proxy-Client-IP");
           }
           if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip) || "null".equals(ip)) {
               ip = "" + request.getRemoteAddr();
           }
           return ip;
       }
   
       private String dateFormat(String pattern) {
           SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
           return dateFormat.format(new Date());
       }
   }
   ```

   

### 参考资料

   * [使用aop+redis+注解 实现 限制单位时间内访问接口的次数]( https://blog.csdn.net/yali_aini/article/details/92653982 )