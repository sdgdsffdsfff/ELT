package com.chinarewards.gwt.elt.client.staffHeavenIndex.model;

import java.io.Serializable;
import java.util.Date;

import com.chinarewards.gwt.elt.client.broadcasting.model.BroadcastingListCriteria.BroadcastingCategory;
import com.chinarewards.gwt.elt.client.broadcasting.model.BroadcastingListCriteria.BroadcastingStatus;

public class StaffHeavenIndexClient implements Serializable, Comparable<StaffHeavenIndexClient> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4934837755724342679L;
	/**
	 * ID
	 */
	private String id;
	/**
	 * 编号
	 */
	private String number;
	/**
	 * 内容
	 */
	private String content;
	/**
	 * 状态
	 */
	private BroadcastingStatus status;

	
	private String createdByUserName;	
	private String deptName;	
	private String staffId;	
	
	/**
	 * 类别
	 */
	private BroadcastingCategory category;
	/**
	 * 广播时间
	 */
	private Date broadcastingTime;

	/**
	 * 回复数
	 */
	private int replyNumber;

	/**
	 * 是否允许回复
	 */
	private boolean isAllowreplies;
	
	
	private String receivingDept;
	private String receivingStaffId;
	private String receivingUserName;
	
	public String getReceivingStaffId() {
		return receivingStaffId;
	}

	public void setReceivingStaffId(String receivingStaffId) {
		this.receivingStaffId = receivingStaffId;
	}

	public String getReceivingDept() {
		return receivingDept;
	}

	public void setReceivingDept(String receivingDept) {
		this.receivingDept = receivingDept;
	}

	public String getReceivingUserName() {
		return receivingUserName;
	}

	public void setReceivingUserName(String receivingUserName) {
		this.receivingUserName = receivingUserName;
	}

	public String getStaffId() {
		return staffId;
	}

	public void setStaffId(String staffId) {
		this.staffId = staffId;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public boolean isAllowreplies() {
		return isAllowreplies;
	}

	public void setAllowreplies(boolean isAllowreplies) {
		this.isAllowreplies = isAllowreplies;
	}
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public BroadcastingStatus getStatus() {
		return status;
	}

	public void setStatus(BroadcastingStatus status) {
		this.status = status;
	}

	public String getCreatedByUserName() {
		return createdByUserName;
	}

	public void setCreatedByUserName(String createdByUserName) {
		this.createdByUserName = createdByUserName;
	}

	public BroadcastingCategory getCategory() {
		return category;
	}

	public void setCategory(BroadcastingCategory category) {
		this.category = category;
	}

	public Date getBroadcastingTime() {
		return broadcastingTime;
	}

	public void setBroadcastingTime(Date broadcastingTime) {
		this.broadcastingTime = broadcastingTime;
	}

	public int getReplyNumber() {
		return replyNumber;
	}

	public void setReplyNumber(int replyNumber) {
		this.replyNumber = replyNumber;
	}

	@Override
	public int compareTo(StaffHeavenIndexClient o) {
		// TODO Auto-generated method stub
		return 0;
	}



}
