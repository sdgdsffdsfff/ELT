package com.chinarewards.gwt.elt.server.login;

import net.customware.gwt.dispatch.server.ExecutionContext;
import net.customware.gwt.dispatch.shared.DispatchException;

import com.chinarewards.elt.model.user.UserSessionVo;
import com.chinarewards.elt.service.user.UserService;
import com.chinarewards.gwt.elt.client.login.TokenValidRequest;
import com.chinarewards.gwt.elt.client.login.TokenValidResponse;
import com.chinarewards.gwt.elt.server.BaseActionHandler;
import com.google.inject.Inject;

public class TokenValidActionHandler extends
		BaseActionHandler<TokenValidRequest, TokenValidResponse> {

	UserService userService;
	
	@Inject
	public TokenValidActionHandler(UserService userService)
	{
		this.userService=userService;
	}
	@Override
	public Class<TokenValidRequest> getActionType() {
		return TokenValidRequest.class;
	}

	@Override
	public TokenValidResponse execute(TokenValidRequest action, ExecutionContext context)
			throws DispatchException {
		TokenValidResponse tokenRep=new TokenValidResponse();
		UserSessionVo userSessionVo=userService.tokenVaild(action.getToken());
		tokenRep.setCorporationId(userSessionVo.getCorporationId());
		tokenRep.setLoginName(userSessionVo.getUsername());
		tokenRep.setToken(userSessionVo.getId());
	//	tokenRep.setUserRoles(userSessionVo.getUserRoles());
		
		return tokenRep;
	}

	@Override
	public void rollback(TokenValidRequest action, TokenValidResponse result,
			ExecutionContext context) throws DispatchException {

	}

}
