package com.silver.a_single_instance.single;


import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Logger {
    private final FileWriter writer;
    private static final Logger instance = new Logger();

    private Logger() {
        File file = new File("/Users/wangzheng/log.txt");
        try {
            writer = new FileWriter(file, true); //true表示追加写入
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 相对于这两种解决方案，单例模式的解决思路就简单一些了。单例模式相对于之前类级别锁的好处是，不用创建那么多 Logger 对象，
     * 一方面节省内存空间，另一方面节省系统文件句柄（对于操作系统来说，文件句柄也是一种资源，不能随便浪费）。
     * <p>
     * 我们将 Logger 设计成一个单例类，程序中只允许创建一个 Logger 对象，所有的线程共享使用的这一个 Logger 对象，
     * 共享一个 FileWriter 对象，而 FileWriter 本身是对象级别线程安全的，也就避免了多线程情况下写日志会互相覆盖的问题。
     */
    public static Logger getInstance() {
        return instance;
    }

    public void log(String message) throws IOException {
        writer.write(message);
    }
}

// Logger类的应用示例：
class UserController {
    public void login(String username, String password) throws IOException {
        // ...省略业务逻辑代码...
        Logger.getInstance().log(username + " logined!");
    }
}

class OrderController {
    public void create(String order) throws IOException {
        // ...省略业务逻辑代码...
        Logger.getInstance().log("Created a order: " + order);
    }
}


