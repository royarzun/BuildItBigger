package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;

import java.io.IOException;

import info.royarzun.gcejokes.backend.theJokesAPI.TheJokesAPI;
import info.royarzun.gcejokes.backend.theJokesAPI.model.Joke;
import info.royarzun.jokesandroidlib.JokeActivity;

/**
 * Created by royarzun on 16-07-16.
 */
public class JokeAsyncTask extends AsyncTask<Void, Void, String> {
    private TheJokesAPI myApiService = null;
    private Context mContext;

    public JokeAsyncTask(Context context) {
        mContext = context;
    }

    @Override
    protected void onPostExecute(String joke) {
        super.onPostExecute(joke);
        Intent intent = new Intent(mContext, JokeActivity.class);
        intent.putExtra(JokeActivity.EXTRA_JOKE, joke);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        mContext.startActivity(intent);
    }

    @Override
    protected String doInBackground(Void...params) {
        if(myApiService == null) {
            TheJokesAPI.Builder builder = new TheJokesAPI.Builder(AndroidHttp.newCompatibleTransport(),
                    new AndroidJsonFactory(), null)
                    .setRootUrl("http://10.0.2.2:8080/_ah/api/")
                    .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                        @Override
                        public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest) throws IOException {
                            abstractGoogleClientRequest.setDisableGZipContent(true);
                        }
                    });

            myApiService = builder.build();
        }

        try {
            return myApiService.getMeSomeJokes().execute().getData();
        } catch (IOException e) {
            return e.getMessage();
        }
    }
}