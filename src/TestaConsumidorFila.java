import javax.jms.*;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.Scanner;

public class TestaConsumidorFila {

    public static void main(String[] args) throws NamingException, JMSException, NamingException {

        InitialContext context = new InitialContext();
        ConnectionFactory factory = (ConnectionFactory) context.lookup("ConnectionFactory");
        Connection connection = factory.createConnection();
        connection.start();


        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

        Destination fila = (Destination) context.lookup("detran");
        MessageConsumer consumer = session.createConsumer(fila);

      //  Message message = consumer.receive(); //receive é apenas para UMA mensagem


        consumer.setMessageListener(new MessageListener() {
            @Override
            public void onMessage(Message message) {
TextMessage textMessage = (TextMessage) message;
                try {
                    System.out.println("Recebendo mensagem: " + textMessage.getText());
                } catch (JMSException e) {
                    e.printStackTrace();
                }
            }
        });




        new Scanner(System.in).nextLine();

        session.close();
        connection.close();
        context.close();
    }

}




