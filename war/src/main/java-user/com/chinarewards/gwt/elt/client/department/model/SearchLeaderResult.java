package com.chinarewards.gwt.elt.client.department.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.chinarewards.gwt.elt.client.rewards.model.StaffClient;

public class SearchLeaderResult implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -412350825877288258L;
	private List<StaffClient> result = new ArrayList<StaffClient>();
	private int total;

	public List<StaffClient> getResult() {
		return result;
	}

	public void setResult(List<StaffClient> result) {
		this.result = result;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

}
