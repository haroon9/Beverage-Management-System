package de.uniba.dsg.dsam.persistence;

import java.util.List;

import de.uniba.dsg.dsam.model.CustomerOrder;
import de.uniba.dsg.dsam.model.OrdersDTO;
import de.uniba.dsg.dsam.model.Revenue;

public interface SalesManagement {
	
	public void createOrder(CustomerOrder order);

	List<OrdersDTO> ordersWithIncentive();

	List<OrdersDTO> allOrders();

	List<OrdersDTO> ordersWithPromotionalGift();

	List<OrdersDTO> ordersWithTrialPackage();

	List<OrdersDTO> ordersWithoutIncentive();
	
	Revenue generateAllRevenue();

	Revenue generateRevenueWithoutIncentive();

	Revenue generateRevenueWithPromotion();

	Revenue generateRevenueWithTrialPackage();

	Revenue generateRevenueWithIncentive();
}
