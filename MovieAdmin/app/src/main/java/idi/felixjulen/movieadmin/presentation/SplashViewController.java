package idi.felixjulen.movieadmin.presentation;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import idi.felixjulen.movieadmin.R;
import idi.felixjulen.movieadmin.domain.controller.CharacterData;
import idi.felixjulen.movieadmin.domain.controller.DirectorData;
import idi.felixjulen.movieadmin.domain.controller.FilmData;
import idi.felixjulen.movieadmin.presentation.listViews.MovieListViewController;

public class SplashViewController extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_view);

        SharedPreferences sharedPreferences = getSharedPreferences(BaseViewController.sharedPreferencesName, 0);
        Boolean firstUsage = sharedPreferences.getBoolean(BaseViewController.firstUsage, true);
        if (firstUsage) {
            CharacterData.getInstance(this).makeDefault();
            DirectorData.getInstance(this).makeDefault();
            FilmData.getInstance(this).makeDefault();
        }
        sharedPreferences.edit().putBoolean(BaseViewController.firstUsage, false).apply();

        new CountDownTimer(1500, 1500) {
            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish() {
                enterApplication();
            }
        }.start();
    }

    private void enterApplication() {
        startActivity(new Intent(this, MovieListViewController.class));
        finish();
    }

    @Override
    public void onBackPressed() {
        Toast.makeText(this, "Starting Movie Admin", Toast.LENGTH_SHORT).show();
    }
}
