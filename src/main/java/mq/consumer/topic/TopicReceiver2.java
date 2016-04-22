package mq.consumer.topic;

import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

/**
 * Created by Heisenberg on 4/21/16.
 */
@Component("topicReceiver2")
public class TopicReceiver2 implements MessageListener{

    public void onMessage(Message message) {
        try {
            System.out.println("TopicReceiver2 receive message: "+((TextMessage) message).getText());
        }catch (JMSException e){
            e.printStackTrace();
        }
    }
}
