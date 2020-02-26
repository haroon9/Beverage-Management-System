package de.uniba.dsg.dsam.model;

import java.util.ArrayList;
import java.util.List;

public class Revenue {
	
	private List<Double> allOrdersRevenue;
	
	public Revenue() {
		this.allOrdersRevenue = new ArrayList<Double>();
	}
	
	public List<Double> getAllOrdersRevenue() {
		return allOrdersRevenue;
	}

	public void setAllOrdersRevenue(List<Double> allOrdersRevenue) {
		this.allOrdersRevenue = allOrdersRevenue;
	}
	
	public void addOrderRevenue(Double revenue) {
		this.allOrdersRevenue.add(revenue);
	}
}
