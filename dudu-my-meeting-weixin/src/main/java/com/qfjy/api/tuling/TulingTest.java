package com.qfjy.api.tuling;

import com.qfjy.project.weixin.util.WeixinUtil;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class TulingTest {

    public static final String URL = "http://openapi.tuling123.com/openapi/api/v2";

    public static void main(String[] args) {
        TulingBean tulingBean = new TulingBean();
        Perception perception = new Perception();

        InputText inputText = new InputText();
        inputText.setText("你好");
        perception.setInputText(inputText);
        perception.setInputImage(null);
        perception.setSelfInfo(null);

        tulingBean.setPerception(perception);
        tulingBean.setReqType(0);

        UserInfo userInfo = new UserInfo();
        userInfo.setApiKey("0d44699bde544431acecdfc79881a028");
        userInfo.setUserId("wukaiwan");

        tulingBean.setUserInfo(userInfo);

        JSONObject jsonObject = JSONObject.fromObject(tulingBean);

        JSONObject responseJson = WeixinUtil.httpRequest(URL, "POST", jsonObject.toString());
        JSONArray reslutArray = (JSONArray) responseJson.get("results");
        JSONObject arrayFirst = (JSONObject) reslutArray.get(0);
        JSONObject valusJson = (JSONObject) arrayFirst.get("values");
        String text = valusJson.getString("text");
        System.out.println(text);



    }
}
