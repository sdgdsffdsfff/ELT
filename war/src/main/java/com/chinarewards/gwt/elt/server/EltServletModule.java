package com.chinarewards.gwt.elt.server;

import net.customware.gwt.dispatch.server.guice.GuiceStandardDispatchServlet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.chinarewards.elt.guice.EltModule;
import com.chinarewards.gwt.elt.client.Elt;
import com.chinarewards.gwt.elt.server.login.LoginServiceImpl;
import com.chinarewards.gwt.elt.servlet.ExportGiftServlet;
import com.chinarewards.gwt.elt.server.order.OrderServlet;
import com.chinarewards.gwt.elt.sevlet.ExcelServlet;
import com.chinarewards.gwt.elt.sevlet.ImportStaffServlet;
import com.google.inject.Singleton;
import com.google.inject.servlet.ServletModule;

/**
 * 
 * 
 */
public class EltServletModule extends ServletModule {

	Logger logger = LoggerFactory.getLogger(getClass());

	@Override
	protected void configureServlets() {

		logger.info("Configuring servlet modules...");

		serve(Elt.GWT_MODULE_PATH + "/dispatch").with(
				GuiceStandardDispatchServlet.class);
		
		bind(InitServlet.class).in(Singleton.class);
		serve(Elt.GWT_MODULE_PATH + "/donottouchme").with(InitServlet.class);
		
		bind(ImportStaffServlet.class).in(Singleton.class);
		serve("/servlet.iss").with(ImportStaffServlet.class);
		
		
		bind(LoginServiceImpl.class).in(Singleton.class);
		serve(Elt.GWT_MODULE_PATH + "/loginService").with(LoginServiceImpl.class);
      
		bind(ExcelServlet.class).in(Singleton.class);
		serve(Elt.GWT_MODULE_PATH +"/servlet.export").with(ExcelServlet.class);
		

		bind(ExportGiftServlet.class).in(Singleton.class);
		serve(Elt.GWT_MODULE_PATH +"/servlet.exportGift").with(ExportGiftServlet.class);
		

		bind(OrderServlet.class).in(Singleton.class);
		serve(Elt.GWT_MODULE_PATH +"/orderServlet.export").with(OrderServlet.class);

		install(new EltModule());

		logger.info("Configuring servlet modules completed.");

	}

}
