package idi.felixjulen.movieadmin.presentation;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.TextView;

import java.util.ArrayList;

import idi.felixjulen.movieadmin.R;
import idi.felixjulen.movieadmin.domain.controller.CharacterData;
import idi.felixjulen.movieadmin.domain.model.Character;
import idi.felixjulen.movieadmin.presentation.adapter.CharacterRecyclerViewAdapter;
import idi.felixjulen.movieadmin.presentation.callback.OnRecyclerViewItemAction;

public class CharacterSearchViewController extends BaseViewController implements OnRecyclerViewItemAction, SearchView.OnQueryTextListener {

    private SearchView searchView;
    private RecyclerView recyclerView;
    private TextView emptyTextView;
    private CharacterData data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentFrame(R.layout.recycler_view_fragment);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(R.string.character_search);
        }

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        emptyTextView = (TextView) findViewById(R.id.empty);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        findViewById(R.id.add_character).setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                    }
                }
        );
    }

    @Override
    protected void onResume() {
        data = CharacterData.getInstance(this);
        setListContent(null);
        super.onResume();
    }

    private void setListContent(String filter) {
        ArrayList<Character> characters = (filter != null) ? data.search(filter) : data.list();
        CharacterRecyclerViewAdapter adapter = new CharacterRecyclerViewAdapter(characters, this);
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
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search, menu);

        MenuItem menuItem = menu.findItem(R.id.search_item);
        searchView = (SearchView) MenuItemCompat.getActionView(menuItem);
        searchView.setOnQueryTextListener(this);

        int searchCloseButtonId = searchView.getContext().getResources()
                .getIdentifier("android:id/search_close_btn", null, null);
        ImageView closeButton = (ImageView) searchView.findViewById(searchCloseButtonId);

        closeButton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        int searchCloseButtonId = searchView.getContext().getResources()
                                .getIdentifier("android:id/search_src_text", null, null);
                        EditText et = (EditText) searchView.findViewById(searchCloseButtonId);

                        if (et != null) {
                            String text = et.getText().toString();
                            et.setText("");
                            if (text.equals("")) {
                                searchView.onActionViewCollapsed();

                            }
                        }
                    }
                }
        );

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    protected Integer getMenuPosition() {
        return 1;
    }

    @Override
    protected Integer getMenuId() {
        return R.id.character_search_item;
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

    @Override
    public void onRecyclerViewItemClick(Long itemEntityId) {
        Intent intent = new Intent(this, CharacterViewController.class);
        intent.putExtra(getString(R.string.itemEntityId), itemEntityId);
        intent.putExtra(getString(R.string.enable_navigation), true);
        startActivity(intent);
    }
}
