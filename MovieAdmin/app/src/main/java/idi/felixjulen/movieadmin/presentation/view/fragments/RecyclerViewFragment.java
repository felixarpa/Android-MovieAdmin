package idi.felixjulen.movieadmin.presentation.view.fragments;

import android.content.ContentValues;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;
import android.widget.TextView;

import java.util.ArrayList;

import idi.felixjulen.movieadmin.R;
import idi.felixjulen.movieadmin.domain.controller.CharacterData;
import idi.felixjulen.movieadmin.domain.model.Character;
import idi.felixjulen.movieadmin.presentation.FragmentBaseViewController;
import idi.felixjulen.movieadmin.presentation.adapter.CharacterRecyclerViewAdapter;
import idi.felixjulen.movieadmin.presentation.callback.OnRecyclerViewItemClick;

public class RecyclerViewFragment extends Fragment implements OnRecyclerViewItemClick, SearchView.OnQueryTextListener {

    public static final String POSITION = "POSITION";
    public static final String ENTITY_ID = "ENTITY_ID";
    private FragmentBaseViewController controller;
    private CharacterData data;
    private RecyclerView recyclerView;
    private TextView emptyTextView;
    private CharacterRecyclerViewAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        data = CharacterData.getInstance(getContext());
        data.makeDefault();

        View rootView = inflater.inflate(R.layout.recycler_view_fragment, container, false);

        recyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_view);
        emptyTextView = (TextView) rootView.findViewById(R.id.empty);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);

        setListContent(null);

        controller = (FragmentBaseViewController) getActivity();

        return rootView;
    }

    private void setListContent(String filter) {
        ArrayList<Character> characters = (filter != null) ? data.search(filter) : data.list();
        adapter = new CharacterRecyclerViewAdapter(characters, this);
        if (characters.size() == 0) {
            emptyTextView.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.GONE);
        } else {
            emptyTextView.setVisibility(View.GONE);
            recyclerView.setVisibility(View.VISIBLE);
            recyclerView.setAdapter(adapter);
        }
    }

    @Override
    public void onRecyclerViewItemClick(Integer position, Long itemEntityId) {
        ContentValues values = new ContentValues();
        values.put(FragmentBaseViewController.FRAGMENT_NAME, "RecyclerViewFragment");
        values.put(POSITION, position);
        values.put(ENTITY_ID, itemEntityId);
        controller.onFragmentAction(values);
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        setListContent(query);
        return true;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        setListContent(newText);
        return true;
    }
}