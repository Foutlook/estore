package com.fan.estore.dao;

import java.util.List;

import com.fan.estore.bean.Line;

public interface ILineDao  {
	void saveLine(Line line);
	List<Line> findLineByOId(Long id);
	void deleteLineByOId(Long id);
}
