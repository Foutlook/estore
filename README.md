# estore
The use of Servlet，Spring and Mybatis.
本项目使用Servlet，Spring 和 Mybatis 写成的，是一个小型的购物系统。

一开始的学习中，本项目并没有使用spring，这两天通过对spring的学习，为了练习spring的使用，对spring进行添加整合，
spring真是个强大的框架，令我对框架产生了浓厚的兴趣。

通过使用spring的IOC(控制反转)，DI(依赖注入)，Aop(切面编程)三大功能，对对象和事务进行管理。
IOC和DI：对servlet使用的Service层的对象和Service层使用的Dao层的对象都交给spring管理，在系统加载的时候，初始化spring容器。

示例代码：
在web.xml 下配置监听spring容器的路径：

    <!-- 可以让spring容器课可以随项目的创建而创建，销毁而销毁 -->
    <listener>
      <listener-class>org.springframework.web.context.ContextLoaderListener</listener-class>
    </listener>
    <!-- 指定加载spring配置文件applicationContext.xml 的位置 -->
    <context-param>
      <param-name>contextConfigLocation</param-name>
      <param-value>classpath:applicationContext.xml</param-value>
    </context-param>
  
在Servlet层，动态的从spring中取出要使用的对象：
实例代码：

    //获得容器，从监听器中获得，获得spring容器，从application域中获得即可
		//1获得servletContext对象
		ServletContext sc = request.getServletContext();
		//2.从sc中获得ac容器
		WebApplicationContext ac = WebApplicationContextUtils.getWebApplicationContext(sc);
		//3.从容器中获得customerService,bookService,orderService
		ICustomerService customerService = (ICustomerService) ac.getBean("customerService");
		IBookService bookService = (IBookService) ac.getBean("bookService");
		IOrderService orderService = (IOrderService) ac.getBean("orderService");

AOP管理事务：
示例代码：
  <!-- 配置事务，进行事务管理 -->
	<bean name="transactionManager" class="org.springframework.jdbc.datasource.DataSourceTransactionManager">
		<property name="dataSource" ref="dataSource"></property>
	</bean>
	<!-- 第一种方式：通过注解的方式注入事务 -->
	<tx:annotation-driven transaction-manager="transactionManager"/>
  
问题：
我在测试我添加的事务时，在抛出异常的情况下，并没有回滚事务，具体原因我没有找到。等过一段时间把spring和mybatis完整的整合学完看是否能发现其中的原因

下面是我完整的spring.xml 代码：

    <?xml version="1.0" encoding="UTF-8"?>
    <beans xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
    xmlns="http://www.springframework.org/schema/beans" xmlns:context="http://www.springframework.org/schema/context"
    xmlns:aop="http://www.springframework.org/schema/aop" xmlns:tx="http://www.springframework.org/schema/tx"
    xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans-4.2.xsd http://www.springframework.org/schema/context http://www.springframework.org/schema/context/spring-context-4.2.xsd http://www.springframework.org/schema/aop http://www.springframework.org/schema/aop/spring-aop-4.2.xsd http://www.springframework.org/schema/tx http://www.springframework.org/schema/tx/spring-tx-4.2.xsd ">

	<!-- 配置连接数据库的信息文件地址 -->
	<context:property-placeholder location="classpath:datasource.properties"/>
	<!-- 数据库连接池 -->
	<bean id="dataSource" class="org.apache.commons.dbcp.BasicDataSource">
		<property name="driverClassName" value="${jdbc.driver}" />
		<property name="url" value="${jdbc.url}" />
		<property name="username" value="${jdbc.username}" />
		<property name="password" value="${jdbc.password}" />
		<property name="maxActive" value="10" />
		<property name="maxIdle" value="5" />
	</bean>
	<!-- 配置SqlSessionFactory:产生sqlsession的工厂 -->
	<bean name="sqlSessionFactory" class="org.mybatis.spring.SqlSessionFactoryBean">
		<!-- 产生工厂需要DataSource -->
		<property name="dataSource" ref="dataSource"></property>
		<!-- mybatis的核心配置文件 直接把路径给引进来-->
		<property name="configLocation" value="classpath:mybatis-config.xml"></property>
		
		
	</bean>
	
	<!-- 配置Mapper代理对象MapperFactoryBean，可以自动映射接口的实现方法 -->
	<!-- <bean name="mapper" class="org.mybatis.spring.mapper.MapperFactoryBean">
		引入sqlsession工厂，mapper类的实现需要工厂创建
		<property name="sqlSessionFactory" ref="sqlSessionFactory"></property>
		配置Mapper接口======但是这种方式会写很多的接口 -不好用
		<property name="mapperInterface" value="com.fan.estore.dao.ICustomerDao"></property>
		<property name="mapperInterface" value="com.fan.estore.dao.IBookDao"></property>
		<property name="mapperInterface" value="com.fan.estore.dao.ILineDao"></property>
		<property name="mapperInterface" value="com.fan.estore.dao.IOrderDao"></property>
	</bean>  -->
	
	<!-- Mapper动态代理开发   扫描 ==使用MapperScannerConfigurer  扫描包方式配置代理-->
	<bean class="org.mybatis.spring.mapper.MapperScannerConfigurer">
		<!-- 引入sqlsession工厂，mapper类的实现需要工厂创建-->
		<!-- <property name="sqlSessionFactory" ref="sqlSessionFactory"></property> -->
		<!-- 基本包 配置Mapper接口 -->
		<property name="basePackage" value="com.fan.estore.mapper"></property>
	</bean>
	
	<!-- 配置事务，进行事务管理 -->
	<bean name="transactionManager" class="org.springframework.jdbc.datasource.DataSourceTransactionManager">
		<property name="dataSource" ref="dataSource"></property>
	</bean>
	<!-- 第一种方式：通过注解的方式注入事务 -->
	<tx:annotation-driven transaction-manager="transactionManager"/>
	
	<!-- 第二种方式，通过配置xml的形式配置事务 -->
	
	<!-- 将Dao对象配置给 spring容器 -->
	<bean name="customerDao" class="com.fan.estore.daomapper.CustomerDaoImpl"></bean>
	<bean name="bookDao" class="com.fan.estore.daomapper.BookDaoImpl"></bean>
	<bean name="orderDao" class="com.fan.estore.daomapper.OrderDaoImpl"></bean>
	<bean name="lineDao" class="com.fan.estore.daomapper.LineDaoImpl"></bean>

	<!-- 配置Service -->
	<bean name="customerService" class="com.fan.estore.service.CustomerServiceImpl">
		<property name="cusdao" ref="customerDao"></property>
	</bean>

	<bean name="bookService" class="com.fan.estore.service.BookServiceImpl">
		<property name="bookdao" ref="bookDao"></property>
	</bean>

	<bean name="orderService" class="com.fan.estore.service.OrderServiceImpl">
		<property name="orderDao" ref="orderDao"></property>
		<property name="lineDao" ref="lineDao"></property>
	</bean>
	<bean name="lineService" class="com.fan.estore.service.LineServiceImpl">
		<property name="lineDao" ref="lineDao"></property>
	</bean>
    </beans>


