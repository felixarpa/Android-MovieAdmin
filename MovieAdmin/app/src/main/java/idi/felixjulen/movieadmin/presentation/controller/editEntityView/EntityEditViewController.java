package idi.felixjulen.movieadmin.presentation.controller.editEntityView;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import java.io.IOException;
import java.util.Date;

import idi.felixjulen.movieadmin.R;
import idi.felixjulen.movieadmin.domain.controller.FileManager;
import idi.felixjulen.movieadmin.domain.model.Entity;

public abstract class EntityEditViewController<T extends Entity> extends AppCompatActivity {

    private static final Integer TAKE_PICTURE_REQUEST = 10;
    private static final Integer PICK_IMAGE_REQUEST = 11;
    protected Integer layoutResourceId;
    protected Long id;
    protected T data;
    protected ImageView imageView;
    protected TextInputLayout nameTextInputLayout;
    protected EditText nameEditText;
    protected Bitmap image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layoutResourceId);

        imageView = (ImageView) findViewById(R.id.edit_image);
        nameTextInputLayout = (TextInputLayout) findViewById(R.id.edit_name_input);
        nameEditText = (EditText) findViewById(R.id.edit_name);
        Button saveButton = (Button) findViewById(R.id.save_button);
        Button cancelButton = (Button) findViewById(R.id.cancel_button);

        id = getIntent().getExtras().getLong(getString(R.string.itemEntityId), -1);
        if (id == -1) {
            data = newData();
            image = defaultImage();
        } else {
            data = getData(id);
            nameEditText.setText(data.getName());
            String name = data.getImage();
            image = FileManager.getInstance(this).loadImageFromStorage(name, R.mipmap.profile);
            imageView.setImageBitmap(image);
        }

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doSave();
            }
        });
        View.OnClickListener cancelAction = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        };
        cancelButton.setOnClickListener(cancelAction);
        registerForContextMenu(imageView);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openContextMenu(v);
            }
        });


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

    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, TAKE_PICTURE_REQUEST);
        }
    }

    private void dispatchPickImageIntent() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            if (requestCode == TAKE_PICTURE_REQUEST) {
                Bundle extras = data.getExtras();
                image = (Bitmap) extras.get("data");
                imageView.setImageBitmap(image);
            } else if (requestCode == PICK_IMAGE_REQUEST) {
                Uri uri = data.getData();
                try {
                    image = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
                    imageView.setImageBitmap(image);
                } catch (IOException ignored) {}
            }
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

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.camera_item:
                dispatchTakePictureIntent();
                break;

            case R.id.file_item:
                dispatchPickImageIntent();
                break;
        }

        return super.onContextItemSelected(item);
    }

    private void doSave() {
        if (nameEditText.getText().length() == 0) {
            nameTextInputLayout.setError(getString(R.string.empty_error));
        } else {
            if (id == -1) {
                data.setName(nameEditText.getText().toString());
                String name = "image" + new Date().getTime() + ".png";
                FileManager.getInstance(this).saveToInternalStorage(name, image);
                data.setImage(name);
                save();
            } else {
                data.setName(nameEditText.getText().toString());
                FileManager.getInstance(this).saveToInternalStorage(data.getImage(), image);
                update();
            }
        }
    }

    private Bitmap defaultImage() {
        return BitmapFactory.decodeResource(getResources(), R.mipmap.profile);
    }

    protected abstract void update();
    protected abstract void save();
    protected abstract T getData(Long id);
    protected abstract T newData();

}
