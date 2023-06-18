package com.silver.a_single_instance.example_1;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Logger0 {
    private final FileWriter fileWriter;

    public Logger0() throws IOException {
        File file = new File("/Users/goku/log.txt");
        fileWriter = new FileWriter(file, true);
    }

    public void log(String msg) throws IOException {
        /**
         * 写法一：
         * 在 UserController 和 OrderController 中，我们分别创建两个 Logger 对象。
         * 在 Web 容器的 Servlet 多线程环境下，如果两个 Servlet 线程同时分别执行 login() 和 create() 两个函数，
         * 并且同时写日志到 log.txt 文件中，那就有可能存在日志信息互相覆盖的情况。
         */
        fileWriter.write(msg);

        /**
         * 写法二：
         * 那如何来解决这个问题呢？我们最先想到的就是通过加锁的方式：给 log() 函数加互斥锁（Java 中可以通过 synchronized 的关键字），
         * 同一时刻只允许一个线程调用执行 log() 函数。
         * 具体的代码实现如下所示：
         *
         * 但是仍然解决不了问题。
         * 这是因为，这种锁是一个对象级别的锁，一个对象在不同的线程下同时调用 log() 函数，会被强制要求顺序执行。
         * 但是，不同的对象之间并不共享同一把锁。在不同的线程下，通过不同的对象调用执行 log() 函数，锁并不会起作用，仍然有可能存在写入日志互相覆盖的问题。
         */
        synchronized (this) {
            fileWriter.write(msg);
        }

        /**
         * 写法三：
         * 要想解决这个问题也不难，我们只需要把对象级别的锁，换成类级别的锁就可以了。
         * 让所有的对象都共享同一把锁。这样就避免了不同对象之间同时调用 log() 函数，而导致的日志覆盖问题。
         */
        synchronized (Logger0.class) {
            fileWriter.write(msg);
        }

    }
}


// Logger类的应用示例：
class UserController {
    private final Logger0 logger = new Logger0();

    public UserController() throws IOException {
    }

    public void login(String username, String password) throws IOException {
        // ...省略业务逻辑代码...
        logger.log(username + " logined!");
    }
}

class OrderController {
    private final Logger0 logger = new Logger0();

    public OrderController() throws IOException {
    }

    public void create(String order) throws IOException {
        // ...省略业务逻辑代码...
        logger.log("Created an order: " + order);
    }
}