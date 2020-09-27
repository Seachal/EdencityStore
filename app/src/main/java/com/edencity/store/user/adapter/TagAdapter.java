package com.edencity.store.user.adapter;

import android.view.View;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.edencity.store.custum.flowlayout.FlowLayout;
import com.edencity.store.entity.BaseDebug;

public abstract class TagAdapter<T>
{
    private List<BaseDebug> mTagDatas;
    private OnDataChangedListener mOnDataChangedListener;

    public TagAdapter(List<BaseDebug> datas)
    {
        mTagDatas = datas;
    }

    public TagAdapter(BaseDebug[] datas)
    {
        mTagDatas = new ArrayList<BaseDebug>(Arrays.asList(datas));
    }

    static interface OnDataChangedListener
    {
        void onChanged();
    }

    void setOnDataChangedListener(OnDataChangedListener listener)
    {
        mOnDataChangedListener = listener;
    }

    public List<BaseDebug> getSelectList(){
        ArrayList<BaseDebug> objects = new ArrayList<>();
        for (int i = 0; i < mTagDatas.size(); i++) {
            boolean checked = mTagDatas.get(i).isChecked();
            if (checked){
                objects.add(mTagDatas.get(i));
            }
        }
        return objects;
    }
    public int getCount()
    {
        return mTagDatas == null ? 0 : mTagDatas.size();
    }

    public void notifyDataChanged()
    {
        mOnDataChangedListener.onChanged();
    }

    public BaseDebug getItem(int position)
    {
        return mTagDatas.get(position);
    }

    public abstract View getView(FlowLayout parent, int position, BaseDebug t);

}
