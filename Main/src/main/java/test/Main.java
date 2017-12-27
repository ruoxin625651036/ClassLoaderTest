/**
 *
 *  All Rights Reserved.
 */
package test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

/**
 *   1. Class: 若已/开头，表示从根路径开始寻找；若没有/，则表示从当前路径开始寻找
 *      ClassLoader:不能以/开头，默认从classpath的根路径开始寻找
 *
 *   2. 如何指定classpath？ 否则的话，idea会一直将整个module所在的路径设置为classpath，由于双亲委派模型，最终加载类的都是系统类加载器,
 *  需要指定classpath路径到test，而不包含A和B这两个目录。（最后采取的方法为将test文件打包为jar文件，然后通过classpath来指定），最终可保证
 *  A和B目录下的文件由自定义的classLoader来加载。
 *
 *   3. 可以通过-Djava.class.path来设置classpath路径 （将要加载的对象放置在classpath外面）
 *
 *   4. 如果所要实例化的类不在classpath下，那么怎样对它进行实例化呢？ （tomcat中是怎样对Servlet进行加载和处理的？）
 *
 *
 * @author dyj
 */
public class Main {
    public static void main(String[] args) {

        // 查看classpath的值
        System.out.println(System.getProperty("java.class.path"));

        // class.getResource(), 得到的是当前类class文件的URI目录
        System.out.println(Main.class.getResource(""));
        // 得到的是当前的classpath的绝对URI路径
        System.out.println(Main.class.getResource("/"));
        // 得到的是当前的classpath的绝对URI路径
        System.out.println(ClassLoader.getSystemResource(""));

        try {
            URL[] urls = new URL[1];
            // 最后面添加/和不添加是有区别的！！！
            // 如果添加/,
            // URL路径只能是在package所在的路径的外层？ 如果定位到了package里面的某一层目录，后面在使用loadClass会找不到对应的class文件，yinw
            // class文件的名称由package.类名组成
            urls[0] = new URL("file:/Users/dyj/Documents/dyj/code/study_source/ClassLoaderTest/childModuleA/target/childModuleA-1.0-SNAPSHOT.jar");

            URLClassLoader cl = new MyClassLoader(urls);
            System.out.println(cl.getResource("A/A.class"));
            Class<?> clazzA = cl.loadClass("A.A");
            System.out.println(clazzA.getClassLoader());
            Object a = clazzA.newInstance();

            //   不能使用A进行强制转化的愿意在于，A不在main所在的classpath下，所有是无法进行实例话的，这个时候需要使用反射机制进行处理
            //  tomcat 中的Bootstrap类也是这样处理的，它在运行时，将Bootstrap.jar设置为classpath路径，然后通过反射机制来调用Catalina运行整个服务器。
            //   A a = (A)clazzA.newInstance();
            //   System.out.println(a.getClass().getClassLoader());
            Method method = clazzA.getMethod("get", null);
            System.out.println(method.invoke(a, null));


            Class<?> clazzB = cl.loadClass("B.B");
            System.out.println(clazzB.getClassLoader().getParent());
//            B b = (B)clazzB.newInstance();
//            System.out.println(b.getClass().getClassLoader());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

    }
}
