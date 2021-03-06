package com.szf.cms.domain;

import java.io.Serializable;

/**
 * 
 * @ClassName: Channel 
 * @Description: 栏目表
 * @author: 宋圳峰
 * @date: 2020年5月28日 上午11:50:12
 */
public class Channel implements Serializable{
	/**
	 * @fieldName: serialVersionUID
	 * @fieldType: long
	 * @Description: TODO
	 */
	private static final long serialVersionUID = -3574416168048232186L;
	private Integer id;
	private String name;
	private String description;
	private String icon;
	private Integer sorted;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
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

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public Integer getSorted() {
		return sorted;
	}

	public void setSorted(Integer sorted) {
		this.sorted = sorted;
	}

	@Override
	public String toString() {
		return "Channel [id=" + id + ", name=" + name + ", description=" + description + ", icon=" + icon + ", sorted="
				+ sorted + "]";
	}

}
