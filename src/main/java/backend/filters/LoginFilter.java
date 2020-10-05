package backend.filters;

import java.io.IOException;


import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import backend.models.User;

/**
 * <h3>Login Filter</h3> This Class is function as a Web filter
 * each client the request to
 * pass to the Web Services (Paths Marks with " /REST/" ) have to pass this
 * filter before The Filter Checks if there is an active session (the
 * user is logged in to the system).
 * if True -the Client is Approved for
 * Web Services , If false - Exception is being throw.
*/
@WebFilter("/REST/*")
public class LoginFilter implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		HttpServletResponse httpServletResponse = (HttpServletResponse) response;

		HttpSession session = httpServletRequest.getSession(false);

		if (session != null && session.getAttribute("User") != null) {
			String[] userPath = httpServletRequest.getRequestURI().split("/");
			User userInfo = (User) session.getAttribute("User");
			String userType = userInfo.getClientType().toString();

			if (userPath[3].equalsIgnoreCase(userType + "WebService")) {
				chain.doFilter(request, response);
			} else {
				session.invalidate();
				httpServletResponse.sendError(401, "You are not Authorized For this Action.. Please re-Login");

			}
		} else {
			httpServletResponse.sendError(401, "Sorry,Time Out.. Please re-Login");

		}

	}

}
