package com.edencity.store.custum.calendar;

// Created by Ardy on 2020/3/4.

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

public class CalendarDayRelativeLayout extends RelativeLayout {
    public CalendarDayRelativeLayout(Context context) {
        this(context, null);
    }

    public CalendarDayRelativeLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void isDurationSat(boolean isSaturday) {
        /*this.setBackground(getResources().getDrawable(R.drawable.appoint_calendar_sat_bg));*/
    }

    public void isDurationSun(boolean isSunday) {
        /*this.setBackground(getResources().getDrawable(R.drawable.appoint_calendar_sun_bg));*/
    }
    public void isETime(boolean etime) {
        /*this.setBackground(getResources().getDrawable(R.mipmap.icon_select));*/
    }
    public void isSTime(boolean stime) {
        /*this.setBackground(getResources().getDrawable(R.mipmap.icon_unselect));*/
    }
}