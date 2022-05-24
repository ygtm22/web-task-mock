package servlet;

import java.io.IOException;
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
		
		 String categoryName = request.getParameter("categoryName"); 
		 String productName = request.getParameter("productName");
		 
		
		ProductService pdService = new ProductService();
		
		Product pdSelect = new Product(null,productName, null, categoryName);
		/*
		 * Product product = new Product(); Category category = new Category();
		 */
		
		List<Product> productList = pdService.find(pdSelect);
		
		if(!productList.isEmpty()) {
			request.setAttribute("productList", productList);
			request.getRequestDispatcher("/menu.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
