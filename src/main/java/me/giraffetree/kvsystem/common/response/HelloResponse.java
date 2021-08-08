package me.giraffetree.kvsystem.common.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import me.giraffetree.kvsystem.common.Result;

/**
 * @author GiraffeTree
 * @date 2021/8/8
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class HelloResponse extends Result {

    private String msg;

}
