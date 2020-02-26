package de.uniba.dsg.dsam.model;

import java.io.Serializable;
import java.util.List;

public final class IncentiveDTO implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private int id;
	private String type;
	private String name;
	
	private List<Beverage> beverages;

	boolean isAttached;

	public IncentiveDTO() {
		super();
	}
	
	public IncentiveDTO(int id, String type, String name) {
		this.id = id;
		this.type = type;
		this.name = name;
	}
	
	
	public IncentiveDTO(int id, String type, String name, List<Beverage> beverages) {
		this.id = id;
		this.type = type;
		this.name = name;
		this.beverages = beverages;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Beverage> getBeverages() {
		return beverages;
	}

	public void setBeverages(List<Beverage> beverages) {
		this.beverages = beverages;
	}

	public boolean isAttached() {
		return isAttached;
	}

	public void setAttached(boolean attached) {
		isAttached = attached;
	}
}
