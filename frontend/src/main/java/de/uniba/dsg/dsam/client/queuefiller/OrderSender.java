package de.uniba.dsg.dsam.client.queuefiller;

import java.util.logging.Logger;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.ObjectMessage;
import javax.jms.Queue;
import javax.jms.Session;

import de.uniba.dsg.dsam.model.CustomerOrder;

/**
 * @author Haroon Gul
 * @email haroon.gul@stud.uni-bamberg.de
 * <p>
 * The class is for creating the connection factory and then creating the producer which sends the order to the consumer.
 */

@Stateless
public class OrderSender {

    private static final Logger logger = Logger.getLogger(OrderSender.class.getName());

    @Resource(mappedName = "BeverageStoreCF")
    private ConnectionFactory factory;

    @Resource(mappedName = "BeverageStoreQueue")
    private Queue queue;

    public void sendOrder(CustomerOrder order) {
        try {
            Connection connection = factory.createConnection();
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            MessageProducer messageProducer = session.createProducer(queue);
            ObjectMessage message = session.createObjectMessage(order);
            messageProducer.send(message);
            System.out.println("inside ordersender");
        } catch (JMSException jmsException) {
            logger.severe("Could not send order to Queue" + jmsException);
        }
    }
}
