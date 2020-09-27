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
public class ItemTicketHistoryFragment extends Fragment {

    private SmartRefreshLayout smart;
    private RecyclerView rlv;

    public ItemTicketHistoryFragment() {
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
        objects.add(new BaseDebug("已开票-电子发票","125000.00",
                "房租",true));
        objects.add(new BaseDebug("房租-第一期","125000.00",
                "定金",false));
        rlv.setLayoutManager(new LinearLayoutManager(getContext()));
        ItemTicketListAdapter listAdapter = new ItemTicketListAdapter(2);
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
