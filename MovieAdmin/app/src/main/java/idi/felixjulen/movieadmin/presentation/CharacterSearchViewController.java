package idi.felixjulen.movieadmin.presentation;

import android.content.ContentValues;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SearchView;

import idi.felixjulen.movieadmin.R;
import idi.felixjulen.movieadmin.presentation.adapter.PagerViewAdapter;
import idi.felixjulen.movieadmin.presentation.view.SwipeViewPager;
import idi.felixjulen.movieadmin.presentation.view.fragments.CharacterViewFragment;
import idi.felixjulen.movieadmin.presentation.view.fragments.RecyclerViewFragment;

public class CharacterSearchViewController extends FragmentBaseViewController {

    private SearchView searchView;
    private RecyclerViewFragment recyclerViewFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentFrame(R.layout.character_search_view);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(R.string.character_search);
        }

        recyclerViewFragment = new RecyclerViewFragment();
        CharacterViewFragment characterViewFragment = new CharacterViewFragment();

        SwipeViewPager pager = (SwipeViewPager) findViewById(R.id.pager);
        //if (pager == null) return;
        pager.setAdapter(new PagerViewAdapter(getSupportFragmentManager(), recyclerViewFragment, characterViewFragment));

        pager.setCurrentItem(0);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search, menu);

        MenuItem menuItem = menu.findItem(R.id.search_item);
        searchView = (SearchView) MenuItemCompat.getActionView(menuItem);
        searchView.setOnQueryTextListener(recyclerViewFragment);

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
    public void onFragmentAction(ContentValues values) {

    }
}
