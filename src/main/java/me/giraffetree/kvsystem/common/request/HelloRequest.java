package me.giraffetree.kvsystem.common.request;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import me.giraffetree.kvsystem.common.Operation;
import me.giraffetree.kvsystem.common.Result;
import me.giraffetree.kvsystem.common.response.HelloResponse;

/**
 * @author GiraffeTree
 * @date 2021/8/8
 */
@Slf4j
@Data
public class HelloRequest extends Operation {

    private String name;

    @Override
    public Result execute() {
        return new HelloResponse("hi~ " + name);
    }

    public HelloRequest(String name) {
        this.name = name;
    }
}
