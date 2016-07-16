package info.royarzun.gcejokes.backend;

/** The object model for the data we are sending through endpoints */
public class Joke {

    private String mJokeString;

    public String getData() {
        return mJokeString;
    }

    public void setData(String data) {
        mJokeString = data;
    }
}