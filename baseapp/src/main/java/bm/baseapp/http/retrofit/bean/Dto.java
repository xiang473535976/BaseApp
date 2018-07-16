package bm.baseapp.http.retrofit.bean;

import java.io.Serializable;


public class Dto<T> implements Serializable {

    private String msg = ""; // 提示信息，一般可以直接toast出来
    private int cod;
    private T date = null;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCod() {
        return cod;
    }

    public void setCod(int cod) {
        this.cod = cod;
    }

    public T getDate() {
        return date;
    }

    public void setDate(T date) {
        this.date = date;
    }
}
