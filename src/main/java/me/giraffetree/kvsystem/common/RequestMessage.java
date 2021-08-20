package me.giraffetree.kvsystem.common;

import com.alipay.common.tracer.core.generator.TraceIdGenerator;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RequestMessage extends Message<Operation> {

    @Override
    public Class getMessageBodyDecodeClass(String operation) {
        return OperationType.valueOf(operation).getRequest();
    }

    public RequestMessage(OperationType operationType, Operation requestBody) {
        MessageHeader messageHeader = new MessageHeader();
        messageHeader.setVersion(1);
        messageHeader.setTraceId(TraceIdGenerator.generate());
        messageHeader.setOperation(operationType.name());
        this.messageHeader = messageHeader;
        this.messageBody = requestBody;
    }
}
