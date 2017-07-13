# TemplateDemo
Spring Boot学习


遇到的问题：<br/> 
>1.项目刚建立，运行报错：Failed to start end point associated with ProtocolHandler ["http-nio-8080"]  
>>解决：运行是端口被占用，修改端口号即可；在application.properties 文件中添加 server.port=8086    