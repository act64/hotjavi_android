package hotjavi.lei.com.netbase.util;


import android.support.annotation.MainThread;

import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;

import hotjavi.lei.com.netbase.model.CashKey;
import hotjavi.lei.com.netbase.model.base.BaseReponseModel;
import hotjavi.lei.com.netbase.model.base.ReponseHeaderModel;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by tom on 2017/4/11.
 */

public class RetrofitUtil {

    private static Retrofit mretrofit;

    protected static Retrofit getRetrofit() {
        if (mretrofit == null) {
            synchronized (RetrofitUtil.class) {
                if (mretrofit == null) {
                    mretrofit = new Retrofit.Builder()
                            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                            .addConverterFactory(GsonConverterFactory.create())
                            .client(OKHttpUtil.getClient())
                            .baseUrl(GlobManger.getAppProxy().getBaseUrl())
                            .build();
                }
            }
        }
        return mretrofit;
    }



    public static <T> void setSubsciber(final rx.Observable<BaseReponseModel<T>> observable, Subscriber<BaseReponseModel<T>> subscriber, final CashKey cashKey) {

        Observable.just("check Internet")
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                //check weher hava internet
                .flatMap(new Func1<String, Observable<BaseReponseModel<T>>>() {
                    @Override
                    public Observable<BaseReponseModel<T>> call(String s) {
                        if (NetWorkStateUtil.haveInternet()){
                            return observable;
                        }else if (cashKey!=null){
                            BaseReponseModel<T> baseReponseModel=new BaseReponseModel<T>();
                            T t= CacheUtil.getCache(cashKey, new TypeToken<T>(){}.getRawType());
                            baseReponseModel.setBody(t);
                            baseReponseModel.setHeader(new ReponseHeaderModel());
                            if (t==null) {
                                baseReponseModel.getHeader().setStatus(ResponseStaus.ERROR_NET);
                            }else {
                                baseReponseModel.getHeader().setStatus(ResponseStaus.OK);
                            }
                            return Observable.just( baseReponseModel);
                        }else {
                            BaseReponseModel<T> baseReponseModel=new BaseReponseModel<T>();
                            baseReponseModel.setBody(null);
                            baseReponseModel.setHeader(new ReponseHeaderModel());
                            baseReponseModel.getHeader().setStatus(ResponseStaus.ERROR_NET);
                            return Observable.just( baseReponseModel);
                        }
                    }
                })
                .map(new Func1<BaseReponseModel<T>, BaseReponseModel<T>>() {
                    @Override
                    public BaseReponseModel<T> call(BaseReponseModel<T> t) {

                        if (t.getHeader().getStatus()==2000){
                            if (cashKey!=null) {
                                CacheUtil.saveCache(cashKey,t.getBody());
                            }
                            return t;
                        }
                        return null;
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }
}
