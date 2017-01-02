package idi.felixjulen.movieadmin.presentation.view.fragments;

import android.content.ContentValues;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import idi.felixjulen.movieadmin.R;
import idi.felixjulen.movieadmin.presentation.FragmentBaseViewController;
import idi.felixjulen.movieadmin.presentation.adapter.CharacterRecyclerViewAdapter;
import idi.felixjulen.movieadmin.presentation.callback.OnRecyclerViewItemClick;

public class RecyclerViewFragment extends Fragment implements OnRecyclerViewItemClick {

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

        recyclerView.setAdapter(new CharacterRecyclerViewAdapter(null, this));
        controller = (FragmentBaseViewController) getActivity();

        return rootView;
    }

    @Override
    public void onRecyclerViewItemClick(Integer position, Long itemEntityId) {
        ContentValues values = new ContentValues();
        values.put(FragmentBaseViewController.FRAGMENT_NAME, "RecyclerViewFragment");
        values.put(POSITION, position);
        values.put(ENTITY_ID, itemEntityId);
        controller.onFragmentAction(values);
    }
}