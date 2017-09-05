package sun.geonoon.wh.androidtest.messaging;

/**
 * Created by marc on 17-9-5.
 */

public class Msg {

    public static final int TYPE_RECEIVED = 0;

    public static final int TYPE_SEND = 1;

    //消息内容
    private String content;

    //消息类型
    private int type;

    public Msg(String content, int type) {
        this.content = content;
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public int getType() {
        return  type;
    }
}
