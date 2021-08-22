package me.giraffetree.kvsystem.common.constant;

import io.netty.util.AttributeKey;

/**
 * @author GiraffeTree
 * @date 2021/8/14
 */
public enum ServerAttr {
    /**
     * 操作类型
     */
    OPERATION_TYPE(AttributeKey.newInstance("OPERATION_TYPE")),
    /**
     * 请求内容
     */
    REQUEST_CONTENT(AttributeKey.newInstance("REQUEST")),

    /**
     * 响应内容
     */
    RESPONSE_CONTENT(AttributeKey.newInstance("RESPONSE")),
    /**
     * 请求开始 时间
     */
    START_TIMESTAMP(AttributeKey.newInstance("START_TIMESTAMP")),
    ;

    ServerAttr(AttributeKey<Object> key) {
        this.key = key;
    }

    private final AttributeKey<Object> key;

    public AttributeKey<Object> getKey() {
        return key;
    }
}
