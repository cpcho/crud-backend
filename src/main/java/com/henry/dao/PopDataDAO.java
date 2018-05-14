package com.henry.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.henry.entity.PopData;

@Transactional
@Repository
public class PopDataDAO implements IPopDataDAO {
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public PopData getPopDataById(int id) {
		return entityManager.find(PopData.class, id);
	}

//	@SuppressWarnings("unchecked")
//	@Override
//	public List<PopData> getAllPopData() {
//		String hql = "FROM pop_data as pd ORDER BY pd.popDataId DESC";
//		return (List<PopData>) entityManager.createQuery(hql).getResultList();
//	}

	@Override
	public void createPopData(PopData pd) {
		entityManager.persist(pd);
	}

	@Override
	public void updatePopData(PopData pd) {
		PopData pdd = getPopDataById(pd.getId());
		pdd.setName(pd.getName());
		pdd.setPop(pd.getPop());
		pdd.setLat(pd.getLat());
		pdd.setLon(pd.getLon());
		entityManager.flush();
	}

	@Override
	public void deletePopData(int id) {
		entityManager.remove(getPopDataById(id));
	}

	@Override
	public boolean popDataExists(String name) {
		String hql = "FROM pop_data as pd WHERE pd.name = ?";
		int count = entityManager.createQuery(hql).setParameter(1, name).getResultList().size();
		return count > 0 ? true : false;
	}

	@Override
	public List<PopData> getAllPopData() {
		// TODO Auto-generated method stub
		return null;
	}
}
