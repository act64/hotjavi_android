package hotjavi.lei.com.netbase.util;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

import hotjavi.lei.com.netbase.model.CashKey;
import hotjavi.lei.com.netbase.model.base.BaseReqModel;
import hotjavi.lei.com.netbase.model.base.ReqHeader;

/**
 * Created by tom on 2017/4/13.
 */

public class CacheUtil {
    public static void  saveCache(CashKey cashKey,Object cashOnjext){

    }
    public static<T> T  getCache(CashKey cashKey,Type type){
        return null;
    }

    public static void main(String[]args){
        BaseReqModel<ArrayList<ReqHeader>> baseReqModel=new BaseReqModel<>();
        ArrayList<ReqHeader> headers=new ArrayList<>();

        ReqHeader header=new ReqHeader();
        header.setToken("adddd");
        header.setUid(10001);
        headers.add(header);
        baseReqModel.setBody(headers);
        Gson gson =new Gson();
      String jsstr=  gson.toJson(baseReqModel);
        System.out.println(jsstr);
        BaseReqModel<ArrayList<ReqHeader>> outobj = getArrayParam(ReqHeader.class, jsstr);
        System.out.println(gson.toJson(outobj));

    }

    public static <T> BaseReqModel<ArrayList<T>> getArrayParam(Class<T> clazz,String json){
        return new Gson().fromJson(json,TypeToken.getParameterized(BaseReqModel.class,TypeToken.getParameterized(ArrayList.class,clazz).getType()).getType());
    }

    public static<T>  Type gettClass(BaseReqModel<T> baseReqModel){
        return new TypeToken<T>(){}.getType();
    }

    public static <T> T testGen(Type t,String str){
        return new Gson().fromJson(str,t);
    }
}
