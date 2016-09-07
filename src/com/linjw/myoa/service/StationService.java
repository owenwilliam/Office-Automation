package com.linjw.myoa.service;

import java.util.List;

import com.linjw.myoa.model.Station;

public interface StationService {

	//查询所有
	public List<Station> findAll();
	//删除实体
	public void detele(long id);
	//保存实体
	public void save(Station station);
	//通过ID查询实体
	public Station getById(long id);
	//更新实体
	public void update(Station station);
}
