package com.chinarewards.gwt.elt.client.budget.view;

import net.customware.gwt.dispatch.client.DispatchAsync;

import com.chinarewards.gwt.elt.client.budget.model.CorpBudgetVo;
import com.chinarewards.gwt.elt.client.budget.presenter.CorpBudgetPresenter.CorpBudgetDisplay;
import com.chinarewards.gwt.elt.client.mvp.ErrorHandler;
import com.chinarewards.gwt.elt.client.support.SessionManager;
import com.chinarewards.gwt.elt.client.view.constant.ViewConstants;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;

public class CorpBudgetWidget extends Composite implements CorpBudgetDisplay {

	// --------vo
	@UiField
	TextBox name;

	@UiField
	Button back;

	@UiField
	Button save;

	@UiField
	Panel breadCrumbs;

	DateTimeFormat dateFormat = DateTimeFormat
			.getFormat(ViewConstants.date_format);

	interface CorpBudgetWidgetBinder extends UiBinder<Widget, CorpBudgetWidget> {

	}

	private static CorpBudgetWidgetBinder uiBinder = GWT
			.create(CorpBudgetWidgetBinder.class);

	@Inject
	public CorpBudgetWidget(DispatchAsync dispatch, ErrorHandler errorHandler,
			SessionManager sessionManager) {
		initWidget(uiBinder.createAndBindUi(this));
		// indate.setFormat(new DateBox.DefaultFormat(dateFormat));
	}

	@Override
	public Widget asWidget() {
		return this;
	}

	@Override
	public void initEditCorpBudget(CorpBudgetVo giftVo) {
		// name.setText(giftVo.getName());
		// initTypeSelect("");
	}

	// private void initTypeSelect(String selectedValue) {
	// type.clear();
	// int selectIndex = 0;
	// int i = 0;
	// for (Entry<String, String> entry : CorpBudgetType.map.entrySet()) {
	// String keyValue = entry.getKey();
	// // System.out.println(entry.getKey() + ": " + entry.getValue());
	// type.addItem(entry.getValue(), entry.getKey());
	// if (selectedValue != null && StringUtil.trim(selectedValue) != ""
	// && StringUtil.trim(keyValue) != "") {
	// if (selectedValue.equals(keyValue)) {
	// selectIndex = i;
	// }
	// }
	// i++;
	// }
	// type.setSelectedIndex(selectIndex);
	// }

	@Override
	public void setBreadCrumbs(Widget breadCrumbs) {
		this.breadCrumbs.clear();
		this.breadCrumbs.add(breadCrumbs);
	}

	@Override
	public HasValue<String> getName() {
		return name;
	}

	@Override
	public void clear() {

	}

	@Override
	public HasValue<Boolean> getStatus() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public HasValue<Boolean> getDeleted() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public HasClickHandlers getUploadClick() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public HasClickHandlers getSaveClick() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public HasClickHandlers getBackClick() {
		// TODO Auto-generated method stub
		return null;
	}

}