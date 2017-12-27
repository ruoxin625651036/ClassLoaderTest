/**
 *
 *
 */
package test;

import java.net.URL;
import java.net.URLClassLoader;

/**
 * a little description
 *
 * @author dyj
 */
public class MyClassLoader extends URLClassLoader {

    public MyClassLoader(URL[] urls) {
        super(urls);
    }
}
