package com.algz.websocket.stomp;

/**
 * 接收消息
 * @param <T>
 */
public class RequestMsg<T> {
    private T body;

    public T getBody() {
        return body;
    }

    public void setBody(T body) {
        this.body = body;
    }
}
