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
import util.ParamUtil;

/**
 * Servlet implementation class InsertServlet
 */
@WebServlet("/InsertServlet")
public class InsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InsertServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		String productId = request.getParameter("productId");
		String productName = request.getParameter("productName");
		String price = request.getParameter("price");
		String categoryId = request.getParameter("categoryId");
		String description = request.getParameter("description");
		
		boolean error = false;
		
		if (ParamUtil.isNullOrEmpty(productId)) {
			request.setAttribute("idMsg", "商品IDは必須です");
			error = true;
		}
		
		if (ParamUtil.isNullOrEmpty(productName)) {
			request.setAttribute("nameMsg", "商品名は必須です");
			error = true;
		}
		
		if (ParamUtil.isNullOrEmpty(price)) {
			request.setAttribute("priceMsg", "単価は必須です");
			error = true;
		}
		
		if(error) {
			request.getRequestDispatcher("/insert.jsp").forward(request, response);
			return;
		}
		
		int pdId = Integer.parseInt(productId);
		int p = Integer.parseInt(price);
		int catId = Integer.parseInt(categoryId);
		
		ProductService productService = new ProductService();
		
		productService.productId(pdId);
		
		Product product = new Product(pdId, productName, p, catId, description);
		
		
		productService.register(product);
		
		if (productId != null) {
			request.setAttribute("pdMsg", "商品IDが重複しています");
			request.getRequestDispatcher("/insert.jsp").forward(request, response);
		}else {
		     request.setAttribute("product", product);
		     request.setAttribute("pdMsg", "登録が完了しました。");
		     request.getRequestDispatcher("/menu.jsp").forward(request, response);
		}
		
		
	}
}
