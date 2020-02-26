package de.uniba.dsg.dsam.client;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import de.uniba.dsg.dsam.persistence.BeverageManagement;
import de.uniba.dsg.dsam.persistence.IncentiveManagement;

import static de.uniba.dsg.dsam.client.EditIncentiveServlet.logger;

/**
 * @author Haroon
 * @Email haroon.gul@stud.uni-bamberg.de
 */

public class BeveragesServlet extends HttpServlet {


    private static final long serialVersionUID = 1L;


    @EJB
    BeverageManagement bMng;

    @EJB
    IncentiveManagement incMng;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
        req.setAttribute("beverageList", bMng.getAllBeverages());
        req.setAttribute("incentiveList", incMng.getAllIncentives());
        //redirect to jsp presentation
        req.getRequestDispatcher("/beverages.jsp").forward(req, res);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {

        String name = req.getParameter("name").trim();
        String manufacturer = req.getParameter("manufacturer").trim();
        int quantity = Integer.parseInt(req.getParameter("quantity"));
        double price = Double.parseDouble(req.getParameter("price"));

        //check if this is a good way to do it.
        String incentiveId = req.getParameter("incentive").trim();
        try {
            if (incentiveId != null) {
                bMng.create(name, manufacturer, quantity, price, incentiveId);
            } else {
                bMng.create(name, manufacturer, quantity, price, null);
            }
            res.sendRedirect("/frontend/beverages");
        } catch (de.uniba.dsg.dsam.persistence.exceptions.PersistenceExceptions e) {
            logger.severe("Error:" + e);
            res.sendRedirect("/frontend/error");
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse res) throws IOException {

    }
}
