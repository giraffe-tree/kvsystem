package me.giraffetree.kvsystem.common;

import com.alibaba.fastjson.JSON;
import io.netty.buffer.ByteBuf;
import lombok.Data;

import java.nio.charset.StandardCharsets;

@Data
public abstract class Message<T extends MessageBody> {

    MessageHeader messageHeader;
    T messageBody;

    public Message() {
    }

    public Message(MessageHeader messageHeader, T messageBody) {
        this.messageHeader = messageHeader;
        this.messageBody = messageBody;
    }

    public T getMessageBody() {
        return messageBody;
    }

    public void encode(ByteBuf byteBuf) {
        // header 部分
        byteBuf.writeInt(messageHeader.getVersion());
        byte[] traceIdBytes = messageHeader.getTraceId().getBytes(StandardCharsets.UTF_8);
        byteBuf.writeInt(traceIdBytes.length);
        byteBuf.writeBytes(traceIdBytes);
        byte[] operationBytes = messageHeader.getOperation().getBytes(StandardCharsets.UTF_8);
        byteBuf.writeInt(operationBytes.length);
        byteBuf.writeBytes(operationBytes);

        // body 部分
        byteBuf.writeBytes(JSON.toJSONBytes(messageBody));
    }

    public abstract Class<T> getMessageBodyDecodeClass(String operation);

    public abstract OperationType getOperationType(String operation);

    public OperationType decode(ByteBuf msg) {
        int version = msg.readInt();
        int traceIdLen = msg.readInt();
        String traceId = msg.readBytes(traceIdLen).toString(StandardCharsets.UTF_8);
        int operationLen = msg.readInt();
        String operation = msg.readBytes(operationLen).toString(StandardCharsets.UTF_8);

        this.messageHeader = new MessageHeader(version, traceId, operation);
        OperationType operationType = getOperationType(operation);
        Class<T> bodyClazz = getMessageBodyDecodeClass(operation);
        this.messageBody = JSON.parseObject(msg.toString(StandardCharsets.UTF_8), bodyClazz);

        return operationType;
    }

}
