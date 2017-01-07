package idi.felixjulen.movieadmin.presentation;

import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import idi.felixjulen.movieadmin.R;


public abstract class EntityViewController extends AppCompatActivity {

    protected Long id;
    protected Boolean enableNavigation;
    protected Long clickedEntityId;
    protected Integer layoutResourceId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layoutResourceId);

        id = getIntent().getExtras().getLong(getString(R.string.itemEntityId), -1);
        if (id == -1) finish();
        enableNavigation = getIntent().getExtras().getBoolean(getString(R.string.enable_navigation), false);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.no_text);
        setSupportActionBar(toolbar);

        Drawable arrowLeft = getDrawable(R.drawable.arrow_left);
        if(arrowLeft != null) {
            arrowLeft.mutate();
            arrowLeft.setColorFilter(getResources().getColor(R.color.md_white_1000), PorterDuff.Mode.SRC_ATOP);
            toolbar.setNavigationIcon(arrowLeft);
        }

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        setUpEntity();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.entity_menu, menu);
        for(int i = 0; i < menu.size(); i++){
            Drawable drawable = menu.getItem(i).getIcon();
            if(drawable != null) {
                drawable.mutate();
                drawable.setColorFilter(getResources().getColor(R.color.md_white_1000), PorterDuff.Mode.SRC_ATOP);
            }
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.remove_item:
                removeEntity();
                break;

            case R.id.edit_iem:
                editEntity();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    protected abstract void setUpEntity();
    protected abstract void editEntity();
    protected abstract void removeEntity();

}
