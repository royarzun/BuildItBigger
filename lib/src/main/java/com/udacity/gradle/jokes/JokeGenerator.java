package com.udacity.gradle.jokes;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Random;


public class JokeGenerator {
    private static final String JOKE_KEY = "joke";

    private JSONArray availableJokes;

    public JokeGenerator(){
        try {
            InputStream data = this.getClass().getClassLoader().getResourceAsStream("jokes-EN.json");
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(data));

            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = bufferedReader.readLine()) != null)
                sb.append(line);

            availableJokes = new JSONArray(sb.toString());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getJoke() {
        try {
            Random random = new Random();
            int index = random.nextInt(availableJokes.length());
            JSONObject jokeObject = availableJokes.getJSONObject(index);

            return jokeObject.getString(JOKE_KEY);
        } catch (NullPointerException e) {
            System.out.print(availableJokes.length());
            return null;
        }
    }

}
