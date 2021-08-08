package me.giraffetree.kvsystem.common;

import me.giraffetree.kvsystem.common.request.HelloRequest;
import me.giraffetree.kvsystem.common.response.HelloResponse;

public enum OperationType {

    /**
     * hello 请求
     */
    HELLO(HelloRequest.class, HelloResponse.class),

    ;
    private final Class<? extends Operation> request;
    private final Class<? extends Result> response;

    OperationType(Class<? extends Operation> request, Class<? extends Result> response) {
        this.request = request;
        this.response = response;
    }

    public Class<? extends Operation> getRequest() {
        return request;
    }

    public Class<? extends Result> getResponse() {
        return response;
    }
}