package de.uniba.dsg.dsam.client;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.Logger;

/**
 * @author Haroon
 * @Email haroon.gul@stud.uni-bamberg.de
 */

public class IncentivesServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private static final Logger logger = Logger.getLogger(IncentivesServlet.class.getName());

    @EJB
    de.uniba.dsg.dsam.persistence.IncentiveManagement incMng;

    /**
     * GET-Method for the incentives, used to load all Incentives to jsp
     *
     * @param request  the request
     * @param response the response
     * @throws ServletException internal servlet errors
     * @throws IOException      internal IO errors
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // add all the incentives to jsp
        request.setAttribute("incentiveList", incMng.getAllIncentives());
        request.getRequestDispatcher("/incentives.jsp").forward(request, response);
    }

    /**
     * Method to create incentives and save them to the database
     *
     * @param request  the request
     * @param response the response
     * @throws IOException internal IO errors
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String name = request.getParameter("name").trim();
        String type = request.getParameter("type").trim();
        try {
            incMng.create(type, name);
        } catch (de.uniba.dsg.dsam.persistence.exceptions.PersistenceExceptions e) {
            logger.severe("Error creating Incentive" + e);
            response.sendRedirect("/frontend/error");
        }
        response.sendRedirect("/frontend/incentives");
    }

    /**
     * Method to delete incentives using the Management Bean for error Handling.
     *
     * @param request  the request
     * @param response the response
     * @throws ServletException internal servlet errors
     * @throws IOException      internal IO errors
     */
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("inc_id"));
        try {
            incMng.deleteIncentive(id);
        } catch (de.uniba.dsg.dsam.persistence.exceptions.PersistenceExceptions e) {
            logger.severe("Error deleting Incentive: " + e);
            response.sendRedirect("/frontend/error");
        }
    }
}
