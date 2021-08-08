package me.giraffetree.kvsystem.utils;

import com.alipay.common.tracer.core.generator.TraceIdGenerator;

/**
 * @author GiraffeTree
 * @date 2021/8/7
 */
public class TraceUtils {

    public static String newTraceId() {
        return TraceIdGenerator.generate();
    }

}
