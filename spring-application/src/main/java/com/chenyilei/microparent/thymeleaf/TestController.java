package com.chenyilei.microparent.thymeleaf;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.Max;

/**
 * --添加相关注释--
 *
 * @author chenyilei
 * @email 705029004@qq.com
 * @date 2019/04/21- 12:55
 */
@Controller
public class TestController {


    @ModelAttribute(name = "ok")
    public String ok(){
        return "modelattribute 的ok";
    }

    @GetMapping("/test")
    public String test(HttpServletRequest request){

        return "test";
    }
}
