package hotjavi.lei.com.netbase.util;

import hotjavi.lei.com.netbase.model.RegisterData;
import hotjavi.lei.com.netbase.model.base.BaseReponseModel;
import hotjavi.lei.com.netbase.model.base.BaseReqModel;
import retrofit2.http.Body;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Header;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by tom on 2017/4/11.
 */

public interface IAdminService {
    @POST("register/register")
    Observable<BaseReponseModel<String>> register (@Header("time-span") String time, @Body BaseReqModel<RegisterData> registerDataReq);
}
