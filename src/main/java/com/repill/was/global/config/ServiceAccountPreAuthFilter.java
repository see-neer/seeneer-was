package com.repill.was.global.config;

import com.repill.was.global.enums.Headers;
import com.repill.was.member.entity.account.AccountId;
import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.preauth.AbstractPreAuthenticatedProcessingFilter;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Collections;

@Order(2)
@Component
@ConditionalOnBean(ServiceAuthProvider.class)
public class ServiceAccountPreAuthFilter extends AbstractPreAuthenticatedProcessingFilter {

	private static final String JWT_SCHEME_FORMAT = "Bearer ";
	private final JwtTokenProvider jwtTokenProvider;

	public ServiceAccountPreAuthFilter(ServiceAuthProvider authenticationProvider, JwtTokenProvider jwtTokenProvider) {
		this.jwtTokenProvider = jwtTokenProvider;
		setCheckForPrincipalChanges(true);
		setAuthenticationManager(new ProviderManager(Collections.singletonList(authenticationProvider)));
	}

	private boolean isValidTokenFormat(String token) {
		return token != null && token.startsWith(JWT_SCHEME_FORMAT);
	}

	private String resolveToken(HttpServletRequest req) {
		return req.getHeader(Headers.AUTHORIZATION.getKey());
	}

	@Override
	protected Object getPreAuthenticatedPrincipal(HttpServletRequest httpServletRequest) {

		String token = resolveToken(httpServletRequest);
		if(!isValidTokenFormat(token)) { return null; }
		token = token.replace(JWT_SCHEME_FORMAT, "");
		if (!jwtTokenProvider.validateToken(token)) {
			// todo refresh token 으로 로그인 하는 API를 호출하기위한 exception 필요
			return null;
		}
		Long accountId = Long.parseLong(jwtTokenProvider.getUserId(token));
		String role = jwtTokenProvider.getUserRoles(token);
		return new ServiceAccountPreAuthenticationPrincipal(new AccountId(accountId), role);
	}

	@Override
	protected Object getPreAuthenticatedCredentials(HttpServletRequest httpServletRequest) {
		return StringUtils.EMPTY;
	}

	@Override
	protected boolean principalChanged(HttpServletRequest request, Authentication currentAuthentication) {
		if (currentAuthentication instanceof ServiceAuthenticationToken) {
			return false;
		}

		return super.principalChanged(request, currentAuthentication);
	}
}
