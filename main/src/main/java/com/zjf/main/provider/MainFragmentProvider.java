package com.zjf.main.provider;

import com.communicate.module.annotation.Provider;
import com.communicate.module.library.base.provider.BaseProvider;
import com.communicate.module.library.router.RouterRequest;
import com.communicate.module.library.router.RouterRespone;
import com.zjf.main.MainFragment;

/**
 * @Desc
 * @Author zjf
 * @Date 2019/3/29
 */
@Provider(name = "main/mainFragment")
public class MainFragmentProvider implements BaseProvider {
    @Override
    public boolean isAsync(RouterRequest routerRequest) {
        return false;
    }

    @Override
    public RouterRespone invoke(RouterRequest routerRequest) {
        RouterRespone result = new RouterRespone.Builder()
                .code(RouterRespone.CODE_SUCCESS)
                .msg("success")
                .result(new MainFragment())
                .build();
        return result;
    }

    @Override
    public String getName() {
        return null;
    }
}
