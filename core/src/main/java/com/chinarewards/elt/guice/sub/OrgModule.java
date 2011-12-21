/**
 * 
 */
package com.chinarewards.elt.guice.sub;

import com.chinarewards.elt.dao.org.CorporationDao;
import com.chinarewards.elt.dao.org.DepartmentDao;
import com.chinarewards.elt.dao.org.DepartmentManagerDao;
import com.chinarewards.elt.dao.org.IndustryDao;
import com.chinarewards.elt.dao.org.OrganizationDao;
import com.chinarewards.elt.service.org.CorporationLogic;
import com.chinarewards.elt.service.org.CorporationService;
import com.chinarewards.elt.service.org.DepartmentLogic;
import com.chinarewards.elt.service.org.OrganizationLogic;
import com.chinarewards.elt.service.org.impl.CorporationLogicImpl;
import com.chinarewards.elt.service.org.impl.CorporationProcessor;
import com.chinarewards.elt.service.org.impl.CorporationServiceImpl;
import com.chinarewards.elt.service.org.impl.DepartmentLogicImpl;
import com.chinarewards.elt.service.org.impl.DeptmentProcessor;
import com.chinarewards.elt.service.org.impl.OrganizationLogicImpl;
import com.chinarewards.elt.service.org.impl.OrganizationProcessor;
import com.chinarewards.elt.service.org.impl.Organizationfactory;
import com.chinarewards.elt.service.org.impl.OrganizationfactoryImpl;
import com.chinarewards.elt.service.org.impl.StaffProcessor;
import com.google.inject.AbstractModule;
import com.google.inject.Singleton;
import com.google.inject.name.Names;

/**
 * Configure of organization module.
 * 
 * @author yanxin
 * @since 1.0
 */
public class OrgModule extends AbstractModule {

	@Override
	protected void configure() {
		bind(DepartmentDao.class).in(Singleton.class);
		bind(OrganizationDao.class).in(Singleton.class);
		bind(CorporationDao.class).in(Singleton.class);
		bind(IndustryDao.class).in(Singleton.class);
		bind(DepartmentManagerDao.class).in(Singleton.class);
		bind(CorporationLogic.class).to(CorporationLogicImpl.class).in(
				Singleton.class);
		bind(CorporationService.class).to(CorporationServiceImpl.class).in(
				Singleton.class);
		bind(OrganizationLogic.class).to(OrganizationLogicImpl.class).in(
				Singleton.class);
		bind(DepartmentLogic.class).to(DepartmentLogicImpl.class);
		bind(Organizationfactory.class).to(OrganizationfactoryImpl.class).in(Singleton.class);
		bind(OrganizationProcessor.class).annotatedWith(
				Names.named("StaffProcessor")).to(StaffProcessor.class);
		bind(OrganizationProcessor.class).annotatedWith(
				Names.named("DeptmentProcessor")).to(DeptmentProcessor.class);
		bind(OrganizationProcessor.class).annotatedWith(
				Names.named("CorporationProcessor")).to(
				CorporationProcessor.class);
	}

}
