package com.login.myhandler;

import com.alibaba.csp.sentinel.slots.block.BlockException;


public class BlockHandler {


    public static String blockHandler1(BlockException exception) {

        return "protect";
    }


    public static String blockHandler3(BlockException exception) {
        return "按客戶自定义,global HandlerException------1";
    }

    public static String blockHandler2(BlockException exception) {
        return "按客戶自定义,global HandlerException------2";
    }
}
