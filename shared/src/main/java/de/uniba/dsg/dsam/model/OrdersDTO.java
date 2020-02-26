package de.uniba.dsg.dsam.model;

import java.util.Date;

public class OrdersDTO {
	
	private String b_name;
	private String b_manufacturer;
	private int b_qunatity;
	private Double b_price;
	
	private String inc_name;
	private String inc_type;

	private Date date;
	
	private Double totalRevenue;
	
	
	public Double getTotalRevenue() {
		return totalRevenue;
	}

	public void setTotalRevenue(Double totalRevenue) {
		this.totalRevenue = totalRevenue;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	
	public OrdersDTO() {
		// TODO Auto-generated constructor stub
	}
	
	public OrdersDTO(String b_name, String b_manufacturer, int b_quantity, Double b_price, String inc_name, String inc_type, int c_id) {
		this.b_name = b_name;
		this.b_manufacturer = b_manufacturer;
		this.b_qunatity = b_quantity;
		this.b_price = b_price;
		this.inc_name = inc_name;
		this.inc_type = inc_type;
	}
	
	public String getB_name() {
		return b_name;
	}

	public void setB_name(String b_name) {
		this.b_name = b_name;
	}

	public String getB_manufacturer() {
		return b_manufacturer;
	}

	public void setB_manufacturer(String b_manufacturer) {
		this.b_manufacturer = b_manufacturer;
	}

	public int getB_qunatity() {
		return b_qunatity;
	}

	public void setB_qunatity(int b_qunatity) {
		this.b_qunatity = b_qunatity;
	}

	public Double getB_price() {
		return b_price;
	}

	public void setB_price(Double b_price) {
		this.b_price = b_price;
	}

	public String getInc_name() {
		return inc_name;
	}

	public void setInc_name(String inc_name) {
		this.inc_name = inc_name;
	}

	public String getInc_type() {
		return inc_type;
	}

	public void setInc_type(String inc_type) {
		this.inc_type = inc_type;
	}
}
