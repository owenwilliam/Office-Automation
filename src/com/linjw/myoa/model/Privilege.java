package com.linjw.myoa.model;

import java.util.HashSet;
import java.util.Set;

/**
 * 权根设置用到的角色
 * @author 林剑文　　2014-7-9
 *
 */
public class Privilege {
	private Long id;
	private String url;
	private String name;//权根名称
	private Set<Station> stations = new HashSet<Station>();//与岗位关联
	private Privilege parent;//上级
	private Set<Privilege> children = new HashSet<Privilege>();//下级
	
	public Privilege() {
		
	}
	
	public Privilege(String name,String url, Privilege parent) {
		super();
		this.url = url;
		this.name = name;
		this.parent = parent;
	}
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Set<Station> getStations() {
		return stations;
	}
	public void setStations(Set<Station> stations) {
		this.stations = stations;
	}
	public Privilege getParent() {
		return parent;
	}
	public void setParent(Privilege parent) {
		this.parent = parent;
	}
	public Set<Privilege> getChildren() {
		return children;
	}
	public void setChildren(Set<Privilege> children) {
		this.children = children;
	}

}
