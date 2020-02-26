package de.uniba.dsg.dsam.client;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import de.uniba.dsg.dsam.persistence.SalesManagement;

/**
 * @author Haroon
 * @Email haroon.gul@stud.uni-bamberg.de
 */

public class BusinessReportServlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@EJB
	SalesManagement sMng;
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// add data for jsp presentation
		
		request.setAttribute("allOrders", sMng.allOrders());
		request.setAttribute("ordersWithIncentive", sMng.ordersWithIncentive());
		request.setAttribute("ordersWithoutIncentive", sMng.ordersWithoutIncentive());
		request.setAttribute("ordersWithPromotion", sMng.ordersWithPromotionalGift());
		request.setAttribute("ordersWithTrial", sMng.ordersWithTrialPackage());
		
		// add revenue for jsp presentation
		request.setAttribute("rev", sMng.generateAllRevenue());
		request.setAttribute("revenueWithIncentive", sMng.generateRevenueWithIncentive());
		request.setAttribute("revenueWithoutIncentive", sMng.generateRevenueWithoutIncentive());
		request.setAttribute("revenueWithPromotion", sMng.generateRevenueWithPromotion());
		request.setAttribute("revenueWithTrialPackage", sMng.generateRevenueWithTrialPackage());
		
		request.getRequestDispatcher("/businessreport.jsp").forward(request, response);
	}
}
