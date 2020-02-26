package de.uniba.dsg.dsam.backend.entities;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Version;

/**
 * @author Haroon
 * @Email haroon.gul@stud.uni-bamberg.de
 */

@Entity
public class CustomerOrderEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private int id;
	
	@Version
	private int version;
	
	private Date issueDate;
	
	private boolean existIncentive;
	
	private int quantity;
	
	private String bName;
	
	private double bPrice;
	
	private String incName;
	
	private String incType;
	
	
	@OneToMany(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
	private List<BeverageEntity> beverageEntities;

	public void addOrder(BeverageEntity beverageEntity) {
		this.beverageEntities.add(beverageEntity);
	}
	
	public List<BeverageEntity> getBeverageEntities() {
		return beverageEntities;
	}

	public void setBeverageEntities(List<BeverageEntity> beverageEntities) {
		this.beverageEntities = beverageEntities;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public Date getIssueDate() {
		return issueDate;
	}

	public void setIssueDate(Date issueDate) {
		this.issueDate = issueDate;
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
		if (!(obj instanceof CustomerOrderEntity))
			return false;
		CustomerOrderEntity other = (CustomerOrderEntity) obj;
		return id == other.id;
	}

	public boolean isUsedIncentive() {
		return existIncentive;
	}

	public void setExistIncentive(boolean existIncentive) {
		this.existIncentive = existIncentive;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getbName() {
		return bName;
	}

	public void setbName(String bName) {
		this.bName = bName;
	}

	public String getIncName() {
		return incName;
	}

	public void setIncName(String incName) {
		this.incName = incName;
	}

	public String getIncType() {
		return incType;
	}

	public void setIncType(String incType) {
		this.incType = incType;
	}

	public double getbPrice() {
		return bPrice;
	}

	public void setbPrice(double bPrice) {
		this.bPrice = bPrice;
	}

}
