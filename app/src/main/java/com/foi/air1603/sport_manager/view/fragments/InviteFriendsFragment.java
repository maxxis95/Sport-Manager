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
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;

import com.foi.air1603.sport_manager.R;
import com.foi.air1603.sport_manager.adapters.FriendsRecycleAdapter;
import com.foi.air1603.sport_manager.entities.User;
import com.foi.air1603.sport_manager.presenter.InviteFriendsPresenter;
import com.foi.air1603.sport_manager.presenter.InviteFriendsPresenterImpl;
import com.foi.air1603.sport_manager.view.InviteFriendsView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Karlo on 30.12.2016..
 */

public class InviteFriendsFragment extends Fragment implements InviteFriendsView {
    List<User> users = new ArrayList<User>();
    private InviteFriendsPresenter presenter;
    private Button btnAdd;
    private AutoCompleteTextView textView;
    private View view;
    private String[] USEREMAILS;
    private RecyclerView recyclerView;
    private FriendsRecycleAdapter friendsRecycleAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        getActivity().setTitle("Pozivanje prijatelja");
        View v = inflater.inflate(R.layout.fragment_invite_friends, null);
        return v;
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_friend_invites);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        this.view = view;
        btnAdd = (Button) view.findViewById(R.id.btnInviteFriends);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.loadUserByEmail(textView.getText().toString());
            }
        });

        presenter = new InviteFriendsPresenterImpl(this);
        presenter.loadAllUserEmails();

        // users.add(MainActivity.user);
        // users.add(MainActivity.user);
        friendsRecycleAdapter = new FriendsRecycleAdapter(users, this);
        recyclerView.setAdapter(friendsRecycleAdapter);
    }

    @Override
    public void initializeAutoComplete(Map<Integer, String> userEmails) {
        USEREMAILS = userEmails.values().toArray(new String[0]);

        //Inicijalizacija autocompleta
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(view.getContext(),
                android.R.layout.simple_dropdown_item_1line, USEREMAILS);
        textView = (AutoCompleteTextView)
                view.findViewById(R.id.actvInviteFriends);
        textView.setAdapter(adapter);
    }

    @Override
    public void addUserToInviteList(User user) {
        users.add(user);
        //friendsRecycleAdapter = new FriendsRecycleAdapter(users, this);
        //recyclerView.setAdapter(friendsRecycleAdapter);
        friendsRecycleAdapter.notifyDataSetChanged();
    }
}
