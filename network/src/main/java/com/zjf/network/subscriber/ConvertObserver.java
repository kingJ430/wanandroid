package com.zjf.network.subscriber;


import com.zjf.network.models.RestError;
import com.zjf.network.util.ExceptionUtil;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;


/**
 * Created by zhangjianfeng
 */

public abstract class ConvertObserver<T> implements Observer<T> {

//    private BasePresenter  mBasePresenter;
//
//    public ConvertObserver(BasePresenter presenter) {
//        mBasePresenter = presenter;
//    }

    @Override
    public void onError(Throwable e) {
        RestError restError = ExceptionUtil.generateRestError(e);
        onFailed(restError);
    }

    @Override
    public void onComplete() {

    }

    @Override
    public void onSubscribe(Disposable d) {
    }


    @Override
    public void onNext(T t) {
        onSuccess(t);
    }

    /**
     * @param pRestError
     * @return true:表未异常不需要上层再处理(即不需要再回调onExceptionDispose方法)
     * false :此异常需要基类中的异常处理方案处理。（表示此异常没有被消费掉）
     */
    public abstract void onFailed(RestError pRestError);

    public abstract void onSuccess(T t);

}
