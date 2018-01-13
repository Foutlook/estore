package com.fan.estore.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.fan.estore.bean.Line;
import com.fan.estore.common.MyBatisSqlSessionFactory;

public class LineDaoImpl implements ILineDao {

	@Override
	public void saveLine(Line line) {
		SqlSession session = MyBatisSqlSessionFactory.getSqlSession();
		try {
			ILineDao mapper = session.getMapper(ILineDao.class);
			mapper.saveLine(line);
			session.commit();
		} catch (Exception e) {
			session.rollback();
		} finally {
			if (session != null) {
				session.close();
			}
		}
	}

	@Override
	public List<Line> findLineByOId(Long id) {
		//通过order的id查询订单项
		SqlSession session = MyBatisSqlSessionFactory.getSqlSession();
		List<Line> lines = null;
		try {
			ILineDao mapper = session.getMapper(ILineDao.class);
			lines = mapper.findLineByOId(id);
			session.commit();
		} catch (Exception e) {
			session.rollback();
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return lines;
	}

	@Override
	public void deleteLineByOId(Long id) {
		SqlSession session = MyBatisSqlSessionFactory.getSqlSession();
		try {
			ILineDao mapper = session.getMapper(ILineDao.class);
			mapper.deleteLineByOId(id);
			session.commit();
		} catch (Exception e) {
			session.rollback();
		} finally {
			if (session != null) {
				session.close();
			}
		}
	}
}
