package com.chinarewards.gwt.elt.client.enterprise.request;

import java.util.List;

import net.customware.gwt.dispatch.shared.Action;

import com.chinarewards.gwt.elt.client.enterprise.model.EnterpriseVo;
import com.chinarewards.gwt.elt.client.support.UserSession;

/**
 * An action which perform request to search user.
 * 
 * @author yanrui
 */
public class EditPeriodRequest implements Action<EditPeriodResponse> {

	String enterpriseId;
	String nowUserId;
	private EnterpriseVo enterpriseVo;
	private UserSession userSession;

	List<String> staffIds;

	public EditPeriodRequest(EnterpriseVo enterpriseVo, UserSession userSession) {
		this.enterpriseVo = enterpriseVo;
		this.userSession = userSession;
	}

	/**
	 * For serialization
	 */
	public EditPeriodRequest() {
	}


	public String getPeriodId() {
		return enterpriseId;
	}

	public void setPeriodId(String enterpriseId) {
		this.enterpriseId = enterpriseId;
	}

	public String getNowUserId() {
		return nowUserId;
	}

	public void setNowUserId(String nowUserId) {
		this.nowUserId = nowUserId;
	}

	public EnterpriseVo getEnterpriseVo() {
		return enterpriseVo;
	}

	public void setEnterpriseVo(EnterpriseVo enterpriseVo) {
		this.enterpriseVo = enterpriseVo;
	}

	public UserSession getUserSession() {
		return userSession;
	}

	public void setUserSession(UserSession userSession) {
		this.userSession = userSession;
	}

	public List<String> getStaffIds() {
		return staffIds;
	}

	public void setStaffIds(List<String> staffIds) {
		this.staffIds = staffIds;
	}

}
