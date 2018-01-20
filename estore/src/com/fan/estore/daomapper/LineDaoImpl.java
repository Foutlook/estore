package com.fan.estore.daomapper;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import com.fan.estore.bean.Line;
import com.fan.estore.bean.Order;
import com.fan.estore.dao.ILineDao;
import com.fan.estore.mapper.LineMapper;

public class LineDaoImpl implements ILineDao {

	@Autowired
	LineMapper mapper;

	@Override
	public void saveLine(Line line) {
		mapper.saveLine(line);
	}

	@Override
	public List<Line> findLineByOId(Long id) {
		List<Line> lines = null;
		lines = mapper.findLineByOId(id);
		return lines;
	}

	@Override
	public void deleteLineByOId(Long id) {
		mapper.deleteLineByOId(id);
	}

	@Override
	public List<Order> findOrderWithBookByOId(Long id) {
		return null;
	}
}
