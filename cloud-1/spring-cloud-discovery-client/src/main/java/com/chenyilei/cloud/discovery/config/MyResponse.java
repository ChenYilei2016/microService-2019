package com.chenyilei.cloud.discovery.config;

import org.springframework.http.HttpHeaders;
import org.springframework.http.client.AbstractClientHttpResponse;

import java.io.IOException;
import java.io.InputStream;
import java.net.URLConnection;

/**
 * --添加相关注释--
 *
 * @author chenyilei
 * @email 705029004@qq.com
 * @date 2019/05/05- 15:40
 */
public class MyResponse extends AbstractClientHttpResponse {

    private final URLConnection urlConnection;

    public MyResponse(final URLConnection urlConnection) {
        this.urlConnection = urlConnection;
    }

    @Override
    public int getRawStatusCode() throws IOException {
        return 200;
    }

    @Override
    public String getStatusText() throws IOException {
        return "ok";
    }

    @Override
    public void close() {

    }

    @Override
    public InputStream getBody() throws IOException {
        return urlConnection.getInputStream();
    }

    @Override
    public HttpHeaders getHeaders() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Content-Type", "application/text;charset=utf-8");
        return httpHeaders;
    }
}
