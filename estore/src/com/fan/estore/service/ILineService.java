package com.fan.estore.service;

import java.util.List;

import com.fan.estore.bean.Line;
import com.fan.estore.myexception.LineException;

public interface ILineService {
	void saveLine(Line line) throws LineException;
	List<Line> findLineByOrderId(Long id) throws LineException;
	void deleteLineByOId(Long id) throws LineException;
}
