package com.alheap.maybach.sample;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.alheap.maybach.Maybach;

/**
 * Created by ALENHEART.APPS (ALHEAP)
 *
 * @author Alex Kamenkov
 */

public class SampleReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Maybach.get().triggerEvent(Constants.SAMPLE_EVENT, true);
    }
}
