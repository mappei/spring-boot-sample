###Spring Boot


### Problems
1. 将SpringbootApplication直接放在``java``目录下会出现以下问题
![1557891727581](/home/libra/.config/Typora/typora-user-images/1557891727581.png)

    - 解决方法1：
将类放在``java``目录下的包中

    - 解决方法2：
按照官方提示使用``@ComponentScan``和``@EnableAutoConfiguration``
![1557891599981](/home/libra/.config/Typora/typora-user-images/1557891599981.png)