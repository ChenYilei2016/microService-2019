package com.chenyilei.cloud.discovery.controller;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * --添加相关注释--
 *
 * @author chenyilei
 * @email 705029004@qq.com
 * @date 2019/05/08- 21:03
 *
 *
 */
@FeignClient(name = "spring-cloud-discovery-provider")
public interface InvokingServerService {

    @GetMapping("/test")
    public String test(@RequestParam(value = "msg",required = false)String msg);
}
