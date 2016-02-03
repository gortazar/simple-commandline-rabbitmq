package es.urjc.code.dad.rabbitmq.sender;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class Sender {

	public static void main(String[] args)
	      throws IOException, TimeoutException {

		if(args.length != 2) {
			System.out.println("Usage: java -jar rabbitmq-receiver.jar <queue name> <message>");
			System.exit(1);
		}

		final String QUEUE_NAME = args[0];
		final String message = args[1];

	    ConnectionFactory factory = new ConnectionFactory();
	    factory.setHost("localhost");
	    Connection connection = factory.newConnection();
	    Channel channel = connection.createChannel();

	    channel.queueDeclare(QUEUE_NAME, false, false, false, null);
	    channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
	    System.out.println(" [x] Sent '" + message + "'");    
	
		channel.close();
		connection.close();
	}
}
