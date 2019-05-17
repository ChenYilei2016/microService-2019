package com.chenyilei.stream.ext;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

/**
 * --添加相关注释--
 *
 * @author chenyilei
 * @email 705029004@qq.com
 * @date 2019/05/13- 18:54
 */
public interface MyInput {
    public static final String MyInput = "gupao2018";

    @Input(MyInput)
    SubscribableChannel gupao();
}
