package idi.felixjulen.movieadmin.presentation.controller;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import idi.felixjulen.movieadmin.R;
import idi.felixjulen.movieadmin.domain.controller.CharacterData;
import idi.felixjulen.movieadmin.domain.controller.DirectorData;
import idi.felixjulen.movieadmin.domain.controller.FilmData;
import idi.felixjulen.movieadmin.presentation.controller.listView.MovieListViewController;

public class SplashViewController extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_view);

        new MakeDefaultTask() {
            @Override
            protected void onPostExecute(Long millis) {
                countDown(millis);
            }
        }.execute();

    }

    private void countDown(Long millis) {
        if (millis == 1000L) {
            findViewById(R.id.progress).setVisibility(View.GONE);
            findViewById(R.id.progress_text).setVisibility(View.GONE);
        }
        new CountDownTimer(millis, millis) {
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
        Toast.makeText(this, "Launching Movie Admin", Toast.LENGTH_SHORT).show();
    }

    private class MakeDefaultTask extends AsyncTask<Void, Void, Long> {

        @Override
        protected Long doInBackground(Void... params) {
            SharedPreferences sharedPreferences = getSharedPreferences(BaseViewController.sharedPreferencesName, 0);
            Boolean firstUsage = sharedPreferences.getBoolean(BaseViewController.firstUsage, true);
            if (firstUsage) {
                CharacterData.getInstance(getApplicationContext()).makeDefault();
                DirectorData.getInstance(getApplicationContext()).makeDefault();
                FilmData.getInstance(getApplicationContext()).makeDefault();
                sharedPreferences.edit().putBoolean(BaseViewController.firstUsage, false).apply();
                return 500L;
            } else {
                return 1000L;
            }
        }

    }
}
