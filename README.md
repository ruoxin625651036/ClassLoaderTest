# 自定义ClassLoader可以加载A目录下的类，但不能加载B目录下的类

1. Class和ClassLoader使用getResource()时的区别：  
 
    Class可以以/开头，表示从根路径下开始寻找；若没有"/",则表示从当前路径下开始寻找。  
    
    ClassLoader不能已"/"开头，始终是从根路径下开始寻找
        
2. JVM默认是双亲委派模型，所以classpath需要进行单独设置，否则会影响结果。  
        
        通过-Djava.class.path设置classpath路径
        
3. 运行，修改Main方法中target的绝对路径；然后运行即可。(特别注意，我将A.class和B.class的位置进行调整了，
  同时将target中的A和B目录删了，主要和classpath有关)       
    

 