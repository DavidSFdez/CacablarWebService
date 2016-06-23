package uo.sdi.rest.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import uo.sdi.infrastructure.Factories;
import uo.sdi.model.User;

public class RestAuthenticationFilter implements javax.servlet.Filter {

    private final String AUTHENTICATION_HEADER = "Authorization";
    public final static String CURRENT_USER = "currentUserKEY";

    @Override
    public void destroy() {
    }

    @Override
    public void init(FilterConfig arg0) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
	    FilterChain filter) throws IOException, ServletException {

	if (request instanceof HttpServletRequest) {
	    HttpServletRequest httpServletRequest = (HttpServletRequest) request;

	    authCredentials: {
		String authCredentials = httpServletRequest
			.getHeader(AUTHENTICATION_HEADER);
		if (authCredentials == null)
		    break authCredentials;

		User user = authenticateBASIC(authCredentials);
		if (user == null)
		    break authCredentials;

		request.setAttribute(CURRENT_USER, user);
		filter.doFilter(request, response);
	    }

	    if (response instanceof HttpServletResponse) {
		HttpServletResponse httpServletResponse = (HttpServletResponse) response;
		httpServletResponse
			.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
	    }
	}
    }

    private User authenticateBASIC(String authCredentials) throws IOException {
	sun.misc.BASE64Decoder decoder = new sun.misc.BASE64Decoder();
	String auth = new String(decoder.decodeBuffer(authCredentials
		.split(" ")[1]));
	String name = auth.split(":")[0];
	String password = auth.split(":")[1];
	return Factories.services.getLoginService().verify(name, password);
    }
}