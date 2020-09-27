package com.edencity.store.home.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.bigkoo.pickerview.adapter.ArrayWheelAdapter;
import com.contrarywind.listener.OnItemSelectedListener;
import com.contrarywind.view.WheelView;
import com.edencity.store.base.BaseActivity;
import com.ogaclejapan.smarttablayout.SmartTabLayout;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import com.edencity.store.R;
import com.edencity.store.base.BaseDialog;
import com.edencity.store.custum.ControlViewPager;
import com.edencity.store.custum.MyMediumTextView;
import com.edencity.store.custum.MyNormalTextView;
import com.edencity.store.custum.calendar.CommonConstant;
import com.edencity.store.custum.calendar.DateUtil;
import com.edencity.store.custum.statubar.StatusBarCompat;
import com.edencity.store.home.adapter.CountVpAdapter;
import com.edencity.store.home.fragment.ItemCountFragment;
import com.edencity.store.util.AdiUtils;
import com.edencity.store.util.DisplayInfoUtils;
import com.edencity.store.util.ResUtils;

public class CountActivity extends BaseActivity {

    private SmartTabLayout mTab;
    private ControlViewPager mVp;
    private MyMediumTextView custom;
    private BaseDialog baseDialog;
    private String etimestr;
    private String stimestr = "2018年2月25日";
    private LinearLayout begin_btn;
    private LinearLayout end_btn;
    private MyNormalTextView begin_time;
    private MyNormalTextView end_time;
    private MyNormalTextView begin;
    private MyNormalTextView end;
    private boolean isCheckBegin = true;

    ArrayList<String> list = new ArrayList<>();
    ArrayList<String> list2 = new ArrayList<>();
    ArrayList<String> list3 = new ArrayList<>();

    private int curentYear = Calendar.getInstance().get(Calendar.YEAR);
    private int curentMonth = Calendar.getInstance().get(Calendar.MONTH) + 1;
    private int beginYear = 2018;
    //当前天数在集合的下标
    private int nowDateId;
    //当前月在集合的下标
    private int nowMonthId;
    //当前年在集合的下标
    private int curentYearId = -1;
    private WheelView mLoop1;
    private WheelView mLoop2;
    private WheelView mLoop3;
    private int checkYear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StatusBarCompat.setStatusBarColor(this, Color.WHITE);
        StatusBarCompat.changeToLightStatusBar(this);
        setContentView(R.layout.activity_count);
        initView();
    }

    private void initView() {
        mTab = findViewById(R.id.tab);
        custom = findViewById(R.id.custom);
        mVp = (ControlViewPager) findViewById(R.id.vp);
        Date time = Calendar.getInstance().getTime();
        etimestr = DateUtil.dateTostr(time, CommonConstant.TFORMATE_year);
        ArrayList<String> objects = new ArrayList<>();
        objects.add("7日");
        objects.add("30日");
        objects.add("自然月");
        objects.add("自定义");
        ArrayList<Fragment> objects1 = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            ItemCountFragment itemCountFragment = new ItemCountFragment();
            Bundle bundle = new Bundle();
            bundle.putInt("type", i);
            itemCountFragment.setArguments(bundle);
            objects1.add(itemCountFragment);

        }

        CountVpAdapter countVpAdapter = new CountVpAdapter(getSupportFragmentManager(), objects, objects1);
        mVp.setAdapter(countVpAdapter);
        mTab.setViewPager(mVp);
        mVp.setNoSlide(false);

        custom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                showTimeDialog();
            }
        });

    }

    private void showTimeDialog() {
        View inflate = LayoutInflater.from(CountActivity.this).inflate(R.layout.dialog_calendar, null);
        MyNormalTextView sure = inflate.findViewById(R.id.sure);
        MyNormalTextView dismiss = inflate.findViewById(R.id.dissmiss);
        LinearLayout ll = inflate.findViewById(R.id.ll);
        MyMediumTextView allday = inflate.findViewById(R.id.allday);
        begin_btn = (LinearLayout) inflate.findViewById(R.id.begin_btn);
        end_btn = (LinearLayout) inflate.findViewById(R.id.end_btn);
        begin_time = (MyNormalTextView) inflate.findViewById(R.id.begin_time);
        end_time = (MyNormalTextView) inflate.findViewById(R.id.end_time);
        begin = (MyNormalTextView) inflate.findViewById(R.id.begin);
        end = (MyNormalTextView) inflate.findViewById(R.id.end);
        mLoop1 = inflate.findViewById(R.id.year);
        mLoop1.setCyclic(false);
        mLoop2 = inflate.findViewById(R.id.month);
        mLoop3 = inflate.findViewById(R.id.day);


        ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                baseDialog.dismiss();
                list.clear();
                list2.clear();
                list3.clear();
            }
        });
        begin_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isCheckBegin = true;
                checkBegin();
            }
        });
        end_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isCheckBegin = false;
                checkEnd();
               /* //设置默认选中的年份
                mLoop1.setCurrentItem(curentYearId);
                mLoop2.setCurrentItem(nowMonthId);
                mLoop3.setCurrentItem(nowDateId);*/

            }
        });
        begin_time.setText(stimestr);
        end_time.setText(etimestr);
        checkBegin();
        for (int i = 0; i <= curentYear - beginYear; i++) {
            list.add(i + beginYear + "年");
        }
        for (int i = 0; i < 12; i++) {
            list2.add(i + 1 + "月");
        }
        //获取当前选中年份在集合的下标
        getCurenntYearId(curentYear);
        //获取当前选中月份在集合的下标
        getCurentMonthId(Calendar.getInstance().get(Calendar.MONTH) + 1);
        //初始化天
        initDay(beginYear, curentMonth);

        mLoop1.setAdapter(new ArrayWheelAdapter(list));
        mLoop2.setAdapter(new ArrayWheelAdapter(list2));
        mLoop3.setAdapter(new ArrayWheelAdapter(list3));

        mLoop1.setItemsVisibleCount(6);
        mLoop2.setItemsVisibleCount(6);
        mLoop3.setItemsVisibleCount(6);

        mLoop3.setLineSpacingMultiplier(2.0f);
        mLoop2.setLineSpacingMultiplier(2.0f);
        mLoop1.setLineSpacingMultiplier(2.0f);

        mLoop1.setDividerColor(Color.parseColor("#6EAAF8"));
        mLoop2.setDividerColor(Color.parseColor("#6EAAF8"));
        mLoop3.setDividerColor(Color.parseColor("#6EAAF8"));

        mLoop1.setTextColorCenter(Color.parseColor("#6EAAF8"));
        mLoop2.setTextColorCenter(Color.parseColor("#6EAAF8"));
        mLoop3.setTextColorCenter(Color.parseColor("#6EAAF8"));

        mLoop1.setTextColorOut(Color.parseColor("#999999"));
        mLoop2.setTextColorOut(Color.parseColor("#999999"));
        mLoop3.setTextColorOut(Color.parseColor("#999999"));


        mLoop1.setDividerWidth(DisplayInfoUtils.getInstance().dp2pxInt(1));
        mLoop2.setDividerWidth(DisplayInfoUtils.getInstance().dp2pxInt(1));
        mLoop3.setDividerWidth(DisplayInfoUtils.getInstance().dp2pxInt(1));


        //设置默认选中的年份
        mLoop1.setCurrentItem(curentYearId);

        mLoop2.setCurrentItem(nowMonthId);

        mLoop3.setCurrentItem(nowDateId);


            int time = getTime(begin_time.getText().toString().trim(), end_time.getText().toString().trim());
            allday.setText("共" + time + "天");

        mLoop1.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(int index) {
                if (isCheckBegin) {
                    String s = begin_time.getText().toString();
                    String replace = s.replace(s.substring(0, s.indexOf("年") + 1), list.get(index));
                    begin_time.setText(replace);
                    initDay(Integer.parseInt(replace.substring(0, replace.indexOf("年"))), getCheckMonth());
                } else {
                    String s = end_time.getText().toString();
                    String replace = s.replace(s.substring(0, s.indexOf("年") + 1), list.get(index));
                    end_time.setText(replace);
                    initDay(Integer.parseInt(replace.substring(0, replace.indexOf("年"))), getCheckMonth());
                }

                    int time = getTime(begin_time.getText().toString().trim(), end_time.getText().toString().trim());
                if (time<0){
                    AdiUtils.showToast("请选择正确的区间");
                    allday.setText("");
                }else{
                    allday.setText("共" + time + "天");
                }

            }
        });
        mLoop2.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(int index) {
                if (isCheckBegin) {
                    String s = begin_time.getText().toString();
                    String replace = s.replace(s.substring(s.indexOf("年") + 1, s.indexOf("月") + 1), list2.get(index));
                    begin_time.setText(replace);
                    String s1 = list2.get(index);
                    initDay(getCheckYear(), Integer.parseInt(s1.substring(0, s1.length() - 1)));
                } else {
                    String s = end_time.getText().toString();
                    String replace = s.replace(s.substring(s.indexOf("年") + 1, s.indexOf("月") + 1), list2.get(index));
                    end_time.setText(replace);
                    String s1 = list2.get(index);
                    initDay(getCheckYear(), Integer.parseInt(s1.substring(0, s1.length() - 1)));

                }
                int time = getTime(begin_time.getText().toString().trim(), end_time.getText().toString().trim());

                if (time<0){
                    AdiUtils.showToast("请选择正确的区间");
                    allday.setText("");
                }else{
                    allday.setText("共" + time + "天");
                }

            }
        });
        mLoop3.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(int index) {
                if (isCheckBegin) {
                    String s = begin_time.getText().toString();
                    String replace = s.replace(s.substring(s.indexOf("月") + 1, s.indexOf("日") + 1), list3.get(index));
                    begin_time.setText(replace);
                } else {
                    String s = end_time.getText().toString();
                    String replace = s.replace(s.substring(s.indexOf("月") + 1, s.indexOf("日") + 1), list3.get(index));
                    end_time.setText(replace);
                }

                    int time = getTime(begin_time.getText().toString().trim(), end_time.getText().toString().trim());
                    if (time<0){
                        AdiUtils.showToast("请选择正确的区间");
                        allday.setText("");
                    }else{
                        allday.setText("共" + time + "天");
                    }


            }
        });


        sure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String beginTime = begin_time.getText().toString();
                String endTime = end_time.getText().toString();
                AdiUtils.showToast(beginTime + "====" + endTime);
                baseDialog.dismiss();
            }
        });
        dismiss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                baseDialog.dismiss();
                list.clear();
                list2.clear();
                list3.clear();
            }
        });
        baseDialog = new BaseDialog(CountActivity.this, inflate, Gravity.BOTTOM);
        baseDialog.show();
    }

    private int getCurentMonthId(int i) {
        int i1 = Calendar.getInstance().get(Calendar.MONTH) + 1;
        for (int j = 0; j < 12; j++) {
            if (list2.get(j).equals(i1 + "月")) {
                nowMonthId = j;
                return j;
            }
        }
        return -1;
    }

    private int getCurenntYearId(int curentYear) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).equals(curentYear + "年")) {
                curentYearId = i;
                return i;
            }
        }
        return -1;
    }

    private int getCheckMonth() {
        String s1 = list2.get(mLoop2.getCurrentItem());
        int i = Integer.parseInt(s1.substring(0, s1.length() - 1));
        return i;
    }

    private int getCheckYear() {
        String s1 = list2.get(mLoop1.getCurrentItem());
        int i = Integer.parseInt(s1.substring(0, s1.length() - 1));
        return i;
    }

    //初始化日历数据
    private void initDay(int beginYear, int nowMonth) {
        list3.clear();
        boolean isRun = isRunNian(beginYear);
        Calendar nowCalendar = Calendar.getInstance();
        int nowDay = nowCalendar.get(Calendar.DAY_OF_MONTH);
        switch (nowMonth) {
            case 1:
            case 3:
            case 5:
            case 7:
            case 8:
            case 10:
            case 12:
                for (int day = 1; day <= 31; day++) {
                    list3.add(day + "日");
                    if (day == nowDay) {
                        nowDateId = list3.size() - 1;
                    }
                }
                break;
            case 2:
                if (isRun) {
                    for (int day = 1; day <= 29; day++) {
                        list3.add(day + "日");
                        if (day == nowDay) {
                            nowDateId = list3.size() - 1;
                        }
                    }
                } else {
                    for (int day = 1; day <= 28; day++) {
                        list3.add(day + "日");
                        if (day == nowDay) {
                            nowDateId = list3.size() - 1;
                        }
                    }
                }
                break;
            case 4:
            case 6:
            case 9:
            case 11:

                for (int day = 1; day <= 30; day++) {
                    list3.add(day + "日");
                    if (day == nowDay) {
                        nowDateId = list3.size() - 1;
                    }
                }
                break;
            default:
                break;
        }

    }

    /**
     * 判断是否是闰年
     *
     * @param year
     * @return
     */
    private boolean isRunNian(int year) {
        if (year % 4 == 0 && year % 100 != 0 || year % 400 == 0) {
            return true;
        } else {
            return false;
        }
    }


    private void checkBegin() {
        begin_time.setTextColor(Color.WHITE);
        begin.setTextColor(Color.WHITE);
        end_time.setTextColor(Color.parseColor("#666666"));
        end.setTextColor(Color.parseColor("#666666"));
        begin_btn.setBackgroundColor(ResUtils.getColor(CountActivity.this, R.color.blue_nomal));
        end_btn.setBackgroundColor(Color.parseColor("#f6f5f5"));
    }

    private void checkEnd() {
        end_time.setTextColor(Color.WHITE);
        end.setTextColor(Color.WHITE);
        begin_time.setTextColor(Color.parseColor("#666666"));
        begin.setTextColor(Color.parseColor("#666666"));
        end_btn.setBackgroundColor(ResUtils.getColor(CountActivity.this, R.color.blue_nomal));
        begin_btn.setBackgroundColor(Color.parseColor("#f6f5f5"));
    }

    private int getTime(String begin, String end) {
        Date date = DateUtil.strToDate(begin, CommonConstant.TFORMATE_year);
        Date date1 = DateUtil.strToDate(end, CommonConstant.TFORMATE_year);
       return getGapCount(date, date1);

    }


    public  int getGapCount(Date startDate, Date endDate) {
        Calendar fromCalendar = Calendar.getInstance();
        fromCalendar.setTime(startDate);
        fromCalendar.set(Calendar.HOUR_OF_DAY, 0);
        fromCalendar.set(Calendar.MINUTE, 0);
        fromCalendar.set(Calendar.SECOND, 0);
        fromCalendar.set(Calendar.MILLISECOND, 0);

        Calendar toCalendar = Calendar.getInstance();
        toCalendar.setTime(endDate);
        toCalendar.set(Calendar.HOUR_OF_DAY, 0);
        toCalendar.set(Calendar.MINUTE, 0);
        toCalendar.set(Calendar.SECOND, 0);
        toCalendar.set(Calendar.MILLISECOND, 0);
        return (int) ((toCalendar.getTime().getTime() - fromCalendar.getTime().getTime()) / (1000 * 60 * 60 * 24));
    }

}
