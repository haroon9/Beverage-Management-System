package de.uniba.dsg.dsam.client.queuefiller;

import static de.uniba.dsg.dsam.client.EditIncentiveServlet.logger;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import de.uniba.dsg.dsam.model.Beverage;
import de.uniba.dsg.dsam.model.CustomerOrder;
import de.uniba.dsg.dsam.persistence.BeverageManagement;

public class OrderBeverageServlet extends HttpServlet {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @EJB
    BeverageManagement bMng;

    @EJB
    OrderSender orderSender;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

            request.setAttribute("beverageList", bMng.getAllBeverages());
            request.getRequestDispatcher("/beveragestoorder.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String b_id = request.getParameter("b_id").trim();
        int quantity = Integer.parseInt(request.getParameter("b_quantity").trim());
        
        if (quantity > 0) {
		    Beverage beverage = bMng.getBeverageById(Integer.parseInt(b_id));
		    List<Beverage> beverages = new ArrayList<Beverage>();
		    beverage.setQuantity(quantity);
		    beverages.add(beverage);

		    CustomerOrder customerOrder = new CustomerOrder();
		    customerOrder.setIssueDate(new Date());
		    customerOrder.setOrderItems(beverages);
		    orderSender.sendOrder(customerOrder);
		    response.sendRedirect("/frontend/beverages");
		}
    }
}
