package com.henry.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.henry.dao.IPopDataDAO;
import com.henry.entity.PopData;
import com.henry.repository.PopDataRepository;

@Service
public class PopDataService implements IPopDataService {
	@Autowired
	private IPopDataDAO popDataDAO;
	@Autowired
	private PopDataRepository pdRepository;

	@Override
	public PopData getPopDataById(int id) {
		PopData pdObj = popDataDAO.getPopDataById(id);
//		PopData pdObj = pdRepository.findById(id).
		return pdObj;
	}

	@Override
	public List<PopData> getAllPopData() {
		return popDataDAO.getAllPopData();
	}

	@Override
	public synchronized boolean createPopData(PopData pd) {
		if (popDataDAO.popDataExists(pd.getName())) {
			return false;
		} else {
			popDataDAO.createPopData(pd);
			return true;
		}
	}

	@Override
	public void updatePopData(PopData pd) {
		popDataDAO.updatePopData(pd);
	}

	@Override
	public void deletePopData(int id) {
		popDataDAO.deletePopData(id);
	}
}