# 自定义ClassLoader可以加载A目录下的类，但不能加载B目录下的类

1. Class和ClassLoader使用getResource()时的区别：  
 
    Class可以以/开头，表示从根路径下开始寻找；若没有"/",则表示从当前路径下开始寻找。  
    
    ClassLoader不能已"/"开头，始终是从根路径下开始寻找
        
2. JVM默认是双亲委派模型，所以classpath需要进行单独设置，否则会影响结果。  
        
        通过-Djava.class.path设置classpath路径
        
3. 由于classpath的问题，后面采用多Module的方式，将不同的内容设置为不同的jar包。 

   运行:  
      
      * 在根目录下运行：mvn package  
      * 替换Main Module中的Main方法的URL路径（**47行**）为childModuleA所生成的jar包路径
      * 运行Main Module中的Main方法，即可发现类A可加载，类B不能加载 
         
    

 