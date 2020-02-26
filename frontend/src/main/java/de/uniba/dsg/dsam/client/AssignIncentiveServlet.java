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

/**
 * Servlet implementation class AssignIncentive
 */
public class AssignIncentiveServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    Logger logger = Logger.getLogger(AssignIncentiveServlet.class.getName());

    @EJB
    BeverageManagement bMng;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int b_id = Integer.parseInt(request.getParameter("b_id"));
        request.setAttribute("beverage", bMng.getBeverageById(b_id));
        request.getRequestDispatcher("/assignincentive.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String b_id = request.getParameter("b_id").trim();
        //check if this is a good way to do it.
        try {
            if (request.getParameter("incentive").trim() != null && request.getParameter("incentive").trim().length() != 0) {
                String incentiveId = (request.getParameter("incentive").trim());
                bMng.assignIncentive(b_id, incentiveId);
            } else {
                bMng.assignIncentive(b_id, null);
            }
        } catch (de.uniba.dsg.dsam.persistence.exceptions.PersistenceExceptions e) {
            logger.severe("Error" + e);
            response.sendRedirect("/frontend/error");
        }
        response.sendRedirect("/frontend/beverages");
    }

}
