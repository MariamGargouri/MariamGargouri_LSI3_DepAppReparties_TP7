package org.example;
import org.apache.activemq.broker.BrokerService;
public class ActiveMQBroker {
    public static void main(String[] args)
    {
        try{
        BrokerService brokerService = new BrokerService();
        brokerService.addConnector("top://0.0.0.0:61616"); // sprécification du port
        //démarrage du brooker
        brokerService.start();
    }
    catch(Exception e){
        e.printStackTrace();
    }
}}
