package com.chinarewards.gwt.elt.client.core.view;

import java.util.Date;

import com.chinarewards.gwt.elt.client.core.presenter.DockPresenter.DockDisplay;
import com.chinarewards.gwt.elt.client.core.view.constant.ViewConstants;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DockLayoutPanel;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.InlineLabel;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.Widget;

public class DockWidget extends Composite implements DockDisplay {

	@UiField
	DockLayoutPanel dock;

	@UiField
	FlowPanel menu;

	@UiField
	Anchor logBtn;

	@UiField
	InlineLabel message;

	@UiField
	Anchor btnEmail;

	@UiField
	Anchor btnGb;
	@UiField
	Anchor btnRewardItem;
	@UiField
	Anchor btnReward;
	@UiField
	Anchor btnStaff;
	@UiField
	Anchor btnSetting;
	@UiField
	Anchor btnGift;
	@UiField
	Anchor collectionBtn;
	
	@UiField
	InlineLabel menuTitle;
	
	// Set the format of datepicker.
	DateTimeFormat dateFormat = DateTimeFormat
			.getFormat(ViewConstants.date_format_chinese);

	interface DockWidgetBinder extends UiBinder<Widget, DockWidget> {
	}

	private static DockWidgetBinder uiBinder = GWT
			.create(DockWidgetBinder.class);

	public DockWidget() {
		initWidget(uiBinder.createAndBindUi(this));
		init();
	}

	@Override
	public Widget asWidget() {
		return this;
	}

	@Override
	public HasClickHandlers getlogBtn() {
		return logBtn;
	}
 String styleOn="";
 String styleNo="";
	private void init() {
		  styleOn=btnRewardItem.getStyleName();
		  styleNo=btnEmail.getStyleName();
		btnReward.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				btnReward.setStyleName(styleOn);
				btnEmail.setStyleName(styleNo);
				btnGb.setStyleName(styleNo);
				btnRewardItem.setStyleName(styleNo);
				btnStaff.setStyleName(styleNo);
				btnSetting.setStyleName(styleNo);
				btnGift.setStyleName(styleNo);
			}
		});
		btnEmail.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				btnReward.setStyleName(styleNo);
				btnEmail.setStyleName(styleOn);
				btnGb.setStyleName(styleNo);
				btnRewardItem.setStyleName(styleNo);
				btnStaff.setStyleName(styleNo);
				btnSetting.setStyleName(styleNo);
				btnGift.setStyleName(styleNo);
			}
		});
		btnGb.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				btnReward.setStyleName(styleNo);
				btnEmail.setStyleName(styleNo);
				btnGb.setStyleName(styleOn);
				btnRewardItem.setStyleName(styleNo);
				btnStaff.setStyleName(styleNo);
				btnSetting.setStyleName(styleNo);
				btnGift.setStyleName(styleNo);
			}
		});
		btnRewardItem.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				btnReward.setStyleName(styleNo);
				btnEmail.setStyleName(styleNo);
				btnGb.setStyleName(styleNo);
				btnRewardItem.setStyleName(styleOn);
				btnStaff.setStyleName(styleNo);
				btnSetting.setStyleName(styleNo);
				btnGift.setStyleName(styleNo);
			}
		});
		btnStaff.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				btnReward.setStyleName(styleNo);
				btnEmail.setStyleName(styleNo);
				btnGb.setStyleName(styleNo);
				btnRewardItem.setStyleName(styleNo);
				btnStaff.setStyleName(styleOn);
				btnSetting.setStyleName(styleNo);
				btnGift.setStyleName(styleNo);
			}
		});
		btnSetting.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				btnReward.setStyleName(styleNo);
				btnEmail.setStyleName(styleNo);
				btnGb.setStyleName(styleNo);
				btnRewardItem.setStyleName(styleNo);
				btnStaff.setStyleName(styleNo);
				btnGift.setStyleName(styleNo);
				btnSetting.setStyleName(styleOn);
			}
		});
		btnGift.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				btnReward.setStyleName(styleNo);
				btnEmail.setStyleName(styleNo);
				btnGb.setStyleName(styleNo);
				btnRewardItem.setStyleName(styleNo);
				btnStaff.setStyleName(styleNo);
				btnSetting.setStyleName(styleNo);
				btnGift.setStyleName(styleOn);
			}
		});
	}

	@Override
	public DockLayoutPanel getDock() {
		return dock;
	}

	@Override
	public Panel getMenu() {
		return menu;
	}

	@Override
	public void setMessage(String userName) {
		String time = dateFormat.format(new Date());
		String msg = "欢迎你，" + userName + "！今天是:" + time;
		message.setText(msg);
	}

	@Override
	public HasClickHandlers getBtnEmail() {
		return btnEmail;
	}

	@Override
	public void setMenu(Panel panel) {
		menu.clear();

	}

	@Override
	public HasClickHandlers getBtnGb() {
		return btnGb;
	}

	@Override
	public void setMenuTitle(String title) {
		menuTitle.setText(title);
	}

	@Override
	public HasClickHandlers getBtnReward() {
		return btnReward;
	}

	@Override
	public HasClickHandlers getBtnRewardItem() {
		return btnRewardItem;
	}

	@Override
	public HasClickHandlers getBtnStaff() {
		return btnStaff;
	}

	@Override
	public HasClickHandlers getBtnSetting() {
		return btnSetting;
	}

	@Override
	public HasClickHandlers getBtnGift() {
		return btnGift;
	}

	@Override
	public HasClickHandlers getBtnCollection() {
		return collectionBtn;
	}

}
