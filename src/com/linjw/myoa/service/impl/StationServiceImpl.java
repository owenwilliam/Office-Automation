package com.linjw.myoa.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.linjw.myoa.dao.StationDao;
import com.linjw.myoa.model.Station;
import com.linjw.myoa.service.StationService;
@Service
@Transactional
public class StationServiceImpl implements StationService {

	@Resource
	private StationDao stationDao;
	//列表显示所有的岗位
	public List<Station> findAll() {
		return stationDao.findAll();
	}
   //删除岗位
	public void detele(Long id) { 
		stationDao.delete(id);
	}
   //保存岗位
	public void save(Station station) {
        stationDao.save(station);
	}
  //通过ID查找某个岗位
	public Station getById(Long id) {
		return stationDao.getById(id);
	}
  //更新岗位信息
	public void update(Station station) {
      stationDao.update(station);
	}

}
