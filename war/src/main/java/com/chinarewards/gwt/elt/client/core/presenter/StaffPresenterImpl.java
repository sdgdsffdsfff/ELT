package com.chinarewards.gwt.elt.client.core.presenter;

import java.util.ArrayList;
import java.util.List;

import net.customware.gwt.dispatch.client.DispatchAsync;

import com.chinarewards.gwt.elt.client.EltGinjector;
import com.chinarewards.gwt.elt.client.awardShop.plugin.AwardShopListConstants;
import com.chinarewards.gwt.elt.client.breadCrumbs.ui.BreadCrumbsMenu;
import com.chinarewards.gwt.elt.client.core.Platform;
import com.chinarewards.gwt.elt.client.core.PluginManager;
import com.chinarewards.gwt.elt.client.core.presenter.StaffPresenter.StaffDisplay;
import com.chinarewards.gwt.elt.client.core.ui.MenuProcessor;
import com.chinarewards.gwt.elt.client.login.LastLoginRoleRequest;
import com.chinarewards.gwt.elt.client.login.LastLoginRoleResponse;
import com.chinarewards.gwt.elt.client.login.event.LoginEvent;
import com.chinarewards.gwt.elt.client.mvp.BasePresenter;
import com.chinarewards.gwt.elt.client.mvp.EventBus;
import com.chinarewards.gwt.elt.client.orderHistory.plugin.OrderHistoryConstants;
import com.chinarewards.gwt.elt.client.staffHeavenIndex.plugin.StaffHeavenIndexConstants;
import com.chinarewards.gwt.elt.client.support.SessionManager;
import com.chinarewards.gwt.elt.model.user.UserRoleVo;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.inject.Inject;

public class StaffPresenterImpl extends BasePresenter<StaffDisplay> implements
		StaffPresenter {
	final PluginManager pluginManager;
	final SessionManager sessionManager;
	final EltGinjector injector;
	final MenuProcessor menuProcessor;
	final DispatchAsync dispatchAsync;
	final BreadCrumbsMenu breadCrumbsMenu;

	@Inject
	public StaffPresenterImpl(EventBus eventBus, StaffDisplay display,
			SessionManager sessionManager, PluginManager pluginManager,
			EltGinjector injector, MenuProcessor menuProcessor,DispatchAsync dispatchAsync,BreadCrumbsMenu breadCrumbsMenu) {
		super(eventBus, display);
		this.sessionManager = sessionManager;
		this.pluginManager = pluginManager;
		this.injector = injector;
		this.menuProcessor = menuProcessor;
		this.dispatchAsync=dispatchAsync;
		this.breadCrumbsMenu=breadCrumbsMenu;
	}

	public void bind() {
		List <UserRoleVo> roleslt = new ArrayList<UserRoleVo>();
		UserRoleVo [] roles=sessionManager.getSession().getUserRoles();

		if(roles.length>0)
		{
			for (UserRoleVo r:roles) {
				roleslt.add(r);
			}
			if(!roleslt.contains(UserRoleVo.CORP_ADMIN) && !roleslt.contains(UserRoleVo.DEPT_MGR))
			{
				display.disableManagementCenter();
			}
			if(!roleslt.contains(UserRoleVo.GIFT))
			{
				display.disableGiftExchange();
			}
			if(!roleslt.contains(UserRoleVo.STAFF))
			{
				display.disableStaffCorner();
			}
		}
		registerHandler(display.getlogBtn().addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				eventBus.fireEvent(new LoginEvent(LoginEvent.LoginStatus.LOGOUT));
			}
		}));
		registerHandler(display.getBtnCollection().addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				Window.alert("收藏");
			}
		}));

		registerHandler(display.getManagementCenter().addClickHandler(
				new ClickHandler() {
					@Override
					public void onClick(ClickEvent event) {
						UserRoleVo role=UserRoleVo.DEPT_MGR;
						
						for (int i = 0; i < sessionManager.getSession().getUserRoles().length; i++) {
							if(UserRoleVo.CORP_ADMIN==sessionManager.getSession().getUserRoles()[i])
							{
								role=UserRoleVo.CORP_ADMIN;
							}
						}
						dispatchAsync.execute(new LastLoginRoleRequest(sessionManager.getSession().getToken(),role),
								new AsyncCallback<LastLoginRoleResponse>() {
	
									@Override
									public void onFailure(Throwable e) {
									//	Window.alert("系统切换出错");
									}
	
									@Override
									public void onSuccess(LastLoginRoleResponse resp) {
										//成功
										if("success".equals(resp.getFal()))
											GWT.log("success update last login role ");
										
									}
								});
						Window.Location.reload();
					}
				}));
		registerHandler(display.getGiftExchange().addClickHandler(
				new ClickHandler() {
					@Override
					public void onClick(ClickEvent event) {
						dispatchAsync.execute(new LastLoginRoleRequest(sessionManager.getSession().getToken(),UserRoleVo.GIFT),
								new AsyncCallback<LastLoginRoleResponse>() {
	
									@Override
									public void onFailure(Throwable e) {
									//	Window.alert("系统切换出错");
									}
	
									@Override
									public void onSuccess(LastLoginRoleResponse resp) {
										//成功
										if("success".equals(resp.getFal()))
											GWT.log("success update last login role ");
										
									}
								});
						Window.Location.reload();
					}
				}));

		registerHandler(display.getAwardShop().addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				Platform.getInstance()
				.getEditorRegistry()
				.openEditor(
						AwardShopListConstants.EDITOR_AWARDSHOPLIST_SEARCH,
						"EDITOR_AWARDSHOPLIST_SEARCH_DO_ID", null);
			}
		}));
		
		registerHandler(display.getExchangeHistory().addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				Platform.getInstance()
				.getEditorRegistry()
				.openEditor(
						OrderHistoryConstants.EDITOR_ORDERHISTORY_SEARCH,
						"EDITOR_ORDERHISTORY_SEARCH_DO_ID", null);
			}
		}));
		//员工首页
		registerHandler(display.getStaffHeavenIndex().addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				Platform.getInstance()
				.getEditorRegistry()
				.openEditor(
						StaffHeavenIndexConstants.EDITOR_STAFFHEAVENINDEX_SEARCH,
						"EDITOR_STAFFHEAVENINDEX_SEARCH_DO_ID", null);
			}
		}));
	}

	public StaffDisplay getDisplay() {
		return display;
	}

}
