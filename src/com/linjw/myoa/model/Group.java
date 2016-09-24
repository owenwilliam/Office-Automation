package com.linjw.myoa.model;

import java.util.HashSet;
import java.util.Set;

public class Group {
	private Long id;
	private String name;
	private String description;
	private User person;
	private Set<Personinfo> personinfos = new HashSet<Personinfo>();
	private int count;
	
	
	private Personinfo personinfo;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public User getPerson() {
		return person;
	}
	public void setPerson(User person) {
		this.person = person;
	}
	public Set<Personinfo> getPersoninfos() {
		return personinfos;
	}
	public void setPersoninfos(Set<Personinfo> personinfos) {
		this.personinfos = personinfos;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public Personinfo getPersoninfo() {
		return personinfo;
	}
	public void setPersoninfo(Personinfo personinfo) {
		this.personinfo = personinfo;
	}

}
