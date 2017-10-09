package com.firozanwar.sample.asynctaskloader;

import android.content.Context;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.AsyncTaskLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<String> {

    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (TextView) findViewById(R.id.textView);
    }

    public void executeAsyncTak(View view) {
        //getSupportLoaderManager().initLoader(0,null,this).forceLoad();
        getSupportLoaderManager().restartLoader(0, null, this).forceLoad();
    }

    public void clear(View view) {

    }

    @Override
    public Loader<String> onCreateLoader(int id, Bundle args) {
        textView.append("Careting Loader " + "\n");
        return new MyAsycnTaskLoader(this);
    }

    @Override
    public void onLoadFinished(Loader<String> loader, String data) {
        textView.append("Loader finished, retured:: " + data + "\n");
    }

    @Override
    public void onLoaderReset(Loader<String> loader) {

    }

    private static class MyAsycnTaskLoader extends AsyncTaskLoader<String> {


        public MyAsycnTaskLoader(Context context) {
            super(context);
        }

        @Override
        public String loadInBackground() {

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "From the loader";
        }

        /**
         * Used when you want to modify data before data retured to onLoadFinished()
         *
         * @param data
         */
        @Override
        public void deliverResult(String data) {
            data += ",deliveed";
            super.deliverResult(data);


        }
    }
}
