package idi.felixjulen.movieadmin.presentation;

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

import idi.felixjulen.movieadmin.R;

public abstract class BaseViewController extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    protected Toolbar toolbar;
    private DrawerLayout drawerLayout;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.base_view);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawerLayout, toolbar, R.string.openDrawer, R.string.closeDrawer);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(this);

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    protected abstract int getPosition();

    @Override
    protected void onResume() {
        super.onResume();
        int iconColor = ContextCompat.getColor(getApplicationContext(), R.color.colorAccent);

    }

//    private Drawable getIcon(int index, boolean checked) {
//
//        Drawable icon;
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//            icon = getDrawable((checked) ? CHECKED_ICONS[index] : ICONS[index]);
//        } else {
//            icon = getResources().getDrawable((checked) ? CHECKED_ICONS[index] : ICONS[index]);
//        }
//
//        int tabIconColor = ContextCompat.getColor(getApplicationContext(), (checked) ? R.color.colorAccent : R.color.md_black_1000);
//        icon.setColorFilter(tabIconColor, PorterDuff.Mode.SRC_IN);
//
//        return icon;
//    }
}
