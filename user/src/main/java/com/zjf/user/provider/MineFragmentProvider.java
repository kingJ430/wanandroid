package com.zjf.user.provider;


import com.communicate.module.annotation.Provider;
import com.communicate.module.library.base.provider.BaseProvider;
import com.communicate.module.library.router.RouterRequest;
import com.communicate.module.library.router.RouterRespone;
import com.zjf.user.MineFragment;

/**
 * @Desc
 * @Author zjf
 * @Date 2019/3/29
 */
@Provider(name = "user/mineFragment")
public class MineFragmentProvider implements BaseProvider {
    @Override
    public boolean isAsync(RouterRequest routerRequest) {
        return false;
    }

    @Override
    public RouterRespone invoke(RouterRequest routerRequest) {
        RouterRespone result = new RouterRespone.Builder()
                .code(RouterRespone.CODE_SUCCESS)
                .msg("success")
                .result(new MineFragment())
                .build();
        return result;
    }

    @Override
    public String getName() {
        return null;
    }
}
