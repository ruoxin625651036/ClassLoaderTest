/**
 * Baifubao.com,Inc.
 * Copyright (c) 2017-2018 All Rights Reserved.
 */
package test;

import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

import A.A.A.A;
import B.B;
/**
 *   1. Class: 若已/开头，表示从根路径开始寻找；若没有/，则表示从当前路径开始寻找
 *      ClassLoader:不能以/开头，默认从classpath的根路径开始寻找
 *
 *   2. 如何指定classpath？ 否则的话，idea会一直将整个module所在的路径设置为classpath，由于双亲委派模型，最终加载类的都是系统类加载器,
 *  需要指定classpath路径到test，而不包含A和B这两个目录。（最后采取的方法为将test文件打包为jar文件，然后通过classpath来指定），最终可保证
 *  A和B目录下的文件由自定义的classLoader来加载。
 *
 *   3. 在加载的过程中发现JAVA安全模型阻止了A和B的加载，需要开启。（JAVA安全模型）
 *
 *
 * @author duanyuejiao
 */
public class Main {
    public static void main(String[] args) {
        // class.getResource(), 得到的是当前类class文件的URI目录
        System.out.println(Main.class.getResource(""));
        // 得到的是当前的classpath的绝对URI路径
        System.out.println(Main.class.getResource("/"));
        // 得到的是当前的classpath的绝对URI路径
        System.out.println(ClassLoader.getSystemResource(""));
        try {
            URL[] urls = new URL[1];
            urls[0] = new URL("file:/Users/duanyuejiao/Documents/duanyuejiao/code/study_source/ClassLoaderTest"
                    + "/target/classes/");

            URLClassLoader cl = new MyClassLoader(urls);
            System.out.println(cl.getResource("A/A/A/A.class"));
            Class<?> clazzA = cl.loadClass("A.A.A.A");
            A a = (A)clazzA.newInstance();
            System.out.println(a.getClass().getClassLoader());

//            Class<?> clazzA2 = cl.loadClass("A");
//            A a2 = (A)clazzA2.newInstance();
//            System.out.println(a2.getClass().getClassLoader());

            Class<?> clazzB = cl.loadClass("B.B");
            B b = (B)clazzB.newInstance();
            System.out.println(b.getClass().getClassLoader());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }

    }
}
