# YourAnswer
Maven配置下的，采用Spring+SpringMVC+Mybaits框架的问答论坛Demo


#
# 框架和工具

Java&IDE: JDK8 IDEA
框架: SpringMVC+Spring+MyBatis
数据库: Mysql
web服务器: Tomcat 8.5
构建工具: Maven
其他的库: Druid(数据库连接池)、JUnit、Log4j、计划（Jackson FastJson）、PasswordEncoderjamie加密包
前端：bootstrap、jQuery

#
# 系统介绍

1、目前功能包含了用户管理模块，包括登陆、注册、更新用户信息、修改密码等；问题与回答模块；个人随笔模块。
   实现了登录用户发表问题，借用了wangEditor富文本编辑器实现问题格式的编辑；实现了登录用户可对问题进行回答；实现登录用户可以发表随笔文章。

2、系统采用Maven进行管理，数据库使用mysql，采用SpringMVC+Spring+Mybaits框架搭建。
   之前，系统是由SpringMVC的ModelAndView来实现将后端数据显示到前端JSP页面；目前正在逐步打算在后端提供数据接口，前端通过数据接口获取数据并显示，不过由于前端知识并不够了解，当前还在尝试使用Ajax实现前端读取并显示数据。

3、功能上计划新增话题标签用来规划分类问题、新增热点排行功能。

4、技术上计划，优化SQL优化、数据库优化、并发改进；分布式、缓存、消息队列

4、系统是在学习SSM过程中的一时兴起所做，欢迎指正。
