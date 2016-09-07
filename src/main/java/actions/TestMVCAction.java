package actions;

import mq.consumer.queue.QueueReceiver1;
import mq.producer.queue.QueueSender;
import mq.producer.topic.TopicSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


/**
 * Created by Heisenberg on 4/22/16.
 */
@Controller
public class TestMVCAction {

    @Autowired
    QueueSender queueSender;

    @Autowired
    TopicSender topicSender;

    @RequestMapping("/topic/sender")
    public String topicSender(@RequestParam("topicMessage") String topicMessage){
        topicSender.send("MessageTopic",topicMessage);
        return "result";
    }

    @RequestMapping("/queue/sender")
    public String queueSender(@RequestParam("message") String message){
        queueSender.send("MessageQueue",message);
        return "result";
    }



    @RequestMapping("/test")
    public String test(){


        System.out.println("hello...");
        return "result";
    }


}
