package com.chinarewards.gwt.elt.client.win.alert;


import com.chinarewards.gwt.elt.client.core.ui.impl.AbstractDialog;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.google.inject.Provider;

public class StaffAlertDialog extends AbstractDialog {
	final Provider<StaffAlertPresenter> presenterProvider;
	StaffAlertPresenter presenter;

	@Inject
	public StaffAlertDialog(Provider<StaffAlertPresenter> presenterProvider) {
		super("Alert", "Alert");
		this.presenterProvider = presenterProvider;
		presenter = presenterProvider.get();
		presenter.setDialog(this);
		presenter.bind();
		setTitle("消息");
	}

	@Override
	public Widget asWidget() {
		return presenter.getDisplay().asWidget();
	}

	@Override
	public boolean beforeClose() {
		presenter.unbind();
		return true;
	}

	public void setMsg(String msg) {
		presenter.getDisplay().setMsg(msg);
	}
	public void setImage(String url) {
		presenter.getDisplay().setImage(url);
	}
}
