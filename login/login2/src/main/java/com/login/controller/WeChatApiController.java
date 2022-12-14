package com.login.controller;


import com.google.gson.Gson;
import com.login.utils.ConstantWeChatUtils;
import com.login.utils.HttpClientUtils;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.net.URLEncoder;
import java.util.HashMap;

@CrossOrigin
@Controller  //只是请求地址,不需要返回数据
@RequestMapping("/api/ucenter/wx")
public class WeChatApiController {




    //1 生成微信扫描二维码(扫描微信二维码登录)
    @GetMapping("login")
    public String getWxCode() {

        // 微信开放平台授权baseUrl  %s相当于?代表占位符
        String baseUrl = "https://open.weixin.qq.com/connect/qrconnect" +
                "?appid=%s" +
                "&redirect_uri=%s" +
                "&response_type=code" +
                "&scope=snsapi_login" +
                "&state=%s" +
                "#wechat_redirect";

        //对redirect_url进行URLEncoder编码
        String redirectUrl = ConstantWeChatUtils.WX_OPEN_REDIRECT_URL;
        try {
            redirectUrl = URLEncoder.encode(redirectUrl, "utf-8");
        }catch(Exception e) {
        }

        //向baseUrl的参数传值
        String url = String.format(
                    baseUrl,
                ConstantWeChatUtils.WX_OPEN_APP_ID,
                    redirectUrl,
                    "atguigu"
                 );

        //重定向到 微信二维码的页面
        return "redirect:"+url;
    }



    //2 获取扫描人信息,添加数据
    @GetMapping("callback")
    public String callback(String code, String state, HttpSession session) {
        try {
            String baseAccessTokenUrl = "https://api.weixin.qq.com/sns/oauth2/access_token" +
                    "?appid=%s" +
                    "&secret=%s" +
                    "&code=%s" +
                    "&grant_type=authorization_code";
            //拼接三个参数:id、秘钥、code值(临时票据,类似于验证码)
            String accessTokenUrl = String.format(
                    baseAccessTokenUrl,
                    ConstantWeChatUtils.WX_OPEN_APP_ID,
                    ConstantWeChatUtils.WX_OPEN_APP_SECRET,
                    code
            );

            //使用httpclient发送请求,得到返回结果(HttpClientUtils可以实现不用浏览器就发送请求并得到响应)
            String accessTokenInfo = HttpClientUtils.get(accessTokenUrl);

            //使用json转换工具 Gson
            Gson gson = new Gson();
            //将accessTokenInfo转换成HashMap集合
            HashMap mapAccessToken = gson.fromJson(accessTokenInfo, HashMap.class);

            //获取 access_token、openid,用于发送请求,得到扫码人信息
            String access_token = (String)mapAccessToken.get("access_token");
            String openid = (String)mapAccessToken.get("openid");


                String baseUserInfoUrl = "https://api.weixin.qq.com/sns/userinfo" +
                        "?access_token=%s" +
                        "&openid=%s";

                String userInfoUrl = String.format(
                        baseUserInfoUrl,
                        access_token,
                        openid
                );
                //发送请求,得到扫码人信息
                String userInfo = HttpClientUtils.get(userInfoUrl);

                //扫码人信息转HashMap集合
                HashMap userInfoMap = gson.fromJson(userInfo, HashMap.class);

                String nickname = (String)userInfoMap.get("nickname");//昵称

                session.setAttribute("userName",nickname);


            //最后: 返回首页面,通过路径传递token字符串
            //return "redirect:http://gb8888.com:8160/api/ucenter/wx/good/"+nickname;
            //return "redirect:http://gb8888.com:8160/teacherNotice";
            return "redirect:/teacherNotice";
        }catch(Exception e) {
            return null;
        }
    }


    @GetMapping("/good/{nickname}")
    public String getName(@PathVariable String nickname,HttpSession session){

        session.setAttribute("userName",nickname);

        return "redirect:/teacherNotice";
    }

}
