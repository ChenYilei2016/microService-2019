package com.chenyilei.cloud.discovery.myfeign;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * --添加相关注释--
 *
 * @author chenyilei
 * @email 705029004@qq.com
 * @date 2019/05/09- 14:21
 */

@MyFeignClient(value = "spring-cloud-discovery-provider")
public interface MyRestInterface {
    @RequestMapping("/test")
    public String test(@RequestParam(value = "msg",required = false)String msg);
}
