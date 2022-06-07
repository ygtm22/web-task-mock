package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entity.Product;
import service.ProductService;
import util.ParamUtil;

/**
 * Servlet implementation class UpdateServlet
 */
@WebServlet("/UpdateServlet")
public class UpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Integer id = ParamUtil.checkAndParseInt(request.getParameter("id"));
		Integer productId = ParamUtil.checkAndParseInt(request.getParameter("productId"));
		String productName = request.getParameter("productName");
		Integer price = ParamUtil.checkAndParseInt(request.getParameter("price"));
		Integer categoryId = ParamUtil.checkAndParseInt(request.getParameter("categoryId"));
		String description = request.getParameter("description");
		
		boolean error = false;
		
		if (productId == null) {
			request.setAttribute("idMsg", "商品IDは必須です");
			error = true;
		}
		
		if (ParamUtil.isNullOrEmpty(productName)) {
			request.setAttribute("nameMsg", "商品名は必須です");
			error = true;
		}
		
		if (price == null) {
			request.setAttribute("priceMsg", "単価は必須です");
			error = true;
		}
		
		if(error) {
			request.setAttribute("upMsg", "更新時にエラーが発生しました");
			request.getRequestDispatcher("/updateInput.jsp").forward(request, response);
			return;
		}
		HttpSession session = request.getSession();
		
		ProductService productService = new ProductService();
		
		Product beforeProduct = (Product) session.getAttribute("product");
		System.out.println("beforeProduct.getId() = " + beforeProduct.getId());
		Product product = new Product(id, productId, productName, price, categoryId, description);
		
		productService.pdUpdate(product, beforeProduct.getProductId());
		
		Product pd = productService.id(id);
		System.out.println("product.getProductId() = "+ product.getProductId());
		System.out.println("pd.getId() = "+ product.getId());
		
		if (product.getProductId() != null && pd.getId() != beforeProduct.getId()) {
			request.setAttribute("pdMsg", "商品IDが重複しています");
			request.getRequestDispatcher("/updateInput.jsp").forward(request, response);
		}else {
		     request.setAttribute("product", product);
		     request.setAttribute("pdMsg", "更新処理が完了しました。");
		     request.getRequestDispatcher("/menu.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
