package org.example;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.*;
import java.net.URISyntaxException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeoutException;

public class Multithread extends Thread {

    private final static String QUEUE_NAME = "Message_Queue";
    MessageProcess messageProcess = new MessageProcess();


    @Override
    public void run() {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");

        try {
            factory.setUri("amqps://tregbovu:RYoWfmNci_jwekKqiCdHltID5WYqj3DU@beaver.rmq.cloudamqp.com/tregbovu");
        } catch (URISyntaxException | KeyManagementException | NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }

            factory.setVirtualHost("tregbovu");
            factory.setUsername("tregbovu");

            factory.setPassword("");

            try (Connection connection = factory.newConnection();
                 Channel channel= connection.createChannel()) {
                channel.queueDeclare(QUEUE_NAME, false, false, false, null);


                List<String> data = messageProcess.readCSV();
                BufferedReader reader = new BufferedReader(new FileReader("device_id.txt"));
                String device_id = reader.readLine();


                for (int i = 0; i < data.size(); i++) {
                    String message = data.get(i);

                    Date currDate = new Date();
                    message = messageProcess.stringToJson(Float.parseFloat(message), currDate.getTime() / 1000, device_id);
                    channel.basicPublish("", QUEUE_NAME, null, message.getBytes("UTF-8"));

                    System.out.println(message);
                    Thread.sleep(5000);
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (TimeoutException e) {
                throw new RuntimeException(e);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
    }
}
