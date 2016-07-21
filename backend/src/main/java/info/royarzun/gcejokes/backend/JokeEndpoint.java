/*
   For step-by-step instructions on connecting your Android application to this backend module,
   see "App Engine Java Endpoints Module" template documentation at
   https://github.com/GoogleCloudPlatform/gradle-appengine-templates/tree/master/HelloEndpoints
*/

package info.royarzun.gcejokes.backend;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;
import com.udacity.gradle.jokes.JokeGenerator;


/** An endpoint class we are exposing */
@Api(
  name = "theJokesAPI",
  version = "v1",
  namespace = @ApiNamespace(
    ownerDomain = "backend.gcejokes.royarzun.info",
    ownerName = "backend.gcejokes.royarzun.info",
    packagePath=""
  )
)
public class JokeEndpoint {

    private JokeGenerator jokeGenerator = new JokeGenerator();

    @ApiMethod(name = "getMeSomeJokes")
    public Joke getMeSomeJokes() {
        Joke response = new Joke();
        response.setData(jokeGenerator.getJoke());

        return response;
    }

}
