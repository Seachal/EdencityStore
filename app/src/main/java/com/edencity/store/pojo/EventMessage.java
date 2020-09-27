package com.edencity.store.pojo;

public class EventMessage {

    public static final int EVENT_PHOTO = 100;
    public static final int EVENT_QRCODE = 101;
    public static final int EVENT_FEEDBACK = 102;

    public int type;
    public Object data;

    public EventMessage(int type,Object data){
        this.type=type;
        this.data=data;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getType() {
        return type;
    }
}
