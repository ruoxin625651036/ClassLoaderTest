# 自定义ClassLoader可以加载A目录下的类，但不能加载B目录下的类

1. Class和ClassLoader使用getResource()时的区别：  
 
    Class可以以/开头，表示从根路径下开始寻找；若没有"/",则表示从当前路径下开始寻找。  
    
    ClassLoader不能已"/"开头，始终是从根路径下开始寻找
        
2. JVM默认是双亲委派模型，所以classpath需要进行单独设置，否则会影响结果。  
        
        
    

 