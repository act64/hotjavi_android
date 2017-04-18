package hotjavi.lei.com.netbase.model.base;

/**
 * Created by tom on 2017/4/11.
 */

public class BaseReponseModel<T> {
    ReponseHeaderModel header;
    T body;

    public ReponseHeaderModel getHeader() {
        return header;
    }

    public void setHeader(ReponseHeaderModel header) {
        this.header = header;
    }

    public T getBody() {
        return body;
    }

    public void setBody(T body) {
        this.body = body;
    }
}
