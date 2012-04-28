package com.chinarewards.elt.model.budget.search;

import java.util.Date;
import java.util.List;

import com.chinarewards.elt.model.common.PaginationDetail;
import com.chinarewards.elt.model.common.SortingDetail;

public class AskBudgetVo {
		
	private PaginationDetail paginationDetail;
	public PaginationDetail getPaginationDetail() {
		return paginationDetail;
	}
	public void setPaginationDetail(PaginationDetail paginationDetail) {
		this.paginationDetail = paginationDetail;
	}
	public SortingDetail getSortingDetail() {
		return sortingDetail;
	}
	public void setSortingDetail(SortingDetail sortingDetail) {
		this.sortingDetail = sortingDetail;
	}

	
	private SortingDetail sortingDetail;
	private String id;
	private String corpBudgetId ;   //企业财年预算ID
	private String corpBudget ;   //企业财年预算名称
	private String departmentId;    //申请部门ID	
	private String depName;         //申请部门名称	
	public String getDepName() {
		return depName;
	}
	public void setDepName(String depName) {
		this.depName = depName;
	}

	private String manageDepId;//管理的主部门ID
	public String getManageDepId() {
		return manageDepId;
	}

	public void setManageDepId(String manageDepId) {
		this.manageDepId = manageDepId;
	}
	private double askIntegral;     //申请预算积分
    private double approveIntegeral;    //审批积分
    private String reason;         //申请原因
   
    private String recorduser;     //申请人ID
    private Date   recorddate;     //申请时间
    private String recordname;     //申请姓名


	private String approveuser;    //审批人
    private Date   approvedate;    //审批时间
    private int    status;         //是否审批 
    private int    deleted   ;     //是否删除 
    private String view;           //审批意见
    public String getView() {
		return view;
	}
	public String getRecordname() {
		return recordname;
	}
	public void setRecordname(String recordname) {
		this.recordname = recordname;
	}
	public void setView(String view) {
		this.view = view;
	}
    private List<String> deptIds;
    
	public String getCorpBudget() {
		return corpBudget;
	}
	public void setCorpBudget(String corpBudget) {
		this.corpBudget = corpBudget;
	}
	public List<String> getDeptIds() {
		return deptIds;
	}
	public void setDeptIds(List<String> deptIds) {
		this.deptIds = deptIds;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCorpBudgetId() {
		return corpBudgetId;
	}
	public void setCorpBudgetId(String corpBudgetId) {
		this.corpBudgetId = corpBudgetId;
	}
	public String getDepartmentId() {
		return departmentId;
	}
	public void setDepartmentId(String departmentId) {
		this.departmentId = departmentId;
	}
	public double getAskIntegral() {
		return askIntegral;
	}
	public void setAskIntegral(double askIntegral) {
		this.askIntegral = askIntegral;
	}
	public double getApproveIntegeral() {
		return approveIntegeral;
	}
	public void setApproveIntegeral(double approveIntegeral) {
		this.approveIntegeral = approveIntegeral;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public String getRecorduser() {
		return recorduser;
	}
	public void setRecorduser(String recorduser) {
		this.recorduser = recorduser;
	}
	public Date getRecorddate() {
		return recorddate;
	}
	public void setRecorddate(Date recorddate) {
		this.recorddate = recorddate;
	}
	public String getApproveuser() {
		return approveuser;
	}
	public void setApproveuser(String approveuser) {
		this.approveuser = approveuser;
	}
	public Date getApprovedate() {
		return approvedate;
	}
	public void setApprovedate(Date approvedate) {
		this.approvedate = approvedate;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public int getDeleted() {
		return deleted;
	}
	public void setDeleted(int deleted) {
		this.deleted = deleted;
	}
   
	
}
