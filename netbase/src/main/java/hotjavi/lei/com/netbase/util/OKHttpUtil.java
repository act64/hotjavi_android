package hotjavi.lei.com.netbase.util;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.internal.cache.DiskLruCache;

/**
 * Created by tom on 2017/4/11.
 */

public class OKHttpUtil {
    private static OkHttpClient mOkHttpClient;

    public static OkHttpClient getClient(){
        if (mOkHttpClient==null){
            synchronized (OKHttpUtil.class){
                if (mOkHttpClient==null){
                   mOkHttpClient=new OkHttpClient.Builder()
                           .addNetworkInterceptor(new Interceptor() {
                               @Override
                               public okhttp3.Response intercept(Chain chain) throws IOException {
                                   Request originalRequest = chain.request();

                                   Request.Builder build = originalRequest.newBuilder();
                                   String time_span = originalRequest.header("time-span");
                                   if (time_span !=null){

                                   }else {
                                       time_span=System.currentTimeMillis()+"";
                                       build.addHeader("time-span",time_span);
                                   }
                                   build.addHeader("sign",AESUtils.parseByte2HexStr(AESUtils.encrypt(time_span,time_span+"-1000")));

                                   //原始请求里面可以放入定制的header,比如token
                                   Request request = build
//                                .addHeader("client-Info", "ANDROID")
//                                .addHeader("app-Version", Const.getAppVer(context))
                                           .addHeader("Content-Type", "application/json; charset=UTF-8")
//                                .addHeader("date", String.valueOf(System.currentTimeMillis()))
                                           .build();

                                   return chain.proceed(request);
                               }
                           })
                           .connectTimeout(10, TimeUnit.SECONDS)
                           .writeTimeout(30, TimeUnit.SECONDS)
                           .readTimeout(30, TimeUnit.SECONDS).build();
                }
            }
        }
        return mOkHttpClient;
    }
}
