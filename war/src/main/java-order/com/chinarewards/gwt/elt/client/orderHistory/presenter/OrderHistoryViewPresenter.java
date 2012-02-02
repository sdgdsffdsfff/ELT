package com.chinarewards.gwt.elt.client.orderHistory.presenter;

import com.chinarewards.gwt.elt.client.mvp.Display;
import com.chinarewards.gwt.elt.client.mvp.Presenter;
import com.chinarewards.gwt.elt.client.order.model.OrderSearchVo;
import com.chinarewards.gwt.elt.client.order.model.OrderVo;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.ui.Widget;

public interface OrderHistoryViewPresenter extends
		Presenter<OrderHistoryViewPresenter.OrderHistoryViewDisplay> {

	public static interface OrderHistoryViewDisplay extends Display {
		public void showOrderHistory(OrderVo orderVo);

		HasClickHandlers getConfirmbutton();

		HasClickHandlers getReturnbutton();

		public void setBreadCrumbs(Widget asWidget);

	}

	public void initInstanceId(String instanceId, OrderSearchVo model);
}
