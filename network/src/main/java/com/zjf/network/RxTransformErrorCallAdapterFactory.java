package com.zjf.network;

import com.google.gson.stream.MalformedJsonException;
import com.zjf.network.exception.*;
import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Scheduler;
import io.reactivex.functions.Function;
import org.reactivestreams.Publisher;
import retrofit2.Call;
import retrofit2.CallAdapter;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.HttpException;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.io.IOException;
import java.io.InterruptedIOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

/**
 * Transform Error Call Adapter
 */
public class RxTransformErrorCallAdapterFactory extends CallAdapter.Factory {
    private final RxJava2CallAdapterFactory original;

    private RxTransformErrorCallAdapterFactory() {
        original = RxJava2CallAdapterFactory.create();
    }

    private RxTransformErrorCallAdapterFactory(Scheduler scheduler) {
        original = RxJava2CallAdapterFactory.createWithScheduler(scheduler);
    }

    public static CallAdapter.Factory create() {
        return new RxTransformErrorCallAdapterFactory();
    }

    public static CallAdapter.Factory createWithScheduler(Scheduler scheduler) {
        return new RxTransformErrorCallAdapterFactory(scheduler);
    }

    @Override
    public CallAdapter<?, ?> get(Type returnType, Annotation[] annotations, Retrofit retrofit) {
        CallAdapter<?, ?> adapter = original.get(returnType, annotations, retrofit);
        if (adapter == null) return null;

        return new RxCallAdapterWrapper(adapter,returnType);
    }

    private static class RxCallAdapterWrapper<R> implements CallAdapter<R, Object> {
        private final CallAdapter<R, ?> wrapped;
        private final Type type;

        RxCallAdapterWrapper(CallAdapter<R, ?> wrapped, Type returnType) {
            this.wrapped = wrapped;
            this.type = returnType;
        }

        @Override
        public Type responseType() {
            return wrapped.responseType();
        }

        @SuppressWarnings("unchecked")
        @Override
        public Object adapt(Call<R> call) {
            Class<?> rawType = getRawType(type);
            Observable observable;
            Flowable flowable = null;
            if (rawType == Flowable.class) {
                flowable = (Flowable) wrapped.adapt(call);

                return flowable.onErrorResumeNext(new Function<Throwable, Publisher<? extends Throwable>>() {
                    @Override
                    public Publisher<? extends Throwable> apply(Throwable throwable) throws Exception {
                        return Flowable.error(transformException(throwable));
                    }
                });
            } else if(rawType == Observable.class) {
                observable = (Observable) wrapped.adapt(call);
                return observable.onErrorResumeNext(new Function<Throwable, ObservableSource>() {
                    @Override
                    public ObservableSource apply(Throwable throwable) throws Exception {
                        return Observable.error(transformException(throwable));
                    }
                });
            }
            return null;
        }

        private Throwable transformException(Throwable throwable) {

            // We had non-200 http error
            if (throwable instanceof HttpException) {
                return new ServerBusyException();
            }
            // A network error happened
            if (throwable instanceof IOException) {
                if (throwable instanceof MalformedJsonException) {
                    return new ConversionException();
                } else if (throwable instanceof UnknownHostException) {
                    return new NoNetworkException();
                } else if (throwable instanceof ConnectException) {
                    return new NoNetworkException();
                } else if (throwable instanceof SocketTimeoutException) {
                    return new NetworkTimeOutException();
                } else if (throwable instanceof InterruptedIOException) {
                    return new NetworkTimeOutException();
                } else {
                    return new NetworkErrorException();
                }
            }

            // We don't know what happened. We need to simply throw error
            return throwable;

        }
    }
}
