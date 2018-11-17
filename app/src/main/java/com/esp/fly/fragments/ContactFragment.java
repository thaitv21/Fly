package com.esp.fly.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.esp.fly.R;
import com.esp.fly.adapters.ContactAdapter;
import com.esp.fly.models.Friend;
import com.esp.fly.models.User;

import java.util.List;

public class ContactFragment extends Fragment {


    private View rootView;
    private RecyclerView recyclerView;
    private ContactAdapter adapter;
    private LinearLayoutManager layoutManager;
    private User currentUser;
    private List<Friend> friendList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       rootView = inflater.inflate(R.layout.fragment_contact, container, false);
        recyclerView = rootView.findViewById(R.id.contact_recycler_view);
        currentUser = User.getInstance();
        if (currentUser != null) {
            friendList = currentUser.getFriends();
            if (friendList != null) {
                adapter = new ContactAdapter(getContext(), friendList);
                recyclerView.setAdapter(adapter);
            }
        }
        layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        return rootView;
    }

}
