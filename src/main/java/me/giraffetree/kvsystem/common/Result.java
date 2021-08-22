package me.giraffetree.kvsystem.common;

import lombok.Data;

@Data
public abstract class Result extends MessageBody{

    protected int code = 200;

}
