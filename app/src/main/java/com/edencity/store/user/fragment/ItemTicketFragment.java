package com.edencity.store.user.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import java.util.ArrayList;

import com.edencity.store.R;
import com.edencity.store.entity.BaseDebug;
import com.edencity.store.user.adapter.ItemTicketListAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class ItemTicketFragment extends Fragment {


    private SmartRefreshLayout smart;
    private RecyclerView rlv;

    public ItemTicketFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View inflate = inflater.inflate(R.layout.fragment_item_ticket_history, container, false);
        initView(inflate);
        initLogic();
        return inflate;
    }

    private void initLogic() {
        ArrayList<BaseDebug> objects = new ArrayList<>();
        objects.add(new BaseDebug("房租-第三期","订单编号：2109471248324013958",
                "125000.00",true));
        objects.add(new BaseDebug("房租-第二期","订单编号：2542363426523421345",
                "125000.00",false));
        objects.add(new BaseDebug("房租-第一期","订单编号：2498128549125125321",
                "125000.00",false));
        rlv.setLayoutManager(new LinearLayoutManager(getContext()));

        ItemTicketListAdapter listAdapter = new ItemTicketListAdapter(1);
        rlv.setAdapter(listAdapter);
        listAdapter.addData(objects);
        listAdapter.select(new ItemTicketListAdapter.onSelect() {
            @Override
            public void select(int i) {
                listAdapter.mList.get(i).setChecked(!listAdapter.mList.get(i).isChecked());
                listAdapter.notifyItemChanged(i);
            }

            @Override
            public void onItemClick(int i) {

            }
        });
    }

    private void initView(View inflate) {

        smart = inflate.findViewById(R.id.smart);
        rlv = inflate.findViewById(R.id.rlv);
    }

}
