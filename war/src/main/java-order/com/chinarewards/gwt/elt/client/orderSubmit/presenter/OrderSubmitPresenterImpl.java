package com.chinarewards.gwt.elt.client.orderSubmit.presenter;

import net.customware.gwt.dispatch.client.DispatchAsync;

import com.chinarewards.gwt.elt.client.awardShop.plugin.AwardShopListConstants;
import com.chinarewards.gwt.elt.client.core.Platform;
import com.chinarewards.gwt.elt.client.detailsOfGift.model.DetailsOfGiftClient;
import com.chinarewards.gwt.elt.client.detailsOfGift.plugin.DetailsOfGiftConstants;
import com.chinarewards.gwt.elt.client.mvp.BasePresenter;
import com.chinarewards.gwt.elt.client.mvp.ErrorHandler;
import com.chinarewards.gwt.elt.client.mvp.EventBus;
import com.chinarewards.gwt.elt.client.orderHistory.plugin.OrderHistoryConstants;
import com.chinarewards.gwt.elt.client.orderSubmit.model.OrderSubmitClient;
import com.chinarewards.gwt.elt.client.orderSubmit.presenter.OrderSubmitPresenter.OrderSubmitDisplay;
import com.chinarewards.gwt.elt.client.orderSubmit.request.OrderSubmitRequest;
import com.chinarewards.gwt.elt.client.orderSubmit.request.OrderSubmitResponse;
import com.chinarewards.gwt.elt.client.support.SessionManager;
import com.chinarewards.gwt.elt.client.win.Win;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.inject.Inject;

public class OrderSubmitPresenterImpl extends BasePresenter<OrderSubmitDisplay>
		implements OrderSubmitPresenter {

	final DispatchAsync dispatch;
	final ErrorHandler errorHandler;
	final SessionManager sessionManager;
	final Win win;
	OrderSubmitClient orderVo;

	@Inject
	public OrderSubmitPresenterImpl(EventBus eventBus, DispatchAsync dispatch,
			ErrorHandler errorHandler, SessionManager sessionManager,
			OrderSubmitDisplay display, Win win) {
		super(eventBus, display);
		this.dispatch = dispatch;
		this.errorHandler = errorHandler;
		this.sessionManager = sessionManager;
		this.win = win;

	}

	@Override
	public void bind() {
		init();

	}

	private void init() {
		display.getMessage().setVisible(false);
		

		display.getShopImage().setUrl("imageshow?imageName="+orderVo.getGiftImage());
		display.setShopText(orderVo.getGiftName());
		display.setTotal(orderVo.getIntegral()+"");
		display.setUnitprice(orderVo.getIntegral()+"");
		display.setSource(orderVo.getSource()+"");
		display.setNumber(orderVo.getNumber()+"");
		display.setMybalance(orderVo.getUserBalance()+"");
		display.setName(orderVo.getName());
		display.setPhone(orderVo.getPhone());
		display.setZipCode(orderVo.getZipCode());
		display.setOrderDefinition(orderVo.getOrderDefinition());
		display.setAddress(orderVo.getAddress());
		
			
		

	display.getConfirmbutton().addClickHandler(new ClickHandler() {
		
		@Override
		public void onClick(ClickEvent event) {
	
				dispatch.execute(new OrderSubmitRequest(orderVo.getOrderId(),sessionManager.getSession().getToken()),
						new AsyncCallback<OrderSubmitResponse>() {
							@Override
							public void onFailure(Throwable e) {
								errorHandler.alert(e.getMessage());
							}

							@Override
							public void onSuccess(OrderSubmitResponse response) {
								display.getConfirmbuttonObj().setEnabled(false);
								String rs=response.getResult();
								if("ok".equals(rs))
								{
									Platform.getInstance()
									.getEditorRegistry()
									.openEditor(
											OrderHistoryConstants.EDITOR_ORDERHISTORY_SEARCH,
											OrderHistoryConstants.EDITOR_ORDERHISTORY_SEARCH, orderVo);

									
									//完成后..跳出订单列表
								}
								else
								{
									win.alert("支付失败!");
								}
								
				
							}

						});


			
		}
	});
	display.getReturnbutton().addClickHandler(new ClickHandler() {
		
		@Override
		public void onClick(ClickEvent event) {
			Platform.getInstance()
			.getEditorRegistry()
			.openEditor(
					AwardShopListConstants.EDITOR_AWARDSHOPLIST_SEARCH,
					"EDITOR_AWARDSHOPLIST_SEARCH_DO_ID", null);
			
		}
	});
	display.getShopText().addClickHandler(new ClickHandler() {
		
		@Override
		public void onClick(ClickEvent event) {
			Platform.getInstance()
			.getEditorRegistry()
			.openEditor(
					DetailsOfGiftConstants.EDITOR_DETAILSOFGIFT_SEARCH,
					"EDITOR_DETAILSOFGIFT_SEARCH_DO_ID", new DetailsOfGiftClient(orderVo.getGiftId()));
			
		}
	});
	}

	@Override
	public void initOrderSubmit(OrderSubmitClient orderVo) {
		this.orderVo=orderVo;
		
	}



}
