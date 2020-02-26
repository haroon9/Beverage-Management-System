package de.uniba.dsg.dsam.backend.entities;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Version;


/**
 * 
 * @author Haroon
 * @Email haroon.gul@stud.uni-bamberg.de
 * 
 */

@Entity
public class BeverageEntity implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private int id;
	
	private String name;
	private String manufacturer;
	private int quantity;
	private double price;
	
	//For concurrent access @version field must be used 
	//The version attribute is sent to the client and is merged back on the server
	//If the user wants to edit an entity the version attribute will be checked with the version attribute
	//Which the client sent with the version attribute on the server side. If in the meantime another user
	//modifies the same entity an OptimisticLockException will be thrown.
	@Version
	private int version;
	
	/*
	 * Need incentive here because with each beverage we can assign or remove an incentive.
	 * Fetch type is LAZY to delay the initialization of the relationship. We can use EAGER 
	 * fetch too but it will load all the relations at once which is a performance issue.
	 */
	@ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
	private IncentiveEntity incentiveEntity;
	
	public BeverageEntity() {
		
	}
	
	public BeverageEntity(String name, String manufacturer, int quantity, Double price, IncentiveEntity entity) {
		this.name = name;
		this.manufacturer = manufacturer;
		this.quantity = quantity;
		this.price = price;
		this.incentiveEntity = entity;
	}
	
	public BeverageEntity(String name, String manufacturer, int quantity, Double price) {
		this.name = name;
		this.manufacturer = manufacturer;
		this.quantity = quantity;
		this.price = price;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}
	
	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public IncentiveEntity getIncentiveEntity() {
		return incentiveEntity;
	}

	public void setIncentiveEntity(IncentiveEntity incentiveEntity) {
		this.incentiveEntity = incentiveEntity;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof BeverageEntity))
			return false;
		BeverageEntity other = (BeverageEntity) obj;
		if (id != other.id)
			return false;
		return true;
	}
}
