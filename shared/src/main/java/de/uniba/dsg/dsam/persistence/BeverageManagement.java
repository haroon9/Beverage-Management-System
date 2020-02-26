package de.uniba.dsg.dsam.persistence;

import java.util.List;

import de.uniba.dsg.dsam.model.Beverage;

public interface BeverageManagement {

	void create(String name, String manufacturer, int quantity, double price, String inc_id) throws de.uniba.dsg.dsam.persistence.exceptions.PersistenceExceptions;
	
    public List<Beverage> getAllBeverages();

    public void assignIncentive(String b_id, String inc_id) throws de.uniba.dsg.dsam.persistence.exceptions.PersistenceExceptions;
    
	public Beverage getBeverageById(int id);
	
	public void updateBeverage(int b_id, String name, String manufacturer, int quantity, Double price, String inc_id) throws de.uniba.dsg.dsam.persistence.exceptions.PersistenceExceptions;
}
