package com.henry.service;

import java.util.List;

import com.henry.entity.PopData;

public interface IPopDataService {
	List<PopData> getAllPopData();
	
//	Iterable<PopData> getAllPopData();

	PopData getPopDataById(int id);

	boolean createPopData(PopData pd);

	void updatePopData(PopData pd);

	void deletePopData(int id);
}