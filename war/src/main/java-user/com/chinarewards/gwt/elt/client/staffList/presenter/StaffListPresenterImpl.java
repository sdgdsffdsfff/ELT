package com.chinarewards.gwt.elt.client.staffList.presenter;

import java.util.Comparator;

import net.customware.gwt.dispatch.client.DispatchAsync;

import com.chinarewards.gwt.elt.client.breadCrumbs.presenter.BreadCrumbsPresenter;
import com.chinarewards.gwt.elt.client.core.Platform;
import com.chinarewards.gwt.elt.client.core.view.constant.ViewConstants;
import com.chinarewards.gwt.elt.client.mvp.BasePresenter;
import com.chinarewards.gwt.elt.client.mvp.ErrorHandler;
import com.chinarewards.gwt.elt.client.mvp.EventBus;
import com.chinarewards.gwt.elt.client.staffAdd.plugin.StaffAddConstants;
import com.chinarewards.gwt.elt.client.staffList.dataprovider.StaffListViewAdapter;
import com.chinarewards.gwt.elt.client.staffList.model.StaffListClient;
import com.chinarewards.gwt.elt.client.staffList.model.StaffListCriteria;
import com.chinarewards.gwt.elt.client.staffList.model.StaffListCriteria.StaffStatus;
import com.chinarewards.gwt.elt.client.support.SessionManager;
import com.chinarewards.gwt.elt.client.ui.HyperLinkCell;
import com.chinarewards.gwt.elt.client.widget.EltNewPager;
import com.chinarewards.gwt.elt.client.widget.EltNewPager.TextLocation;
import com.chinarewards.gwt.elt.client.widget.GetValue;
import com.chinarewards.gwt.elt.client.widget.ListCellTable;
import com.chinarewards.gwt.elt.client.widget.Sorting;
import com.chinarewards.gwt.elt.client.win.Win;
import com.google.gwt.cell.client.FieldUpdater;
import com.google.gwt.cell.client.TextCell;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.inject.Inject;

public class StaffListPresenterImpl extends
		BasePresenter<StaffListPresenter.StaffListDisplay> implements
		StaffListPresenter {

	private final DispatchAsync dispatch;
	private final SessionManager sessionManager;
	private final Win win;
	final ErrorHandler errorHandler;
	EltNewPager pager;
	ListCellTable<StaffListClient> cellTable;
	StaffListViewAdapter listViewAdapter;

	private final BreadCrumbsPresenter breadCrumbs;
	@Inject
	public StaffListPresenterImpl(EventBus eventBus,
			StaffListDisplay display, DispatchAsync dispatch,
			SessionManager sessionManager,Win win,BreadCrumbsPresenter breadCrumbs,ErrorHandler errorHandler) {
		super(eventBus, display);
		this.dispatch = dispatch;
		this.sessionManager = sessionManager;
		this.errorHandler=errorHandler;
		this.win=win;
		this.breadCrumbs=breadCrumbs;
	}

	@Override
	public void bind() {
		breadCrumbs.loadListPage();
		display.setBreadCrumbs(breadCrumbs.getDisplay().asWidget());
		init();
		registerHandler(display.getSearchBtnClickHandlers().addClickHandler(
				new ClickHandler() {
					@Override
					public void onClick(ClickEvent event) {
				//		doSearch();
					}
				}));

		registerHandler(display.getAddStaffBtnClickHandlers().addClickHandler(
				new ClickHandler() {
					@Override
					public void onClick(ClickEvent event) {
						Platform.getInstance()
						.getEditorRegistry()
						.openEditor(
								StaffAddConstants.EDITOR_STAFFADD_SEARCH,
								"EDITOR_STAFFADD_SEARCH_DO_ID", null);
					}
				}));
		registerHandler(display.getSynchronousStaffBtnClickHandlers().addClickHandler(
				new ClickHandler() {
					@Override
					public void onClick(ClickEvent event) {
						win.alert("同步");
					}
				}));
	}
	
	private void init() {	
		display.initStaffStatus();
		buildTable();
		doSearch();
	}

	private void buildTable() {
		// create a CellTable
		cellTable = new ListCellTable<StaffListClient>();

		initTableColumns();
		pager = new EltNewPager(TextLocation.CENTER);
		pager.setDisplay(cellTable);
		cellTable.setWidth(ViewConstants.page_width);
		cellTable.setPageSize(ViewConstants.per_page_number_in_dialog);
	//	cellTable.getColumn(0).setCellStyleNames("divTextLeft");
		display.getResultPanel().clear();
		display.getResultPanel().add(cellTable);
		display.getResultpage().clear();
		display.getResultpage().add(pager);
		
	}

	private void doSearch() {
		StaffListCriteria criteria = new StaffListCriteria();
		criteria.setStaffNameorNo(display.getStaffNameorNo().getValue());
		if(!"ALL".equals(display.getSttaffStatus()))
			criteria.setStaffStatus(StaffStatus.valueOf(display.getSttaffStatus()));
		

		listViewAdapter = new StaffListViewAdapter(dispatch, criteria,
				errorHandler, sessionManager,display);
		listViewAdapter.addDataDisplay(cellTable);

	}

	private void initTableColumns() {
		Sorting<StaffListClient> ref = new Sorting<StaffListClient>() {
			@Override
			public void sortingCurrentPage(Comparator<StaffListClient> comparator) {
				// listViewAdapter.sortCurrentPage(comparator);
			}

			@Override
			public void sortingAll(String sorting, String direction) {
				listViewAdapter.sortFromDateBase(sorting, direction);

			}
		};


		cellTable.addColumn("员工编号", new TextCell(),
				new GetValue<StaffListClient, String>() {
					@Override
					public String getValue(StaffListClient staff) {
						return staff.getStaffNo();
					}
				}, ref, "jobNo");
		cellTable.addColumn("姓名", new TextCell(),
				new GetValue<StaffListClient, String>() {
					@Override
					public String getValue(StaffListClient staff) {
						return staff.getStaffName();
					}
				}, ref, "name");
		cellTable.addColumn("所属部门", new TextCell(),
				new GetValue<StaffListClient, String>() {
					@Override
					public String getValue(StaffListClient staff) {
						return staff.getDepartmentName();
					}
				});
		cellTable.addColumn("职位", new TextCell(),
				new GetValue<StaffListClient, String>() {
					@Override
					public String getValue(StaffListClient staff) {
						return staff.getJobPosition();
					}
				}, ref, "jobPosition");
		cellTable.addColumn("电话", new TextCell(),
				new GetValue<StaffListClient, String>() {
					@Override
					public String getValue(StaffListClient staff) {
						return staff.getPhone();
					}
				}, ref, "phone");
		cellTable.addColumn("员工状态", new TextCell(),
				new GetValue<StaffListClient, String>() {
					@Override
					public String getValue(StaffListClient staff) {
						if(staff.getStaffStatus()!=null)
						return staff.getStaffStatus().getDisplayName();
						else
						return "未知";
					}
				});
		cellTable.addColumn("操作", new HyperLinkCell(),
				new GetValue<StaffListClient, String>() {
					@Override
					public String getValue(StaffListClient rewards) {
						return "查看";
					}
				}, new FieldUpdater<StaffListClient, String>() {

					@Override
					public void update(int index, final StaffListClient o,
							String value) {
						win.alert("待实现");
					}

				});
		cellTable.addColumn("操作", new HyperLinkCell(),
				new GetValue<StaffListClient, String>() {
					@Override
					public String getValue(StaffListClient rewards) {
						return "发送Email";
					}
				}, new FieldUpdater<StaffListClient, String>() {

					@Override
					public void update(int index, final StaffListClient o,
							String value) {
						win.alert("待实现");
					}

				});
	}

}