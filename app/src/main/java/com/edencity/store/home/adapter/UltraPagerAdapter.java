package com.edencity.store.home.adapter;

import android.support.constraint.ConstraintLayout;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import com.edencity.store.R;


// Created by Ardy on 2020/3/1.
public class UltraPagerAdapter extends PagerAdapter {
    private boolean isMultiScr;
    private ArrayList<Integer> objects;

    public UltraPagerAdapter(boolean isMultiScr, ArrayList<Integer> objects) {
        this.isMultiScr = isMultiScr;
        this.objects = objects;
    }

    @Override
    public int getCount() {
        return objects.size()==0?0:objects.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View inflate = LayoutInflater.from(container.getContext()).inflate(R.layout.layout_child, null);

        ImageView img = inflate.findViewById(R.id.img);
        Glide.with(container.getContext()).load(objects.get(position)).into(img);
        /*linearLayout.setId(R.id.item_id);*/

        container.addView(inflate);
//        linearLayout.getLayoutParams().width = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 180, container.getContext().getResources().getDisplayMetrics());
//        linearLayout.getLayoutParams().height = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 400, container.getContext().getResources().getDisplayMetrics());
        return inflate;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        ConstraintLayout view = (ConstraintLayout) object;
        container.removeView(view);
    }
}
