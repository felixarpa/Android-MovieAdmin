package idi.felixjulen.movieadmin.presentation.controller.editEntityView;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.io.IOException;
import java.util.Date;

import idi.felixjulen.movieadmin.R;
import idi.felixjulen.movieadmin.domain.controller.FileManager;
import idi.felixjulen.movieadmin.domain.controller.ImageData;
import idi.felixjulen.movieadmin.domain.model.Entity;

public abstract class EntityEditViewController<T extends Entity> extends AppCompatActivity implements View.OnClickListener {

    private static final Integer TAKE_PICTURE_REQUEST = 10;
    private static final Integer PICK_IMAGE_REQUEST = 11;
    protected Integer layoutResourceId;
    protected Integer alternativeResourceId;
    protected Long id;
    protected T data;
    protected ImageView imageView;
    protected TextInputLayout nameTextInputLayout;
    protected EditText nameEditText;
    protected Bitmap image;
    private Boolean listenerDisabled;
    private ProgressBar progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layoutResourceId);

        progress = (ProgressBar) findViewById(R.id.progress_bar);
        imageView = (ImageView) findViewById(R.id.edit_image);
        nameTextInputLayout = (TextInputLayout) findViewById(R.id.edit_name_input);
        nameEditText = (EditText) findViewById(R.id.edit_name);
        Button saveButton = (Button) findViewById(R.id.save_button);
        Button cancelButton = (Button) findViewById(R.id.cancel_button);

        id = getIntent().getExtras().getLong(getString(R.string.itemEntityId), -1L);
        if (id == -1L) {
            data = newData();
            data.setImage("image" + new Date().getTime() + ".png");
            image = BitmapFactory.decodeResource(getResources(), alternativeResourceId);
        } else {
            data = getData(id);
            nameEditText.setText(data.getName());
            image = FileManager.getInstance(this).loadImageFromStorage(data.getImage(), alternativeResourceId);
        }
        imageView.setImageBitmap(image);

        saveButton.setOnClickListener(this);
        View.OnClickListener cancelAction = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listenerDisabled) {
                    Toast.makeText(getApplicationContext(), "Loading image", Toast.LENGTH_SHORT).show();
                } else {
                    onBackPressed();
                }
            }
        };
        cancelButton.setOnClickListener(cancelAction);
        registerForContextMenu(imageView);
        imageView.setOnClickListener(this);

        listenerDisabled = false;


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.no_text);
        setSupportActionBar(toolbar);

        Drawable arrowLeft = getDrawable(R.drawable.arrow_left);
        if(arrowLeft != null) {
            arrowLeft.mutate();
            arrowLeft.setColorFilter(getResources().getColor(R.color.md_white_1000), PorterDuff.Mode.SRC_ATOP);
            toolbar.setNavigationIcon(arrowLeft);
        }

        toolbar.setNavigationOnClickListener(cancelAction);

    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.choose_intent_menu, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        if (listenerDisabled) return super.onContextItemSelected(item);
        Intent intent;
        switch (item.getItemId()) {
            case R.id.camera_item:
                intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivityForResult(intent, TAKE_PICTURE_REQUEST);
                }
                break;

            case R.id.file_item:
                intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
                break;
        }

        return super.onContextItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            if (requestCode == TAKE_PICTURE_REQUEST) {
                Bundle extras = data.getExtras();
                image = (Bitmap) extras.get("data");
            } else if (requestCode == PICK_IMAGE_REQUEST) {
                Uri uri = data.getData();
                try {
                    image = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
                } catch (IOException ignored) {}
            }
            if (imageView != null) imageView.setImageBitmap(image);
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.edit_menu, menu);
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
            case R.id.save_item:
                doSave();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    protected void doSave() {
        if (nameEditText.getText().length() == 0) {
            nameTextInputLayout.setError(getString(R.string.empty_error));
        } else {
            data.setName(nameEditText.getText().toString());
            listenerDisabled = true;
            progress.setVisibility(View.VISIBLE);
            new SaveImageTask() {
                @Override
                protected void onPostExecute(Bitmap response) {
                    if (imageView != null) imageView.setImageBitmap(response);
                    if (progress != null) progress.setVisibility(View.GONE);
                    if (id == -1L) {
                        save();
                    } else {
                        update();
                    }
                }
            }.execute(ImageData.build(image, data.getImage()));
        }
    }

    @Override
    public void onClick(View v) {
        if (listenerDisabled) {
            Toast.makeText(getApplicationContext(), "Loading image", Toast.LENGTH_SHORT).show();
        } else {
            switch (v.getId()) {
                case R.id.save_button:
                    doSave();
                    break;

                case R.id.edit_image:
                    openContextMenu(v);
                    break;

            }
        }
    }

    protected abstract void update();
    protected abstract void save();
    protected abstract T getData(Long id);
    protected abstract T newData();

    private class SaveImageTask extends AsyncTask<ImageData, Void, Bitmap> {

        @Override
        protected Bitmap doInBackground(ImageData... params) {
            Bitmap image = params[0].getImage();
            String name = params[0].getName();
            FileManager.getInstance(getApplicationContext()).saveToInternalStorage(name, image);
            return image;

        }
    }

}
