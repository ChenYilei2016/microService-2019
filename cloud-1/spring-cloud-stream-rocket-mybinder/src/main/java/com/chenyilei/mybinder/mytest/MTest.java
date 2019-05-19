package com.chenyilei.mybinder.mytest;

import org.springframework.util.SerializationUtils;

import java.util.ServiceLoader;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * {@link java.beans.BeanInfo}
 * {@link java.beans.PropertyDescriptor }
 *
 * {@link org.springframework.beans.PropertyEditorRegistrar}
 *
 * @author chenyilei
 * @email 705029004@qq.com
 * @date 2019/05/18- 14:07
 */
public class MTest {
    static class T implements Cloneable{
        String test = "";

        @Override
        public Object clone() throws CloneNotSupportedException {
            return super.clone();
        }
    }
    public static void main(String[] args) throws Exception{
        String f = "adfsf查收到的";
        System.out.println(new String(SerializationUtils.serialize(f)));
        System.out.println(new String(SerializationUtils.serialize(f.getBytes())));
        AtomicInteger a = new AtomicInteger();

        T tt = new T();
        Object clone = tt.clone();
        System.out.println(tt);
        System.out.println(clone);
//        ServiceLoader

    }
}
