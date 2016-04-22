package actions;

import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Heisenberg on 4/22/16.
 */
@RequestMapping("/mq")
public class TestMVCAction {

    @RequestMapping("/test")
    public void test(){
        System.out.println("hello...");
    }
}
