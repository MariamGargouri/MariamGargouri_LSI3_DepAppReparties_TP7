package org.example;
import org.apache.activemq.ActiveMQConnectionFactory;
import javax.jms.*;

public class Consumer {
    public static void main(String[] args)
    {   try
    {//Etablissement d'une connexion au broker ActiveMQ
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://localhost:61616");
        Connection connection = connectionFactory.createConnection();
        //je lance la connection
        connection.start();

        //Création d'une session
        Session session=connection.createSession(true, Session.AUTO_ACKNOWLEDGE);
        Destination destination=session.createTopic("my topic.topic");

        //Création du MessageConsumer
        MessageConsumer consumer= session.createConsumer(destination);

        consumer.setMessageListener(new MessageListener() {
        @Override
            public void onMessage (Message message)
            {
                if (message instanceof TextMessage)
                {
                    TextMessage textMassage = (TextMessage) message;
                    try
                    {
                        System.out.println("Message reçu :" + textMassage.getText());
                    }
                    catch (Exception e){e.printStackTrace();}
                }
            }
        });
    }catch(Exception e){e.printStackTrace();}
    }
}

