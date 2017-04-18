package hotjavi.lei.com.netbase.admin;

import hotjavi.lei.com.netbase.model.RegisterData;
import hotjavi.lei.com.netbase.model.base.BaseReponseModel;
import hotjavi.lei.com.netbase.model.base.BaseReqModel;
import hotjavi.lei.com.netbase.model.base.ReqHeader;
import hotjavi.lei.com.netbase.util.AESUtils;
import hotjavi.lei.com.netbase.util.IAdminService;
import hotjavi.lei.com.netbase.util.RetrofitUtil;
import retrofit2.Retrofit;
import rx.Observable;
import rx.Subscriber;
import rx.functions.Action1;

/**
 * Created by tom on 2017/4/11.
 */

public class AdminUtil extends RetrofitUtil {
    private static final IAdminService adminService=getRetrofit().create(IAdminService.class);

    public static IAdminService getAdminService() {
        return adminService;
    }

    public static void main(String[]args){
        BaseReqModel<RegisterData> registerDataBaseReqModel=new BaseReqModel<>();
        RegisterData registerData=new RegisterData();
        registerData.setAccount("act6622");
        registerData.setPwd("L123244");
        registerData.setEmail("leiyi1993@foxmail.com");
        registerData.setNickname("hello");
        registerDataBaseReqModel.setBody(registerData);
        long timenow = System.currentTimeMillis();
        registerData.setPwd(AESUtils.parseByte2HexStr(AESUtils.encrypt( registerData.getPwd(),timenow+"-1000")));
        adminService.register(timenow +"",registerDataBaseReqModel)
                .subscribe(new Action1<BaseReponseModel<String>>() {
                    @Override
                    public void call(BaseReponseModel<String> stringBaseReponseModel) {
                        System.out.print(stringBaseReponseModel.getHeader());
                    }
                });
    }
}
