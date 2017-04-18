package hotjavi.lei.com.netbase.model.base;

/**
 * Created by tom on 2017/4/11.
 */

public class BaseReqModel<T> {
   ReqHeader header;
    T body;

    public ReqHeader getHeader() {
        return header;
    }

    public void setHeader(ReqHeader header) {
        this.header = header;
    }

    public T getBody() {
        return body;
    }

    public void setBody(T body) {
        this.body = body;
    }

    public BaseReqModel(){}
    public BaseReqModel(T body){
        this.body=body;
    }
}
