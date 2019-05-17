package com.chenyilei.stream.ext;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

/**
 * --添加相关注释--
 *
 * @author chenyilei
 * @email 705029004@qq.com
 * @date 2019/05/13- 18:54
 */
public interface MyOutput {
    public static final String MyOutput = "gupao2018";

    @Output(MyOutput)
    MessageChannel gupao();
}
