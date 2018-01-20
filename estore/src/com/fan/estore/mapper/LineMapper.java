package com.fan.estore.mapper;

import java.util.List;

import com.fan.estore.bean.Line;
import com.fan.estore.bean.Order;

public interface LineMapper {
	void saveLine(Line line);
	List<Line> findLineByOId(Long id);
	void deleteLineByOId(Long id);
	List<Order> findOrderWithBookByOId(Long id);
}
