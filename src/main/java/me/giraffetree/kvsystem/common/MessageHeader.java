package me.giraffetree.kvsystem.common;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MessageHeader {

    private int version = 1;
    /**
     * 30个字符
     */
    private String traceId;
    /**
     * 操作标识
     */
    private String operation;


}
