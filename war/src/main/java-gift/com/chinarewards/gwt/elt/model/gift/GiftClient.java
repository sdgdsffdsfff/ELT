package com.chinarewards.gwt.elt.model.gift;

import java.io.Serializable;

import com.chinarewards.gwt.elt.model.gift.GiftCriteria.GiftStatus;

public class GiftClient implements Serializable, Comparable<GiftClient> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4934837755724342679L;



	// 状态
	private GiftStatus status;
	private String id;
	private String name;
	/**
	 * 来源
	 */
	private String source;
	/**
	 * 库存
	 */
	private String inventory;
	public GiftStatus getStatus() {
		return status;
	}
	public void setStatus(GiftStatus status) {
		this.status = status;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getInventory() {
		return inventory;
	}
	public void setInventory(String inventory) {
		this.inventory = inventory;
	}

	@Override
	public int compareTo(GiftClient o) {
		return o == null ? -1 : o.getId().compareTo(id);
	}






}
