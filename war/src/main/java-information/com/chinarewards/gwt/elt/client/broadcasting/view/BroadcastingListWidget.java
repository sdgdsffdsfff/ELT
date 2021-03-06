package com.chinarewards.gwt.elt.client.broadcasting.view;


import java.util.Date;

import com.chinarewards.gwt.elt.client.broadcasting.presenter.BroadcastingListPresenter.BroadcastingListDisplay;
import com.chinarewards.gwt.elt.client.view.constant.ViewConstants;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.InlineLabel;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.user.datepicker.client.DateBox;

public class BroadcastingListWidget extends Composite implements BroadcastingListDisplay {

	@UiField
	TextBox createUser;
	@UiField
	ListBox status;
	@UiField
	ListBox broadcastType;
	
	@UiField
	Button addBtn;

	@UiField
	Button searchBtn;
	
	@UiField
	InlineLabel dataCount;
	@UiField
	Panel resultPanel;
	@UiField
	Panel resultpage;
	@UiField
	Panel breadCrumbs;
	
	@UiField
	DateBox broadcastingTime;
	@UiField
	DateBox broadcastingTimeEnd;
	@UiField
	ListBox pageNumber;
	
	DateTimeFormat dateFormat = DateTimeFormat
			.getFormat(ViewConstants.date_format);
	

	private static BroadcastingListWidgetUiBinder uiBinder = GWT
			.create(BroadcastingListWidgetUiBinder.class);

	interface BroadcastingListWidgetUiBinder extends
			UiBinder<Widget, BroadcastingListWidget> {

	}

	public BroadcastingListWidget() {
		initWidget(uiBinder.createAndBindUi(this));
		broadcastingTime.setFormat(new DateBox.DefaultFormat(dateFormat));
		broadcastingTimeEnd.setFormat(new DateBox.DefaultFormat(dateFormat));
		pageNumber.clear();
		pageNumber.addItem("10","10");
		pageNumber.addItem("20","20");
		pageNumber.addItem("50","50");
	}

	@Override
	public HasClickHandlers getSearchBtnClickHandlers() {
		return this.searchBtn;
	}


	@Override
	public HasClickHandlers getAddBtnClickHandlers() {
		return this.addBtn;
	}



	@Override
	public void setDataCount(String text) {
		dataCount.setText(text);
		
	}

	@Override
	public void setBreadCrumbs(Widget breadCrumbs) {
		this.breadCrumbs.clear();
		this.breadCrumbs.add(breadCrumbs);
		
	}

	@Override
	public Panel getResultPanel() {
		return this.resultPanel;
	}

	@Override
	public Panel getResultpage() {
		return this.resultpage;
	}

	@Override
	public void initStatus() {
		status.addItem("不限", "ALL");
		status.addItem("已广播", "HASBROADCAST");
		status.addItem("未广播", "NOTBROADCAST");
	}

	@Override
	public String getStatus() {
		return status.getValue(status.getSelectedIndex());
	}

	@Override
	public String getCreateUser() {
		return createUser.getValue();
	}

	@Override
	public Date getBroadcastingTime() {
		return broadcastingTime.getValue();
	}

	@Override
	public Date getBroadcastingTimeEnd() {
		return broadcastingTimeEnd.getValue();
	}
	@Override
	public ListBox getPageNumber() {
		return pageNumber;
	}

	@Override
	public void initBroadcastType() {
		broadcastType.addItem("不限", "ALL");
		broadcastType.addItem("公司广播", "COMPANYBROADCAST");
		broadcastType.addItem("员工广播", "STAFFBROADCAST");
		broadcastType.addItem("系统广播", "SYSBROADCAST");
		broadcastType.addItem("奖励广播", "REWARDBROADCAST");
		broadcastType.addItem("主题广播", "THEMEBROADCAST");
		broadcastType.addItem("其他广播", "OTHERBROADCAST");
	}

	@Override
	public String getBroadcastType() {
		return broadcastType.getValue(broadcastType.getSelectedIndex());
	}

	



}
