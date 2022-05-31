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
		String pd = request.getParameter("keyword");
		//List<Product> list = new ArrayList<>();
		/*
		 * String productId = request.getParameter("productId"); int id =
		 * Integer.parseInt(productId); productService.findByCount(id);
		 */
		List<Product> pdList = (List<Product>) request.getAttribute(pd);
		
		//list.sort((p1, p2) -> p1.getPrice() >= p2.getPrice() ? 1 : -1); 
		
		if(ParamUtil.isNullOrEmpty(pd)) {
			productService.findAll();
			request.setAttribute("pdList", productService.findAll());
			//request.setAttribute("count", productId + "件");
			request.getRequestDispatcher("/menu.jsp").forward(request, response);
		}else {
			pdList = productService.findByName(pd);
			
			if (!pdList.isEmpty()) {
				request.setAttribute("pdList", pdList);
				//request.setAttribute("count", productId);
				request.getRequestDispatcher("/menu.jsp").forward(request, response);
			}else {
				request.setAttribute("listMsg", "検索結果がありません");
				//request.setAttribute("count", productId);
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
