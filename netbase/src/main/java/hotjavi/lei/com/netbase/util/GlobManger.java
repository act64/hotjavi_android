package hotjavi.lei.com.netbase.util;

/**
 * Created by tom on 2017/4/13.
 */

public class GlobManger {

    private static IAppProxy appProxy;

    public static IAppProxy getAppProxy() {
        if (appProxy==null){
            return new IAppProxy() {
                @Override
                public String getBaseUrl() {
                    return "http://192.168.0.154:8080/hotjavi/";
                }
            };
        }
        return appProxy;
    }

    public static void setAppProxy(IAppProxy appProxy) {
        appProxy = appProxy;
    }

    public interface IAppProxy{

        String getBaseUrl();

    }
}
