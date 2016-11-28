package com.alheap.maybach;

import android.util.Log;

import java.util.HashMap;

/**
 * Created by ALENHEART.APPS (ALHEAP)
 *
 * @author Alex Kamenkov
 */

/**
 * This class have some cool and easy to use features
 * to connect your activities, fragments, broadcast receivers and
 * services by sign up for events in one side and trigger them on another.
 */
public class Maybach {
    private static Maybach sInstance = new Maybach();
    private HashMap<Object, HashMap<String, Event>> mReferenceMap = new HashMap<>();
    private final String TAG = getClass().getSimpleName();

    /**
     * Getting the instance to Maybach.
     */
    public static Maybach get() {
        return sInstance;
    }

    /**
     * Sign up your reference object (activity for example), to event.
     * Each event has its unique name, by this name we are going to trigger it later.
     *
     * @param reference object, reference to activity for example.
     * @param event     the event, by calling his name we can trigger it.
     */
    public void signUpForEvent(Object reference, Event event) {
        if (mReferenceMap.containsKey(reference)) {
            mReferenceMap.get(reference).put(event.name, event);
        } else {
            HashMap<String, Event> mapOfEvents = new HashMap<>();
            mapOfEvents.put(event.name, event);
            mReferenceMap.put(reference, mapOfEvents);
        }
    }

    /**
     * Unregister an event from the reference.
     *
     * @param reference object, reference to activity for example.
     * @param event     the event that should be unregistered.
     */
    public void unregisterEvent(Object reference, Event event) {
        unregisterEvent(reference, event.name);
    }

    /**
     * Unregister an event (by name) from the reference.
     *
     * @param reference object, reference to activity for example.
     * @param eventName event with this name should be unregistered.
     */
    public void unregisterEvent(Object reference, String eventName) {
        if (mReferenceMap.containsKey(reference)) {
            HashMap<String, Event> mapOfEvents = mReferenceMap.get(reference);
            if (mapOfEvents.containsKey(eventName)) {
                mapOfEvents.remove(eventName);
                if (mapOfEvents.isEmpty()) {
                    mReferenceMap.remove(reference);
                }
            }
        }
    }

    /**
     * Trigger an event by its name.
     *
     * @param eventName event with this name should be triggered.
     * @param data      object that should be passed to the event.
     */
    public void triggerEvent(String eventName, Object data) {
        for (HashMap<String, Event> mapOfEvents : mReferenceMap.values()) {
            if (mapOfEvents.containsKey(eventName)) {
                try {
                    //noinspection unchecked
                    mapOfEvents.get(eventName).onEvent(data);
                } catch (ClassCastException e) {
                    Log.e(TAG, e.getMessage());
                }
            }
        }
    }

    /**
     * Unregister all events from the reference.
     *
     * @param reference object, reference to activity for example.
     */
    public void unregisterClassEvents(Object reference) {
        mReferenceMap.remove(reference);
    }
}
