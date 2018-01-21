package com.fan.estore.service;

import java.util.List;

import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.fan.estore.bean.Line;
import com.fan.estore.dao.ILineDao;
import com.fan.estore.myexception.LineException;

@Transactional(isolation=Isolation.DEFAULT,propagation=Propagation.REQUIRED,readOnly=false)
public class LineServiceImpl implements ILineService {
	private ILineDao lineDao;
	
	@Override
	public void saveLine(Line line) {
		lineDao.saveLine(line);
	}
	
	//通过orderid查询订单项
	@Transactional(readOnly=true)
	@Override
	public List<Line> findLineByOrderId(Long id) throws LineException {
		List<Line> lines;
		try {
			lines = lineDao.findLineByOId(id);
			System.out.println(lines);
		} catch (Exception e) {
			throw new LineException("通过orderid查询数据出错");
		}
		
		return lines;
	}

	@Override
	public void deleteLineByOId(Long id) throws LineException {
		try {
			lineDao.deleteLineByOId(id);
		} catch (Exception e) {
			throw new LineException("通过orderid删除数据出错");
		}
	}

	public void setLineDao(ILineDao lineDao) {
		this.lineDao = lineDao;
	}
	
}
