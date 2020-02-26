package de.uniba.dsg.dsam.client;

import java.io.IOException;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import de.uniba.dsg.dsam.persistence.BeverageManagement;

/**
 * @author Haroon
 * @Email haroon.gul@stud.uni-bamberg.de
 */

public class EditBeverageServlet extends HttpServlet{
	
	Logger logger = Logger.getLogger(EditBeverageServlet.class.getName());
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@EJB
	BeverageManagement bMng;
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int b_id = Integer.parseInt(request.getParameter("b_id").trim());
		request.setAttribute("beverage", bMng.getBeverageById(b_id));
		request.getRequestDispatcher("/editbeverage.jsp").forward(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		try {
			int b_id = Integer.parseInt(request.getParameter("b_id").trim());
			String name = request.getParameter("name").trim();
			String manufacturer = request.getParameter("manufacturer").trim();
			int quantity = Integer.parseInt(request.getParameter("quantity").trim());
			Double price = Double.parseDouble(request.getParameter("price").trim());
			
			String inc_id = request.getParameter("incentive");
			
			bMng.updateBeverage(b_id, name, manufacturer, quantity, price, inc_id);
			
		}catch (de.uniba.dsg.dsam.persistence.exceptions.PersistenceExceptions e) {
			logger.severe("Error:" + e);
		}
		
		response.sendRedirect("/frontend/beverages");
	}
}
