package idi.felixjulen.movieadmin.presentation.controller.singleEntityView;

import android.content.DialogInterface;
import android.graphics.Bitmap;
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
import idi.felixjulen.movieadmin.domain.controller.FileManager;
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

        id = getIntent().getExtras().getLong(getString(R.string.itemEntityId), -1L);
        if (id == -1L) finish();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.no_text);
        setSupportActionBar(toolbar);

        Drawable arrowLeft = getDrawable(R.drawable.arrow_left);
        if (arrowLeft != null) {
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

    }

    @Override
    protected void onResume() {
        super.onResume();

        data = getData();

        TextView nameView = (TextView) findViewById(R.id.name);
        nameView.setText(data.getName());

        ImageView imageView = (ImageView) findViewById(R.id.image);
        Bitmap image = FileManager.getInstance(this).loadImageFromStorage(data.getImage(), R.mipmap.profile);
        imageView.setImageBitmap(image);

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

            case R.id.edit_item:
                editEntity();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    protected abstract void editEntity();
    protected abstract void removeEntity();
    protected abstract T getData();

}
