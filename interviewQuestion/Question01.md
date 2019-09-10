# 面试题

## 1. 请简单讲一下什么是框架？
框架可以理解为半成品，它封装的是复杂的底层，提供更简洁的API,用来提高开发效率，方便团队开发，实现项目解耦！

## 2. 什么是耦合度？
是一种软件度量，是指一程序中，模块及模块之间信息或参数依赖的程度。

## 3. SSM框架是指什么框架？
- spring
- springMVC
- MyBatis

### 3.Spring框架的作用？
- IOC(DI): 控制反转(依赖注入)
- AOP（面向切面编程）
- 集成其他框架的使用
- 实现解耦，易于团队开发，提高开发效率

## 4.如何在一个项目使用Spring框架？
- 导包
- 编写配置文件

## 5.你使用过的后端框架有哪些？
spring、springmvc、mybaits、druid、shiro、springboot

## 6.SpringMVC五大组件？
- DispatcherServlet:    请求入口
- HandlerMapping:   请求派发,负责请求和控制器建立一一对应的关系
- Controller:   处理器
- ModelAndView：    封装模型信息和视图信息
- ViewResolver：    视图处理器,定位页面

## 7. 简述SpringMVC的工作原理？
- TUDO()



## 8. 什么是MVC？作用是什么？
M：model  业务处理
V：view   页面视图jsp html
C: controller  控制器

## 9. 访问数据库的技术有哪些？
- jdbc  
- mybatis   	没有完全实现JPA的思想（无论多简单的sql,mybatis都不能省略）
- hibernate       可实现表字段和属性的全自动映射，自动生成sql 	
		对操作多表，特别繁琐，配置文件特别冗余 
- JPA：JPA是Java Persistence API的简称，中文名Java持久层API，是JDK 5.0注解或XML描述对象－关系表的映射关系，并将运行期的实体对象持久化到数据库中 

## 10. 占位符 $ 和 '#' 

## 11. 框架底层实现技术是什么？
- 反射机制

## 12. 代理模式的实现方式？
- jdk代理
- cglib

## 13. 服务器宕机的原因有哪些？
- 高并发（访问量大）
- 可能受病毒攻击
- 性能差
- 服务器集群不合理（优化问题）

## 14. 解决高并发的最有效手段是什么？
- 通过nginx实现负载均衡，合理搭建服务器集群

## 15.数据库如何实现高可用？
- Mycat作用为：能满足数据库数据大量存储；提高了查询性能
支持MySQL ORACLE SQLServer等一些主流的数据库

## 16. 你了解的设计模式有哪几种？
- 单例
- 工厂
- 观察者
- 适配器
## 17. 请手写单例模式的代码
```java
public class LazyMode {
    private static LazyMode sLazyMode;

    private LazyMode() {
    System.out.println("create " + getClass().getSimpleName());
 }

```

```java
public static LazyMode getInstance(){
    if (sLazyMode == null) {
        sLazyMode = new LazyMode();
    }
    return sLazyMode;
}
```

```java
 public static void main(String[] args) { 
   LazyMode.getInstance(); 
 }
```

## 18. ajax的作用？
- 异步向服务发请求，实现页面局部刷新，减少流量和服务端的内存压力

## 19. 你用过哪些前端框架？
你用过哪些前端技术？

## 20. 数据库索引的作用?
- 提高查询效率

## 21. 查询数据时如何提高查询效率？
- 建立索引
- sql语句的优化
- 尽量减少表与表之间的关联


## 22. 某张表中有数据1000条，其中有200条数重复，请问如何去重？
- group by 方法
- distinct方法  例子：select distinct name  From student 

## 23. 数据库中如何现实分页查询？
- limit ?,?  偏移量   每页有多少条数据

## 24. 数据库中事务的特性有哪些？
1. 原子性：事务包含的所有数据库操作要么全部成功，要不全部失败回滚
2. 一致性：一个事务执行之前和执行之后都必须处于一致性状态。拿转账来说，假设用户A和用户B两者的钱加起来一共是5000，那么不管A和B之间如何转账，转几次账，事务结束后两个用户的钱相加起来应该还得是5000，这就是事务的一致性。
3. 隔离性：一个事务未提交的业务结果是否对于其它事务可见。级别一般有：read_uncommit，read_commit，read_repeatable，串行化访问。
4.持久性：一个事务一旦被提交了，那么对数据库中数据的改变就是永久性的，即便是在数据库系统遇到故障的情况下也不会丢失提交事务的操作


## 25. 数据库字段约束类型有哪些？
- 非空约束  not null 
- 唯一约束   unique
- 检查约束 check() 
- 主键和外键约束  

## 26. 线程状态有哪几种?
- new Thread（） 新建
- start()  就绪
- 当cpu给线程分配时间片段时，才进入 运行
- 调用sleep() 进入 阻塞
- run() 执行完  进入 死亡 

## 27. 理解线程的同步和异步？
线程安全问题？针对是否访问同一资源的时候，出现的数据安全问题

## 28. Http协议和RPC协议的区别
UPD/TCP：传输层

## 29. osi七层模型


## 30. http常见的异常状态码有哪些？分别是什么原因造成？


## 31. jsp和servlet的区别？

## 32. jsp的隐含对象?

## 33. servlet的生命周期?

## 34. get和post的区别？

## 35. session和cookie的区别？

## 36. JDK8的新特性?

## 37. 重定向和转发的区别？

## 38. 简述目前互联网中电商软件特点？
- 高并发
- 分布式架构
- 高可用
- 安全性

## 39. eureka 微服务的注册中心 
zookeeper 是属于dubbo框架的注册中心

## 40. 关系型数据库和非关系型数据库的存储特点?

## 41. hashtable和hashmap的区别？
- hashtable：线程安全意味这性能低
- hashmap：允许有null键值 非线程安全，性能较高
hashmap初始化容量是多少？--16

## 42. 写出java中常见的异常？

## 43. List集合和set集合的存储特点？和底层实现结构?

## 44. 异常的顶级父类？
Throwbale 
-- Exception
-- Error

## 45.& 和 &&的区别

## 46.== 和 equals的区别？

## 47.java中序列化的意义？对象如何实现序列化？

## 48.B/S，C/S的架构的区别？

## 49.String 和StringBuffer和StringBuilder的区别

## 50. OOM是什么？产生OOM的原因？
内存溢出


职场规则：
1.会做人 
2.会做事













































 











