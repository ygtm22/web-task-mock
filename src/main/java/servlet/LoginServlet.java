package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entity.Product;
import service.ProductService;
import entity.User;
import service.UserService;
import util.ParamUtil;

/**
 * Servlet implementation class loginServlet
 */
@WebServlet("/loginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		String id = request.getParameter("loginId");
		String pass = request.getParameter("pass");
		
		boolean error = false;
		
		if (ParamUtil.isNullOrEmpty(id)) {
			request.setAttribute("idMsg", "IDは必須です。");
			error = true;
		}
		
		if (ParamUtil.isNullOrEmpty(pass)) {
			request.setAttribute("passMsg", "PASSは必須です。");
			error = true;
		}
		
		if(error) {
			request.getRequestDispatcher("/index.jsp").forward(request, response);
			return;
		}
		
		UserService userService = new UserService();
		User user = userService.authentication(id, pass);
		
		if (user != null) {
			request.setAttribute("user", user);
			request.getRequestDispatcher("/menu.jsp").forward(request, response);
		}else {
			request.setAttribute("msg", "IDまたはPASSが不正です");
			request.getRequestDispatcher("/index.jsp").forward(request, response);
		}
	}
}
