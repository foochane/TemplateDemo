# TemplateDemo
Spring Boot学习

项目的启动方式：<br/>
>启动方式1： 
>>通过IDEA的启动按钮启动 

>启动方法2： 
>>切换到工程目录，通过命令行启动：mvn spring-boot:run 

>启动方式3： 
>>1.切换到工程目录，先编译程序：mvn install  
>>2.进入到target目录,找到编译好的 .jar文件，执行命令：java -jar xxxx.jar 


遇到的问题：<br/> 
>1.项目刚建立，运行报错：Failed to start end point associated with ProtocolHandler ["http-nio-8080"]  
>>解决：运行是端口被占用，修改端口号即可；在application.properties 文件中添加 server.port=8086    