package idi.felixjulen.movieadmin.presentation.singleEntityViews;

import android.content.DialogInterface;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import idi.felixjulen.movieadmin.R;
import idi.felixjulen.movieadmin.domain.controller.FilmData;
import idi.felixjulen.movieadmin.domain.model.Entity;


public abstract class EntityViewController<T extends Entity> extends AppCompatActivity {

    protected Long id;
    protected Long clickedEntityId;
    protected Integer layoutResourceId;
    protected T data;
    protected Integer removeTitle;
    protected Integer removeMessage;
    protected Integer removeYes;
    protected Integer removeNo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layoutResourceId);

        id = getIntent().getExtras().getLong(getString(R.string.itemEntityId), -1);
        if (id == -1) finish();

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

        data = getData();

        FilmData.getInstance(this).get(id);

        TextView nameView = (TextView) findViewById(R.id.name);
        nameView.setText(data.getName());

        if (data.getImage() != null) {
            ImageView imageView = (ImageView) findViewById(R.id.image);
            imageView.setImageBitmap(data.getImage());
        }
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
                new AlertDialog.Builder(this)
                        .setTitle(removeTitle)
                        .setMessage(removeMessage)
                        .setPositiveButton(
                                removeYes,
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        removeEntity();
                                        finish();
                                    }
                                })
                        .setNeutralButton(
                                removeNo,
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.dismiss();
                                    }
                                })
                        .show();
                break;

            case R.id.edit_iem:
                editEntity();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    protected abstract void editEntity();
    protected abstract void removeEntity();
    protected abstract T getData();

}
