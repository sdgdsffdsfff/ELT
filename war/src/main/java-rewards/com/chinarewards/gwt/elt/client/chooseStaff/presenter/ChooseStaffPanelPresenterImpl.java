package com.chinarewards.gwt.elt.client.chooseStaff.presenter;

import java.util.ArrayList;
import java.util.List;

import net.customware.gwt.dispatch.client.DispatchAsync;

import com.chinarewards.gwt.elt.client.core.Platform;
import com.chinarewards.gwt.elt.client.core.ui.DialogCloseListener;
import com.chinarewards.gwt.elt.client.mvp.BasePresenter;
import com.chinarewards.gwt.elt.client.mvp.EventBus;
import com.chinarewards.gwt.elt.client.rewardItem.dialog.ChooseStaffListDialog;
import com.chinarewards.gwt.elt.client.rewardItem.dialog.ChooseStaffWinDialog;
import com.chinarewards.gwt.elt.client.rewardItem.event.ChooseStaffEvent;
import com.chinarewards.gwt.elt.client.rewardItem.handler.ChooseStaffHandler;
import com.chinarewards.gwt.elt.client.rewards.model.OrganicationClient;
import com.chinarewards.gwt.elt.client.rewards.model.ParticipateInfoClient;
import com.chinarewards.gwt.elt.client.rewards.model.ParticipateInfoClient.SomeoneClient;
import com.chinarewards.gwt.elt.client.rewards.model.StaffClient;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.inject.Inject;
import com.google.inject.Provider;

public class ChooseStaffPanelPresenterImpl extends
		BasePresenter<ChooseStaffPanelPresenter.ChooseStaffPanelDisplay>
		implements ChooseStaffPanelPresenter {

	private final Provider<ChooseStaffListDialog> chooseStaffDialogProvider;
	// private final SessionManager sessionManager;
	private final DispatchAsync dispatcher;

	@Inject
	public ChooseStaffPanelPresenterImpl(EventBus eventBus,
			ChooseStaffPanelDisplay display,
			Provider<ChooseStaffListDialog> chooseStaffDialogProvider,
			DispatchAsync dispatcher) {
		super(eventBus, display);
		this.chooseStaffDialogProvider = chooseStaffDialogProvider;
		this.dispatcher = dispatcher;
		// this.sessionManager = sessionManager;, SessionManager sessionManager
	}

	public void bind() {

		registerHandler(display.getChooseStaffBtnClick().addClickHandler(
				new ClickHandler() {
					@Override
					public void onClick(ClickEvent arg0) {
						final ChooseStaffListDialog dialog = chooseStaffDialogProvider.get();
						dialog.setNominee(false, true, null);// The key is the
																// first
																// parameter(false).
						final HandlerRegistration registration = eventBus.addHandler(ChooseStaffEvent.getType(),
										new ChooseStaffHandler() {
											@Override
											public void chosenStaff(List<StaffClient> list) {
												for (StaffClient r : list) {
													System.out.println("ds=="+ r);
													if (!display.getSpecialTextArea().containsItem(r)) {
														display.getSpecialTextArea().addItem(r);
													}
												}
											}
										});

						Platform.getInstance().getSiteManager()
								.openDialog(dialog, new DialogCloseListener() {
									public void onClose(String dialogId,
											String instanceId) {
										registration.removeHandler();
									}
								});
					}
				}));
	}

	@Override
	public ParticipateInfoClient getparticipateInfo() {
		ParticipateInfoClient participateInfo = null;

		List<OrganicationClient> orgs = new ArrayList<OrganicationClient>();
		for (String orgId : display.getRealOrginzationIds()) {
			orgs.add(new OrganicationClient(orgId, ""));
		}
		participateInfo = new SomeoneClient(orgs);

		return participateInfo;
	}

}