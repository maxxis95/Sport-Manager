package com.foi.air1603.sport_manager.view.fragments;

import android.app.Fragment;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.foi.air1603.sport_manager.MainActivity;
import com.foi.air1603.sport_manager.R;
import com.foi.air1603.sport_manager.adapters.FriendsRecycleAdapter;
import com.foi.air1603.sport_manager.entities.User;
import com.foi.air1603.sport_manager.view.InviteFriendsView;

import java.net.URI;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Karlo on 30.12.2016..
 */

public class InviteFriendsFragment extends Fragment implements InviteFriendsView {

    private RecyclerView recyclerView;
    List<User> users = new ArrayList<User>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_invite_friends, null);
        return v;
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_friend_invites);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));


        users.add(MainActivity.user);
        users.add(MainActivity.user);
        recyclerView.setAdapter(new FriendsRecycleAdapter(users, this));
    }
}
