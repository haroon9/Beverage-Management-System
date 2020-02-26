package de.uniba.dsg.dsam.model;

import java.io.Serializable;

public class Beverage implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
    private String name;
    private String manufacturer;
    private int quantity;
    private double price;
    
    private IncentiveDTO incentiveDTO;
    
    public Beverage() {
	}
    
	public Beverage(int id, String name, String manufacturer, int quantity, double price) {
		this.manufacturer = manufacturer;
		this.name = name;
		this.quantity = quantity;
		this.price = price;
		this.id = id;
	}
	
	public Beverage(int id, String name, String manufacturer, int quantity, double price, IncentiveDTO incentive) {
		this.id = id;
		this.manufacturer = manufacturer;
		this.name = name;
		this.quantity = quantity;
		this.price = price;
		this.incentiveDTO = incentive;
	}

	/**
	 * @return the manufacturer
	 */
	public String getManufacturer() {
		return manufacturer;
	}

	/**
	 * @param manufacturer the manufacturer to set
	 */
	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	/**
	 * @return the quantity
	 */
	public int getQuantity() {
		return quantity;
	}

	/**
	 * @param quantity the quantity to set
	 */
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the price
	 */
	public double getPrice() {
		return price;
	}

	/**
	 * @param price the price to set
	 */
	public void setPrice(double price) {
		this.price = price;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public IncentiveDTO getIncentiveDTO() {
		return incentiveDTO;
	}

	public void setIncentiveDTO(IncentiveDTO incentiveDTO) {
		this.incentiveDTO = incentiveDTO;
	}
}
