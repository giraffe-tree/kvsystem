package me.giraffetree.kvsystem.common;

/**
 * @author GiraffeTree
 * @date 2021/8/8
 */
public abstract class Operation extends MessageBody {

    /**
     * 执行操作
     *
     * @return 操作结果
     */
    public abstract Result execute();

}