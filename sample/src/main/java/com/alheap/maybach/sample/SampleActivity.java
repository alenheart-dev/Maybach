package com.alheap.maybach.sample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.alheap.maybach.Event;
import com.alheap.maybach.Maybach;

/**
 * Created by ALENHEART.APPS (ALHEAP)
 *
 * @author Alex Kamenkov
 */

public class SampleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sample);
    }

    private final Event<Boolean> sampleEvent = new Event<Boolean>(Constants.SAMPLE_EVENT) {
        @Override
        protected void onEvent(Boolean aBoolean) {

        }
    };

    @Override
    public void onStart() {
        super.onStart();
        Maybach.get().signUpForEvent(this, sampleEvent);
    }

    @Override
    public void onStop() {
        super.onStop();
        Maybach.get().unregisterClassEvents(this);
    }
}
