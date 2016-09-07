package mq.jmsexception;


import org.springframework.stereotype.Component;

import javax.jms.ExceptionListener;
import javax.jms.JMSException;

/**
 * Created by LaiDaShen on 2016/4/23.
 */
@Component("jmsExceptionListener")
public class JmsExceptionListener implements ExceptionListener{
    @Override
    public void onException(JMSException e) {
        e.printStackTrace();
    }
}
