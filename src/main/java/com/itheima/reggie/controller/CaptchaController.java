package com.itheima.reggie.controller;


import com.google.code.kaptcha.Producer;
import com.itheima.reggie.common.R;
import com.itheima.reggie.utils.CommonUtil;
import com.itheima.reggie.utils.JsonData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

@Slf4j
@RequestMapping("captcha")
@RestController
public class CaptchaController {
    @Autowired
    private Producer captchaProducer;
    @Autowired
    private RedisTemplate redisTemplate;
    @GetMapping("get")
    public void getCaptcha(HttpServletRequest request, HttpServletResponse response){
        String text = captchaProducer.createText();
        redisTemplate.opsForValue().set(getCaptchaKey(request),text,30, TimeUnit.SECONDS);
        BufferedImage image = captchaProducer.createImage(text);
        try {
            ServletOutputStream outputStream = response.getOutputStream();
            ImageIO.write(image,"png",outputStream);
            outputStream.flush();
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @GetMapping("check")
    public R checkCaptcha(String captcha, HttpServletRequest request){
        Object value = redisTemplate.opsForValue().get(getCaptchaKey(request));
        if(value==null){
            //return JsonData.buildError("验证码过期");
            return R.error("验证码过期");
        }
        if(captcha.equals(value)){
            //登录成功后 删除redis的验证码缓存
            redisTemplate.delete(getCaptchaKey(request));
            //return JsonData.buildSuccess(null,"验证成功");
            return R.success("验证成功");
        }
        //return JsonData.buildError("验证码错误");
        return R.error("验证码错误");
    }
    private String getCaptchaKey(HttpServletRequest request){
        String ip = CommonUtil.getIpAddr(request);
        String userInfo = request.getHeader("User-Agent");
        String md5 = CommonUtil.MD5(ip + userInfo);
        return "captcha:"+md5;
    }
}
