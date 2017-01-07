package idi.felixjulen.movieadmin.presentation.listViews;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.MenuItemCompat;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SearchView;

import java.util.ArrayList;

import idi.felixjulen.movieadmin.R;
import idi.felixjulen.movieadmin.domain.controller.CharacterData;
import idi.felixjulen.movieadmin.domain.model.Character;
import idi.felixjulen.movieadmin.presentation.singleEntityViews.CharacterViewController;

public class CharacterSearchViewController extends EntityListViewController<Character> implements SearchView.OnQueryTextListener {

    private SearchView searchView;
    private String searchText = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        layoutResourceId = R.layout.entity_list_view;
        rowLayoutResourceId = R.layout.entity_row_layout;
        titleResourceId = R.string.character_search;
        super.onCreate(savedInstanceState);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.add);
        fab.setImageResource(R.drawable.account_plus);
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
        searchText = query;
        setListContent();
        return true;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        searchText = newText;
        setListContent();
        return true;
    }

    @Override
    protected void addEntity() {

    }

    @Override
    protected ArrayList<Character> loadData() {
        return CharacterData.getInstance(this).search(searchText);
    }

    @Override
    protected Class<?> entityActivity() {
        return CharacterViewController.class;
    }
}
