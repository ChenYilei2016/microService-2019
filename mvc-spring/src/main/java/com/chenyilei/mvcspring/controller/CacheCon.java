package com.chenyilei.mvcspring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * --添加相关注释--
 *
 * @author chenyilei
 * @email 705029004@qq.com
 * @date 2019/04/22- 16:37
 */
@Controller
public class CacheCon {

    @GetMapping("/test")
    @ResponseBody
    public User test(){
//        return ResponseEntity.ok().cacheControl()
        //浏览器不动了
//        return ResponseEntity.status(HttpStatus.NOT_MODIFIED).body("nihao");
        User user = new User();
        user.setName("nihao!!!");
        return user;
    }

}
