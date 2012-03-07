package com.chinarewards.gwt.elt.client.mailSave.presenter;

import net.customware.gwt.dispatch.client.DispatchAsync;

import com.chinarewards.gwt.elt.client.broadcastSave.dialog.StaffChooseOrganizationListDialog;
import com.chinarewards.gwt.elt.client.mail.model.MailVo;
import com.chinarewards.gwt.elt.client.mail.request.MailRequest;
import com.chinarewards.gwt.elt.client.mail.request.MailResponse;
import com.chinarewards.gwt.elt.client.mvp.BaseDialogPresenter;
import com.chinarewards.gwt.elt.client.mvp.ErrorHandler;
import com.chinarewards.gwt.elt.client.mvp.EventBus;
import com.chinarewards.gwt.elt.client.support.SessionManager;
import com.chinarewards.gwt.elt.client.util.StringUtil;
import com.chinarewards.gwt.elt.client.win.Win;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.inject.Inject;
import com.google.inject.Provider;

public class MailSavePresenterImpl extends
		BaseDialogPresenter<MailSavePresenter.MailSaveDisplay> implements
		MailSavePresenter {

	private final DispatchAsync dispatch;
	private final SessionManager sessionManager;
	private final Win win;
	final ErrorHandler errorHandler;
	String broadcastId = null;
	String staffId=null;

	private final Provider<StaffChooseOrganizationListDialog> chooseOrganizationDialogProvider;

	@Inject
	public MailSavePresenterImpl(
			EventBus eventBus,
			MailSaveDisplay display,
			DispatchAsync dispatch,
			SessionManager sessionManager,
			Win win,
			ErrorHandler errorHandler,
			Provider<StaffChooseOrganizationListDialog> chooseOrganizationDialogProvider) {
		super(eventBus, display);
		this.dispatch = dispatch;
		this.sessionManager = sessionManager;
		this.errorHandler = errorHandler;
		this.win = win;

		this.chooseOrganizationDialogProvider = chooseOrganizationDialogProvider;
	}

	@Override
	public void bind() {

	
		registerHandler(display.getSaveBtnClickHandlers().addClickHandler(
				new ClickHandler() {

					@Override
					public void onClick(ClickEvent event) {
						if (StringUtil.isEmpty(display.getTitle())) {
							win.alert("请填写邮件标题!");
							return;
						}
						if (StringUtil.isEmpty(display.getContent())) {
							win.alert("请填写邮件内容!");
							return;
						}

						MailVo mailvo = new MailVo();
						mailvo.setContent(display.getContent());
						mailvo.setStaffId(display.getStaffId());
						mailvo.setTitle(display.getTitle());
						MailRequest request = new MailRequest(mailvo,sessionManager.getSession());
						dispatch.execute(request,new AsyncCallback<MailResponse>() {

									@Override
									public void onFailure(Throwable t) {
										win.alert(t.getMessage());
									}

									@Override
									public void onSuccess(
											MailResponse resp) {
										win.alert("发送成功");
										display.setContent("");
										closeDialog();
									}
								});
					}
				}));

		
		registerHandler(display.getReturnBtnClickHandlers().addClickHandler(
				new ClickHandler() {

					@Override
					public void onClick(ClickEvent event) {
						closeDialog();

					}
				}));

	}


	@Override
	public void initBroadcastStaff(String staffId,String staffName) {
		this.staffId=staffId;
		display.setStaffId(staffId);
		display.setStaffName(staffName);
//		OrganicationClient client=new OrganicationClient();
//		client.setId(staffId);
//		client.setName(staffName);
//		client.setType(OrganType.STAFF);
//		display.getSpecialTextArea().addItem(client);
	}

	


}
