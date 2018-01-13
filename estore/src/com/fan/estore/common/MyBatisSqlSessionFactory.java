package com.fan.estore.common;

import java.io.IOException;
import java.io.InputStream;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

//封装得到sqlsession
public class MyBatisSqlSessionFactory {
	public static SqlSessionFactory sqlSessionFactory;
	private static SqlSessionFactory getSqlSessionFactory(){
		if(sqlSessionFactory == null){
			InputStream inputStream = null;
			try {
				// 首先加载mybatis-config.xml配置文件
				inputStream = Resources.getResourceAsStream("mybatis-config.xml"); 
				// 得到SQLSessionFactory
				sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
			} catch (IOException e) {
				e.printStackTrace();
				throw new RuntimeException(e.getCause());
			}
		}
		return sqlSessionFactory;
	}
	private static SqlSession getOpenSession(boolean autocommit){
		//通过SqlSessionFactory得到SqlSession(通过SqlSession来动态生成映射接口的实现类)
		return getSqlSessionFactory().openSession(autocommit);
	}
	public static SqlSession getSqlSession(){
		return getOpenSession(false);
	}
}
