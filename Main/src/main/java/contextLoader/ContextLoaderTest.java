/**
 *
 *
 */
package contextLoader;

/**
 * 线程上下文类加载器测试
 *
 * @author dyj
 */
public class ContextLoaderTest {

    public static void main(String[] args) {
        // 当前类加载器
        System.out.println(ContextLoaderTest.class.getClassLoader());
        // 线程上下文类加载器
        System.out.println(Thread.currentThread().getContextClassLoader());

        // 类似与SPI问题解决方案，比如JDBC API接口是由启动累加载器来加载的；比如DriverManager在rt.jar中，
        // com.mysql.jdbc.Driver这个类加载则是由系统类加载器来加载的，但是在DriverManager中会用到由系统类加载器加载的Driver
        // 如果使用的是线程上下文类加载器，则会将其转换为系统类加载器，
        // 这个时候会出现什么问题？
        // 构造案例：
    }

}
