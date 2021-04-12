import javax.jms.*;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.Scanner;

public class TestaConsumidor {

    public static void main(String[] args) throws NamingException, JMSException, NamingException {

        InitialContext context = new InitialContext();
        ConnectionFactory factory = (ConnectionFactory) context.lookup("ConnectionFactory");
        Connection connection = factory.createConnection();
        connection.start();


        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

        Destination fila = (Destination) context.lookup("detran");
        MessageConsumer consumer = session.createConsumer(fila);
        Message message = consumer.receive();
        System.out.println("Recebendo mensagem: " + message);

        new Scanner(System.in).nextLine();
        session.close();
        connection.close();
        context.close();
    }

}




