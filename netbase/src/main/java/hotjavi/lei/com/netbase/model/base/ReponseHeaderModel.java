package hotjavi.lei.com.netbase.model.base;

import com.google.gson.Gson;

/**
 * Created by tom on 2017/4/11.
 */

public class ReponseHeaderModel {

    /**
     * status : 3000
     * message : 当前用户名已存在
     */

    private int status;
    private String message;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
