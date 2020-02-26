package de.uniba.dsg.dsam.client;

import java.io.IOException;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import de.uniba.dsg.dsam.model.IncentiveDTO;
import de.uniba.dsg.dsam.persistence.IncentiveManagement;

/**
 * @author Haroon
 * @Email haroon.gul@stud.uni-bamberg.de
 */

public class EditIncentiveServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	public static final Logger logger = Logger.getLogger(EditIncentiveServlet.class.getName());
	
	@EJB
	IncentiveManagement inc;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		if(request.getParameter("inc_id") != null) {
			int id = Integer.valueOf(request.getParameter("inc_id"));
			
			IncentiveDTO incentiveDTO = inc.getIncentive(id);
			
			request.setAttribute("inc", incentiveDTO);
		}
		
		request.getRequestDispatcher("/editincentive.jsp").forward(request, response);
	}
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int id = Integer.valueOf(request.getParameter("inc_id"));
		String incentiveType = request.getParameter("inc_type");
		String incentiveName = request.getParameter("inc_name");
		
		inc.updateIncentive(id, incentiveType, incentiveName);
		
		response.sendRedirect("/frontend/incentives");
	}

}
