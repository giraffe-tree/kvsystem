package me.giraffetree.kvsystem.common;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class ResponseMessage extends Message<Result> {

    @Override
    public Class getMessageBodyDecodeClass(String operation) {
        return OperationType.valueOf(operation).getResponse();
    }

    @Override
    public OperationType getOperationType(String operation) {
        return OperationType.valueOf(operation);
    }

}
