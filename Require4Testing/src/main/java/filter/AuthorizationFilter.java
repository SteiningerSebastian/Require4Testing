//Inspired by BalusC, 12.12.2011 https://stackoverflow.com/questions/8480100/how-implement-a-login-filter-in-jsf
package filter;

import java.io.IOException;

import bean.controller.NavigationController;
import bean.entity.AuthSession;
import jakarta.faces.application.ResourceHandler;
import jakarta.inject.Inject;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class AuthorizationFilter implements Filter {
	private static final String AJAX_REDIRECT_XML = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>"
			+ "<partial-response><redirect url=\"%s\"></redirect></partial-response>";

	@Inject
	protected AuthSession authSession;

	@Inject
	protected NavigationController navController;

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws ServletException, IOException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;
		String loginURL = request.getContextPath() + "/login.xhtml";

		boolean loggedIn = authSession != null ? authSession.getIsAuthenticated() : false;
		boolean navigationAllowed = navController.navigationAllowed(request.getRequestURI());
		boolean loginRequest = request.getRequestURI().equals(loginURL);
		boolean resourceRequest = request.getRequestURI()
				.startsWith(request.getContextPath() + ResourceHandler.RESOURCE_IDENTIFIER + "/");
		boolean ajaxRequest = "partial/ajax".equals(request.getHeader("Faces-Request"));

		if ((loggedIn && navigationAllowed) || loginRequest || resourceRequest) {
			if (!resourceRequest) {
				response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
				response.setHeader("Pragma", "no-cache");
				response.setDateHeader("Expires", 0);
			}

			chain.doFilter(request, response);
		} else if (ajaxRequest) {
			response.setContentType("text/xml");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().printf(AJAX_REDIRECT_XML, loginURL);
		} else {
			response.sendRedirect(loginURL);
		}
	}

	@Override
	public void destroy() {

	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {

	}
}
