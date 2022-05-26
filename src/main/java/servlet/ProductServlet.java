package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entity.Product;
import entity.Category;
import service.ProductService;
import util.ParamUtil;
import dbexam.ProductDao;

/**
 * Servlet implementation class loginServlet
 */
@WebServlet("/productServlet")
public class ProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProductServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		ProductService productService = new ProductService();
		String productName = request.getParameter("keyword");
		List<Product> pdList = (List<Product>) request.getAttribute(productName);
		
		
		if(ParamUtil.isNullOrEmpty(productName)) {
			productService.findAll();
			request.setAttribute("pdList", productService.findAll());
			request.getRequestDispatcher("/menu.jsp").forward(request, response);
		}else {
			pdList = productService.findByName(productName);
			
			if (pdList == null) {
				request.setAttribute("msg", "検索結果がありません");
				request.getRequestDispatcher("/menu.jsp").forward(request, response);
			}else {
				request.setAttribute("pdList", pdList);
				request.getRequestDispatcher("/menu.jsp").forward(request, response);
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
