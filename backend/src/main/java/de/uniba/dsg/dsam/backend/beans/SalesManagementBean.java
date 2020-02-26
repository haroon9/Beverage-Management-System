package de.uniba.dsg.dsam.backend.beans;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;

import de.uniba.dsg.dsam.backend.entities.BeverageEntity;
import de.uniba.dsg.dsam.backend.entities.CustomerOrderEntity;
import de.uniba.dsg.dsam.model.Beverage;
import de.uniba.dsg.dsam.model.CustomerOrder;
import de.uniba.dsg.dsam.model.OrdersDTO;
import de.uniba.dsg.dsam.model.Revenue;
import de.uniba.dsg.dsam.persistence.SalesManagement;

/**
 * @author Haroon
 * @Email haroon.gul@stud.uni-bamberg.de
 */

@Stateless
@Remote(SalesManagement.class)
public class SalesManagementBean implements SalesManagement {

    @PersistenceContext(type = PersistenceContextType.TRANSACTION)
    EntityManager em;
    
    // create a new order and persist it in the db
    @Override
    public void createOrder(CustomerOrder order) {

        order.getOrderItems().forEach(item -> {
            updateBeverageQuantity(item.getId(), item.getQuantity());
        });

        CustomerOrderEntity customerOrderEntity = new CustomerOrderEntity();
        customerOrderEntity.setIssueDate(order.getIssueDate());
        customerOrderEntity.setExistIncentive(checkIfBeverageHasIncentive(order.getOrderItems()));
        customerOrderEntity.setQuantity(order.getOrderItems().get(0).getQuantity());
        customerOrderEntity.setBeverageEntities(toBeverageEntity(order.getOrderItems()));
        customerOrderEntity.setbName(order.getOrderItems().get(0).getName());
        customerOrderEntity.setbPrice(order.getOrderItems().get(0).getPrice());
        if(order.getOrderItems().get(0).getIncentiveDTO() != null) {
        	customerOrderEntity.setIncName(order.getOrderItems().get(0).getIncentiveDTO().getName());
            customerOrderEntity.setIncType(order.getOrderItems().get(0).getIncentiveDTO().getType());
        }
        em.persist(customerOrderEntity);
    }
    
    // update the quantity of a beverage
    public void updateBeverageQuantity(int b_id, int quantity) {
        BeverageEntity beverageEntity = em.find(BeverageEntity.class, b_id);
        if (beverageEntity != null) {
            beverageEntity.setQuantity(beverageEntity.getQuantity() - quantity);
            em.persist(beverageEntity);
        }
    }

    // check if a Beverage has an incentive attached
    public boolean checkIfBeverageHasIncentive(List<Beverage> beverages) {

        if (beverages != null && beverages.size() > 0) {
            for (Beverage b : beverages) {

                BeverageEntity beverageEntity = em.find(BeverageEntity.class, b.getId());
                if (beverageEntity.getIncentiveEntity() != null) {
                    return true;
                } else {
                    return false;
                }
            }
        }
        return false;
    }
    
    // get all orders entities with and without incentives
    public List<CustomerOrderEntity> getAllOrdersEntities() {
        @SuppressWarnings("unchecked")
        List<CustomerOrderEntity> customerOrderEntities = em.createNamedQuery("getAllOrders").getResultList();
        
        if (customerOrderEntities != null && customerOrderEntities.size() > 0) {
            return customerOrderEntities;
        } else {
            return new ArrayList<CustomerOrderEntity>();
        }
    }
    
    // get all orders with and without incentive for frontend
    @Override
    public List<OrdersDTO> allOrders(){
    	List<CustomerOrderEntity> orderEntities = getAllOrdersEntities();
    	if(orderEntities != null && orderEntities.size() > 0) {
    		return toOrdersDTO(orderEntities);
    	}
    	return new ArrayList<OrdersDTO>();
    }
    
    // all orders with no incentive
    @Override
    public List<OrdersDTO> ordersWithoutIncentive(){
    	List<CustomerOrderEntity> allOrders = getAllOrdersEntities();
    	List<CustomerOrderEntity> ordersWithoutIncentive = new ArrayList<CustomerOrderEntity>();
    	
    	if(allOrders != null && allOrders.size() > 0) {
    		allOrders.forEach(order -> {
    			if (!order.isUsedIncentive()) {
    						ordersWithoutIncentive.add(order);
				}
    		});
    		if (ordersWithoutIncentive.size() > 0 && ordersWithoutIncentive != null) {
    			return toOrdersDTO(ordersWithoutIncentive);
			}
    	}
    	else {
    		return new ArrayList<OrdersDTO>();
    	}
    	
    	return new ArrayList<OrdersDTO>();
    }
    
    // get all orders with incentives for frontend
    @Override
    public List<OrdersDTO> ordersWithIncentive(){
    	List<CustomerOrderEntity> customerOrderEntities = getAllOrdersEntities();
    	List<CustomerOrderEntity> orderEntities = new ArrayList<CustomerOrderEntity>();
    	if(customerOrderEntities != null && customerOrderEntities.size() > 0) {
    		for(CustomerOrderEntity entity : customerOrderEntities) {
        		if (entity.isUsedIncentive()) {
    				orderEntities.add(entity);
    			}
        	}
        	return toOrdersDTO(orderEntities);
    	}
    	else {
    		return new ArrayList<OrdersDTO>();
    	}
    }
    
    // get all orders with incentive type promotional Gift
    @Override
    public List<OrdersDTO> ordersWithPromotionalGift(){
    	List<CustomerOrderEntity> orderEntities = getAllOrdersEntities();
    	List<OrdersDTO> promotionalOrders = new ArrayList<OrdersDTO>();
    	if(orderEntities != null && orderEntities.size() > 0) {
    		orderEntities.forEach(order ->{
    			if(order.getBeverageEntities().get(0).getIncentiveEntity() != null) {
    				System.out.println(order.getBeverageEntities().get(0).getIncentiveEntity().getIncentiveType());
    				if(order.getIncType() != null && order.getIncType().equals("Promotional Gift") && order.isUsedIncentive()) {
    					OrdersDTO dto = new OrdersDTO();
        				dto.setDate(order.getIssueDate());
        				dto.setB_name(order.getbName());
        				dto.setB_manufacturer(order.getBeverageEntities().get(0).getManufacturer());
        				dto.setB_qunatity(order.getQuantity());
        				dto.setB_price(order.getbPrice());
        				if(order.getBeverageEntities().get(0).getIncentiveEntity() != null && order.isUsedIncentive()) {
        					dto.setInc_name(order.getIncName());
                			dto.setInc_type(order.getIncType());	
        				}
        				
            			dto.setTotalRevenue(order.getQuantity() * order.getbPrice());
            			promotionalOrders.add(dto);
    				}
    			}
    		});
    		return promotionalOrders;
    	}
		return new ArrayList<OrdersDTO>();
    }
    
    // get all orders with incentive type Trial Package
    @Override
    public List<OrdersDTO> ordersWithTrialPackage(){
    	List<CustomerOrderEntity> orderEntities = getAllOrdersEntities();
    	List<OrdersDTO> trialOrders = new ArrayList<OrdersDTO>();
    	if(orderEntities != null && orderEntities.size() > 0) {
    		orderEntities.forEach(order ->{
    			if(order.getBeverageEntities().get(0).getIncentiveEntity() != null) {
    				if(order.getIncType() != null && order.getIncType().equals("Trial Package") && order.isUsedIncentive()) {
    					OrdersDTO dto = new OrdersDTO();
        				dto.setDate(order.getIssueDate());
        				dto.setB_name(order.getbName());
        				dto.setB_manufacturer(order.getBeverageEntities().get(0).getManufacturer());
        				dto.setB_qunatity(order.getQuantity());
        				dto.setB_price(order.getbPrice());
        				if(order.getBeverageEntities().get(0).getIncentiveEntity() != null && order.isUsedIncentive()) {
        					dto.setInc_name(order.getIncName());
                			dto.setInc_type(order.getIncType());	
        				}
        				
            			dto.setTotalRevenue(order.getQuantity() * order.getbPrice());
            			trialOrders.add(dto);
    				}
    			}
    		});
    		
    		return trialOrders;
    	}
		return new ArrayList<OrdersDTO>();
    }
    
    //generate revenue for all the orders with and without incentives
    @Override
    public Revenue generateAllRevenue(){
    	
    	List<OrdersDTO> allOrders = allOrders();
    	
    	if(allOrders != null && allOrders.size() > 0) {
    		Revenue rev = new Revenue();
        	Double revenue = 0.0;
    		for(OrdersDTO order : allOrders) {
    			revenue = revenue + order.getTotalRevenue();
    		}
    		rev.addOrderRevenue(revenue);
    		
    		return rev;
    	}
    	return null;
    }
    
    //generate revenue of orders which has incentive
    @Override
    public Revenue generateRevenueWithIncentive() {
    	
    	List<OrdersDTO> orders = ordersWithIncentive();
    	
    	if(orders !=  null && orders.size() > 0) {
    		Revenue rev = new Revenue();
        	Double revenue = 0.0;
    		for(OrdersDTO order : orders) {
    			revenue = revenue + order.getTotalRevenue();
    		}
    		rev.addOrderRevenue(revenue);
    		return rev;
    	}
    	return null;
    }
    
    //generate revenue report for all orders without incentive
    @Override
    public Revenue generateRevenueWithoutIncentive() {
    	
    	List<OrdersDTO> orders = ordersWithoutIncentive();
    	
    	if(orders != null && orders.size() > 0) {
    		Revenue rev = new Revenue();
        	Double revenue = 0.0;
    		for(OrdersDTO order : orders) {
    			revenue = revenue + order.getTotalRevenue();
    		}
    		rev.addOrderRevenue(revenue);
    		return rev;
    	}
    	
    	return null;
    }
    
    // revenue of orders with incentive type Promotional Gift
    @Override
    public Revenue generateRevenueWithPromotion() {
    	
    	List<OrdersDTO> orders = ordersWithPromotionalGift();
    	
    	if(orders !=  null && orders.size() > 0) {
    		Revenue rev = new Revenue();
        	Double revenue = 0.0;
    		for(OrdersDTO order : orders) {
    			revenue = revenue + order.getTotalRevenue();
    		}
    		rev.addOrderRevenue(revenue);
    		return rev;
    	}
    	return null;
    }
    
    // revenue of orders with incentive type Trial Package
    @Override
    public Revenue generateRevenueWithTrialPackage() {
    	
    	List<OrdersDTO> orders = ordersWithTrialPackage();
    	
    	if(orders !=  null && orders.size() > 0) {
    		Revenue rev = new Revenue();
        	Double revenue = 0.0;
    		for(OrdersDTO order : orders) {
    			revenue = revenue + order.getTotalRevenue();
    		}
    		rev.addOrderRevenue(revenue);
    		return rev;
    	}
    	return null;
    }
    
    // convert orders entities to DTO's for frontend
    public List<OrdersDTO> toOrdersDTO(List<CustomerOrderEntity> orderEntities){
    	
    	List<OrdersDTO> ordersDTOs = new ArrayList<OrdersDTO>();
    	
    	if(orderEntities != null && orderEntities.size() > 0) {
    		orderEntities.forEach(order ->{
    			OrdersDTO dto = new OrdersDTO();
    			dto.setDate(order.getIssueDate());
    			dto.setB_name(order.getbName());
    			dto.setB_manufacturer(order.getBeverageEntities().get(0).getManufacturer());
    			dto.setB_qunatity(order.getQuantity());
    			dto.setB_price(order.getbPrice());
    			if(order.getBeverageEntities().get(0).getIncentiveEntity() != null && order.isUsedIncentive()) {
    				dto.setInc_name(order.getIncName());
        			dto.setInc_type(order.getIncType());
    			}
    		
    			dto.setTotalRevenue(order.getQuantity() * order.getbPrice());
    			ordersDTOs.add(dto);
    		});
    		return ordersDTOs;
    	}
    	return new ArrayList<OrdersDTO>();
    }
    
    // convert Beverage Object to Beverage entity for database persistence
    public List<BeverageEntity> toBeverageEntity(List<Beverage> beverages) {
        List<BeverageEntity> entities = new ArrayList<BeverageEntity>();

        if (beverages != null && beverages.size() > 0) {
            beverages.forEach(beverage -> {
                BeverageEntity entity = em.find(BeverageEntity.class, beverage.getId());
                entities.add(entity);
            });

            return entities;
        }
        return new ArrayList<BeverageEntity>();
    }
}
