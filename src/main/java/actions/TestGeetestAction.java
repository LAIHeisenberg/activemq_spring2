package actions;

import com.geetest.sdk.java.GeetestLib;
import com.geetest.sdk.java.web.demo.GeetestConfig;
import com.sun.xml.internal.ws.client.RequestContext;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.RequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * Created by Heisenberg on 7/7/16.
 */
@Controller
public class TestGeetestAction {

    @RequestMapping("/verifyLogin")
    public void verifyLogin(
            @RequestParam(GeetestLib.fn_geetest_challenge) String challenge,
            @RequestParam(GeetestLib.fn_geetest_validate) String validate,
            @RequestParam(GeetestLib.fn_geetest_seccode) String seccode,
            HttpServletRequest request,HttpServletResponse response
            ){

        GeetestLib gtSdk = new GeetestLib(GeetestConfig.getCaptcha_id(), GeetestConfig.getPrivate_key());


        //从session中获取gt-server状态
        int gt_server_status_code = (Integer) request.getSession().getAttribute(gtSdk.gtServerStatusSessionKey);

        //从session中获取userid
        String userid = (String)request.getSession().getAttribute("userid");

        int gtResult = 0;

        if (gt_server_status_code == 1) {
            //gt-server正常，向gt-server进行二次验证

            gtResult = gtSdk.enhencedValidateRequest(challenge, validate, seccode, userid);
            System.out.println(gtResult);
        } else {
            // gt-server非正常情况下，进行failback模式验证

            System.out.println("failback:use your own server captcha validate");
            gtResult = gtSdk.failbackValidateRequest(challenge, validate, seccode);
            System.out.println(gtResult);
        }

        PrintWriter out = null;
        if (gtResult == 1) {
            // 验证成功

            JSONObject data = new JSONObject();
            try {
                out = response.getWriter();
                data.put("status", "success");
                data.put("version", gtSdk.getVersionInfo());
            } catch (Exception e) {
                e.printStackTrace();
            }
            out.println(data.toString());
        }
        else {
            // 验证失败
            JSONObject data = new JSONObject();
            try {
                data.put("status", "fail");
                data.put("version", gtSdk.getVersionInfo());
                out = response.getWriter();
            } catch (Exception e) {
                e.printStackTrace();
            }

            out.println(data.toString());
        }

    }
}
