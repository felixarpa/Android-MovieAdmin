package idi.felixjulen.movieadmin.presentation.view.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import idi.felixjulen.movieadmin.R;
import idi.felixjulen.movieadmin.presentation.FragmentBaseViewController;

public class CharacterViewFragment extends Fragment {

    public static final String POSITION = "POSITION";
    public static final String ENTITY_ID = "ENTITY_ID";
    private RecyclerView recyclerView;
    private FragmentBaseViewController controller;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.recycler_view_fragment, container, false);

        RecyclerView recyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_view);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);

        //recyclerView.setAdapter(new CharacterRecyclerViewAdapter(null, this));
        controller = (FragmentBaseViewController) getActivity();

        return rootView;
    }
}