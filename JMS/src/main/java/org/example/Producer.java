package org.example;
import org.apache.activemq.ActiveMQConnectionFactory;
import javax.jms.*;

public class Producer {
    public static void main(String[] args)
    {   try
    {
        //connection sur le brooker
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://localhost:61616"); //localhost adresse ip de la machine qui a le brooker (serveur) / tcp le protocol utilisé / 61616 numéro de port du brooker
        //creation d'une connection
        Connection connection = connectionFactory.createConnection();
        //je lance la connection
        connection.start();

        //création d'une session
        Session session=connection.createSession(true, Session.AUTO_ACKNOWLEDGE);// true il n'envoie le message que aprés un commit
        Destination destination=session.createTopic("my topic.topic"); // fil d'attente de type topic (mytopic c'est le nom et .topic c'est le type )

        //création du MessageProducer
        MessageProducer producer = session.createProducer(destination); // qui envoit le message à la destination
        producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);// suppresion du message de la fil d'attente aprés l'envoie

        //création du message à envoyer
        TextMessage textMessage = session.createTextMessage();
        textMessage.setText("Hello world");// le message a envoyé
        session.commit(); // puisque on a fait Session.AUTO_ACKNOWLEDGE

        //fermeture de la session
        session.close();
        //fermeture de la connexion
        connection.close();
    } catch (Exception e) {e.printStackTrace();}
    }
}
