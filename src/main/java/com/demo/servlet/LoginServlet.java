package com.demo.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.demo.domain.User;
import com.demo.service.LoginService;

public class LoginServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse response)
			throws ServletException, IOException {

		if (req.getRequestURI().contains("index")) {
			req.getRequestDispatcher("/WEB-INF/index.html").forward(req,
					response);
			return;
		}

		String name = req.getParameter("username");
		String pwd = req.getParameter("password");

		User user = new User(name, pwd);

		LoginService loginService = new LoginService();

		if (loginService.isIegal(user)) {
			// 跳转登陆成功
			response.setContentType("text/html;charset=GB18030");

			// 返回html页面
			response.getWriter().println("<html>");
			response.getWriter().println("<head>");
			response.getWriter().println("<title>登录信息</title>");
			response.getWriter().println("</head>");
			response.getWriter().println("<body>");
			response.getWriter().println("欢迎【" + name + "】用户登录成功！！！");
			response.getWriter().println("</body>");
			response.getWriter().println("</html>");

		} else {
			// 失败页面
			response.setContentType("text/html;charset=GB18030");	
			response.getWriter().println("<h1>用户名或者密码错误</h1>");
			response.getWriter().println("<a href="+"http://127.0.0.1:8080/LoginServlet/index.html"+">点击返回登录页面</a>");
		}

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doGet(req, resp);
	}

}
