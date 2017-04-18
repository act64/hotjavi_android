package hotjavi.lei.com.netbase.model;

/**
 * Created by tom on 2017/4/13.
 */

public class CashKey {
    long uid;
    String requestUrl;
    long key;

    public CashKey(long uid, String requestUrl, long key) {
        this.uid = uid;
        this.requestUrl = requestUrl;
        this.key = key;
    }

    public long getUid() {
        return uid;
    }

    public void setUid(long uid) {
        this.uid = uid;
    }

    public String getRequestUrl() {
        return requestUrl;
    }

    public void setRequestUrl(String requestUrl) {
        this.requestUrl = requestUrl;
    }

    public long getKey() {
        return key;
    }

    public void setKey(long key) {
        this.key = key;
    }
}
