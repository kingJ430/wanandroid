package com.zjf.wanandroid.manager;

import com.communicate.module.library.utils.CommunicateUtil;

/**
 * user: zhangjianfeng
 * date: 06/09/2017
 * version: 7.3
 */

public class CommunicateManager {
    private  static String[] module = new String[]{"Home","Main","Discovery","Navigation","User"};

    public static void init() {
        CommunicateUtil.setup(module);
    }
}
