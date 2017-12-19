/**
 * Baifubao.com,Inc.
 * Copyright (c) 2017-2018 All Rights Reserved.
 */
package test;

import java.net.URL;
import java.net.URLClassLoader;

/**
 * a little description
 *
 * @author duanyuejiao
 */
public class MyClassLoader extends URLClassLoader {

    public MyClassLoader(URL[] urls) {
        super(urls);
    }
}
