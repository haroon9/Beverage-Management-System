package de.uniba.dsg.dsam.backend.beans;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.EJB;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;

import de.uniba.dsg.dsam.model.CustomerOrder;
import de.uniba.dsg.dsam.persistence.SalesManagement;

/**
 * @author Haroon
 * @Email haroon.gul@stud.uni-bamberg.de
 */

@MessageDriven(mappedName = "BeverageStoreQueue", 
		activationConfig = {@ActivationConfigProperty (
				propertyName = "destinationType", propertyValue = "javax.jms.Queue")
				
		})
public class OrderMessageDrivenBean implements MessageListener {

	@EJB
	SalesManagement sMng;
	
    public OrderMessageDrivenBean() {}

    // receives message object which contains the order
    public void onMessage(Message message) {
    	
    	if(message == null) {
    		return;
    	}
    	
    	if(message instanceof ObjectMessage) {
    		try {
    			Object o = ((ObjectMessage)message).getObject();
    			if(validate(o))
    			{
    				sMng.createOrder((CustomerOrder)o);
    			}
    		} catch (JMSException e) {
    			
    		}
    	}
    }
    
    // validate the object if the object is an order or not
    private boolean validate(Object o) {
    	
    	if(o == null || !(o instanceof CustomerOrder)) {
    		
    		return false;
    	}
    	CustomerOrder customerOrder = (CustomerOrder)o;
    	if(customerOrder.getOrderItems().size() < 0) {
    		return false;
    	}
    	else {
    		return true;
    	}
    }
}
