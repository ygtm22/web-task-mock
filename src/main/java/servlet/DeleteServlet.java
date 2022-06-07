package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entity.Product;
import service.ProductService;
import util.ParamUtil;

/**
 * Servlet implementation class DeleteServlet
 */
@WebServlet("/DeleteServlet")
public class DeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String productId = request.getParameter("productId");
		String productName = request.getParameter("productName");
		String price = request.getParameter("price");
		String categoryName = request.getParameter("categoryName");
		String description = request.getParameter("description");
		
		
		boolean error = false;
		
		if(ParamUtil.isNullOrEmpty(productId)) {
			request.setAttribute("pdMsg", "削除に失敗しました。");
			error = true;
		}
		
		if(error) {
			request.getRequestDispatcher("/detail.jsp").forward(request, response);
			return;
		}
		
		Integer pdId = Integer.parseInt(productId);
		Integer p = Integer.parseInt(price);
		//Integer catId = Integer.parseInt(categoryId);
		
		ProductService productService = new ProductService();
		
		productService.productId(pdId);
		
		Product product = new Product(pdId, productName, p, categoryName, description);
		
		productService.pdDelete(product);
		
		request.setAttribute("dlMsg", "削除に成功しました");
	    request.getRequestDispatcher("/menu.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
