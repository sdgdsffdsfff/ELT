package com.chinarewards.gwt.elt.server.budget;

import java.util.ArrayList;
import java.util.List;

import net.customware.gwt.dispatch.server.ExecutionContext;
import net.customware.gwt.dispatch.shared.DispatchException;

import org.slf4j.Logger;

import com.chinarewards.elt.domain.budget.DepartmentBudget;
import com.chinarewards.elt.model.budget.search.DepartmentBudgetVo;
import com.chinarewards.elt.model.common.PageStore;
import com.chinarewards.elt.model.common.PaginationDetail;
import com.chinarewards.elt.model.common.SortingDetail;
import com.chinarewards.elt.model.user.UserContext;
import com.chinarewards.elt.service.budget.BudgetService;
import com.chinarewards.gwt.elt.client.budget.model.DepBudgetVo;
import com.chinarewards.gwt.elt.client.budget.request.AddDepartmentBudgetRequest;
import com.chinarewards.gwt.elt.client.budget.request.AddDepartmentBudgetResponse;
import com.chinarewards.gwt.elt.server.BaseActionHandler;
import com.chinarewards.gwt.elt.server.logger.InjectLogger;
import com.chinarewards.gwt.elt.util.UserRoleTool;
import com.google.inject.Inject;

/**
 * @author lw
 * @since 2012年1月10日 16:09:07
 */
public class AddDepBudgetHandler extends
		BaseActionHandler<AddDepartmentBudgetRequest, AddDepartmentBudgetResponse> {

	@InjectLogger
	Logger logger;

	BudgetService budgetService;

	@Inject
	public AddDepBudgetHandler(BudgetService budgetService) {
		this.budgetService = budgetService;

	}

	@Override
	public AddDepartmentBudgetResponse execute(AddDepartmentBudgetRequest request,
			ExecutionContext context) throws DispatchException {

		AddDepartmentBudgetResponse resp = new AddDepartmentBudgetResponse();

		DepBudgetVo clientVo = request.getDepartmentBudgetVo();
		DepartmentBudget serviceVo = adapter(clientVo);//从客户端转到实体
		
		UserContext uc = new UserContext();
		uc.setCorporationId(request.getUserSession().getCorporationId());
		uc.setLoginName(request.getUserSession().getLoginName());
		uc.setUserRoles(UserRoleTool.adaptToRole(request.getUserSession().getUserRoles()));
		uc.setUserId(request.getUserSession().getToken());
		String id = budgetService.findByDepAndCorpBudgetId(serviceVo);
		if(id.equals("")){
		   DepartmentBudget dep = budgetService.saveDepartmentBudget(uc, serviceVo);
		      resp.setMessage("保存成功");
		}else{
			  resp.setMessage("保存失败，数据已存在");
		}
		return resp;
	}

	private DepartmentBudget adapter(DepBudgetVo criteria) {
		DepartmentBudget service = new DepartmentBudget();
		service.setBudgetIntegral(criteria.getBudgetIntegral());
		service.setCorpBudgetId(criteria.getCorpBudgetId());
		service.setDepartmentId(criteria.getDepartmentId());
		service.setUseIntegeral(criteria.getUseIntegeral());
		return service;
	}
	
	@Override
	public Class<AddDepartmentBudgetRequest> getActionType() {
		return AddDepartmentBudgetRequest.class;
	}

	@Override
	public void rollback(AddDepartmentBudgetRequest req, AddDepartmentBudgetResponse resp,
			ExecutionContext cxt) throws DispatchException {
	}

}
