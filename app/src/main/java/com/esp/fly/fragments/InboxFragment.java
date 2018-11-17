package com.esp.fly.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.esp.fly.R;
import com.esp.fly.adapters.InboxAdapter;
import com.esp.fly.models.Conservation;
import com.esp.fly.models.User;

import java.util.List;

public class InboxFragment extends Fragment {

    private View rootView;
    private RecyclerView recyclerView;
    private InboxAdapter adapter;
    private User currentUser;
    private List<Conservation> conservations;
    private LinearLayoutManager layoutManager;
    private TextView noDataView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_inbox, container, false);
        recyclerView = rootView.findViewById(R.id.inbox_recycler_view);
        noDataView = rootView.findViewById(R.id.no_conservation);
        currentUser = User.getInstance();
        if (currentUser != null) {
            conservations = currentUser.getConservations();
            if (conservations != null) {
                adapter = new InboxAdapter(getContext(), conservations);
                recyclerView.setAdapter(adapter);
                noDataView.setVisibility(View.GONE);
            } else {
                noDataView.setVisibility(View.VISIBLE);
            }
        } else {
            noDataView.setVisibility(View.VISIBLE);
        }
        layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        return rootView;
    }

}
