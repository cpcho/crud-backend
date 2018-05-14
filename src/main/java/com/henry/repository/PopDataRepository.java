package com.henry.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.henry.entity.PopData;

public interface PopDataRepository extends CrudRepository<PopData, Long> {
	List<PopData> findByName(String name);
}
