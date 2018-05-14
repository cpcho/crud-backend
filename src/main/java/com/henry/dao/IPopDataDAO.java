package com.henry.dao;

import java.util.List;

import com.henry.entity.PopData;

public interface IPopDataDAO {
	List<PopData> getAllPopData();

	PopData getPopDataById(int id);

	void createPopData(PopData pd);

	void updatePopData(PopData pd);

	void deletePopData(int id);

	boolean popDataExists(String name);
}