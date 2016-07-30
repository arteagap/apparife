package com.example.raul.apparife;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ProgressBar;

/**
 * Created by raul on 21/07/2016.
 */
public class splash extends AppCompatActivity {

    public static final int MAXIMO_CUENTA = 100;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash);

        Bgsplashtask bgsplashtask=new Bgsplashtask();
        bgsplashtask.execute(MAXIMO_CUENTA);
    }

    private class Bgsplashtask extends AsyncTask<Integer,Integer,Integer>
    {
        ProgressBar pg;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pg=(ProgressBar) findViewById(R.id.pgsplah);
        }

        @Override
        protected Integer doInBackground(Integer... params) {

            int maximo=params[0];
            int i=0;
            while (i<=maximo){
                try {
                    Thread.sleep(15);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                int trabajorealizado=i;
                publishProgress(trabajorealizado);
                i = i + 1;
            }
            return 0;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            pg.setProgress(values[0]);
        }

        @Override
        protected void onPostExecute(Integer integer) {
            super.onPostExecute(integer);
            Intent intent=new Intent(splash.this, MainActivity.class);
            startActivity(intent);
            finish();
        }
    }
}
