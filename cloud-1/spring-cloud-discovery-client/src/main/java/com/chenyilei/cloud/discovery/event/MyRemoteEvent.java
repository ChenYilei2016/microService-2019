package com.chenyilei.cloud.discovery.event;

import org.springframework.context.ApplicationEvent;

import java.io.Serializable;

/**
 * --添加相关注释--
 *
 * @author chenyilei
 * @email 705029004@qq.com
 * @date 2019/05/15- 19:30
 */
public class MyRemoteEvent extends ApplicationEvent implements Serializable {

    /**
     * 事件传输类型 HTTP、RPC、MQ
     */
    private String type;

    /**
     * 应用名称
     */
    private final String appName;

    /**
     * 是否广播到集群
     */
    private final boolean isCluster;

    /***
     * @param source POJO 事件源，JSON 格式
     * @param appName
     * @param isCluster
     */
    public MyRemoteEvent(Object source, String appName, boolean isCluster) {
        super(source);
        this.appName = appName;
        this.isCluster = isCluster;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAppName() {
        return appName;
    }
}
