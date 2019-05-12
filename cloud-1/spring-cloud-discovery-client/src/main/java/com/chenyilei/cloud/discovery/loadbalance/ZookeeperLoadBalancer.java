package com.chenyilei.cloud.discovery.loadbalance;

import com.netflix.loadbalancer.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * --添加相关注释--
 *
 * @author chenyilei
 * @email 705029004@qq.com
 * @date 2019/05/12- 14:35
 */
@Component
public class ZookeeperLoadBalancer extends BaseLoadBalancer {

    @Autowired
    DiscoveryClient discoveryClient;

    IRule iRule = new RandomRule();

    @Override
    public Server chooseServer(Object key) {
        Server server = new Server("127.0.0.1",9090);
        return server;
    }

    @Override
    public void addServer(Server newServer) {
        super.addServer(newServer);
    }


}
