package idi.felixjulen.movieadmin.presentation;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.FrameLayout;

import idi.felixjulen.movieadmin.R;

public abstract class BaseViewController extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    protected Toolbar toolbar;
    protected ActionBarDrawerToggle toggle;
    protected DrawerLayout drawerLayout;

    private final Integer[] CHECKED_ICONS = new Integer[] {
            R.drawable.movie,
            R.drawable.account,
            R.drawable.settings,
            R.drawable.help_circle,
            R.drawable.information
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.base_view);
        setUpViews();
    }

    private void setUpViews() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        toggle = new ActionBarDrawerToggle(
                this, drawerLayout, toolbar, R.string.openDrawer, R.string.closeDrawer);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(this);

        Integer position = getMenuPosition();
        Integer accentColor = ContextCompat.getColor(getApplicationContext(), R.color.colorAccent);

        Drawable icon = getDrawable(CHECKED_ICONS[position]);
        assert icon != null;
        icon.setColorFilter(accentColor, PorterDuff.Mode.SRC_IN);

        navigationView.getMenu().getItem(position).setIcon(icon);
        navigationView.getMenu().getItem(position).setChecked(true);

    }

    public void setContentFrame(int layoutResID) {
        DrawerLayout drawerLayout = (DrawerLayout) getLayoutInflater().inflate(R.layout.base_view, null);
        FrameLayout frameLayout = (FrameLayout) drawerLayout.findViewById(R.id.main_frame);
        getLayoutInflater().inflate(layoutResID, frameLayout, true);
        super.setContentView(drawerLayout);
        setUpViews();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Intent intent = null;

        switch (item.getItemId()) {
            case R.id.movie_list_item:
                intent = new Intent(this, MovieListViewController.class);
                break;

            case R.id.character_search_item:
                intent = new Intent(this, CharacterSearchViewController.class);
                break;

            case R.id.settings_item:
                intent = new Intent(this, SettingsViewController.class);
                break;

            case R.id.help_item:
                intent = new Intent(this, HelpViewController.class);
                break;

            case R.id.about_item:
                intent = new Intent(this, AboutViewController.class);
                break;
        }

        if (intent != null && item.getItemId() != getMenuId()) {
            startActivity(intent);
            finish();
        }

        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    protected abstract Integer getMenuPosition();
    protected abstract Integer getMenuId();

}
