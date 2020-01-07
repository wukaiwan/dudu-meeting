package com.qfjy.api.tuling;

import com.qfjy.project.weixin.util.WeixinUtil;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 吴凯皖
 */
@Component
public class TulingUtils {

    private static final String TULING_URL = "http://openapi.tuling123.com/openapi/api/v2";

    private static final List<String> KEY_ARRAY = new ArrayList<>();

    private int num = 0 ;


    static {
        KEY_ARRAY.add("1896a791efbd4c338f36aa8c4636fbbc");
        KEY_ARRAY.add("768493348f674b59bd33532af96163f0");
        KEY_ARRAY.add("bfe05586db914cc196017efbd927be28");
        KEY_ARRAY.add("eb6cdf1f7a974bc7bdd6b35c39e60c7a");
        KEY_ARRAY.add("a285d643902a4a00a546ded3495af4bf");
        KEY_ARRAY.add("5d46f432d7a744dab2d0cdb7f5e5532b");
        KEY_ARRAY.add("160bc76215184671a94a33825d9469a5");
        KEY_ARRAY.add("a38a07f2386f4abeaf8cb7c027d46286");
        KEY_ARRAY.add("cbb6dc788c1a47cda4f44bf4e7035c3f");
        KEY_ARRAY.add("dc83ad93f4a443e4860aea319018a792");
    }

    private String sendPrivate(String msg){

        TulingBean tulingBean = new TulingBean();
        Perception perception = new Perception();

        InputText inputText = new InputText();
        inputText.setText(msg);
        perception.setInputText(inputText);
        perception.setInputImage(null);
        perception.setSelfInfo(null);

        tulingBean.setPerception(perception);
        tulingBean.setReqType(0);

        UserInfo userInfo = new UserInfo();
        userInfo.setApiKey(KEY_ARRAY.get(num));
        userInfo.setUserId("wukaiwan");

        tulingBean.setUserInfo(userInfo);

        JSONObject jsonObject = JSONObject.fromObject(tulingBean);

        JSONObject responseJson = WeixinUtil.httpRequest(TULING_URL, "POST", jsonObject.toString());
        JSONArray reslutArray = (JSONArray) responseJson.get("results");
        JSONObject arrayFirst = (JSONObject) reslutArray.get(0);
        JSONObject valusJson = (JSONObject) arrayFirst.get("values");
        String str = valusJson.getString("text");
        return str;
    }

    public String send(String msg){
        String str = sendPrivate(msg);
        if ("请求次数超限制!".equals(str)){
            num++;
            str = send(msg);
        }
        return str;
    }
}
