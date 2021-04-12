import javax.jms.*;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class TestaProdutorTopico {

    public static void main(String[] args) throws NamingException, JMSException {

        InitialContext context = new InitialContext();
        ConnectionFactory factory = (ConnectionFactory) context.lookup("ConnectionFactory");

        Connection connection = factory.createConnection();
        connection.start();

        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

        Destination topico = (Destination) context.lookup("multa");

        MessageProducer producer = session.createProducer(topico);


            Message message = session.createTextMessage("<PEDIDO> <" + 1234 + ">");
            producer.send(message);


        session.close();
        connection.close();
        session.close();
    }
}
