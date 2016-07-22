
package com.udacity.gradle.builditbigger;

import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.concurrent.ExecutionException;


@RunWith(AndroidJUnit4.class)
public class AsyncTaskTest {
    @Test
    public void testAsyncTaskWithNull(){
        String testString = null;
        try {
            testString = new JokeAsyncTask(InstrumentationRegistry.getTargetContext()).execute().get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        Assert.assertNotNull(testString);
    }

}
