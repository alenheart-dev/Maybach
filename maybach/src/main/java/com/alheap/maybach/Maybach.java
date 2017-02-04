package com.alheap.maybach;

import android.util.Log;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by ALENHEART.APPS (ALHEAP)
 * <p>
 * This class provides an ability to transfer data across activities, fragments, services and
 * broadcast receivers inside an application.
 * <p>
 * Objects that are supposed to wait for something to happen somewhere else inside the application,
 * sigh up for the events they are interested in, and wait for them to happen asynchronously.
 * Objects that know when such events happen, trigger them by name passing along the relevant data.
 * Objects that are no longer interested in the events that they signed up for, can
 * unregister the event and stop waiting for it.
 *
 * @author Alex Kamenkov
 */
public class Maybach {
    /**
     * A single instance of this class
     */
    private static Maybach sInstance = new Maybach();

    /**
     * A map that holds references to signed up objects ant the events that they are signed up for
     */
    private Map<Object, Map<String, Event>> mReferenceMap = new HashMap<>();

    /**
     * This tag is used for debugging
     */
    private final String TAG = getClass().getSimpleName();

    /**
     * Get the only instance of this class
     */
    public static Maybach get() {
        return sInstance;
    }

    /**
     * Add a new event and associate it with a provided object reference (activity, fragment...).
     * The event should have a unique name by which it can be triggered later.
     *
     * @param reference an object that is associated with an event (activity, fragment, etc)
     * @param event     an event that may be called later by its unique name
     */
    public void signUpForEvent(Object reference, Event event) {
        if (mReferenceMap.containsKey(reference)) {
            mReferenceMap.get(reference).put(event.name, event);
        } else {
            Map<String, Event> mapOfEvents = new HashMap<>();
            mapOfEvents.put(event.name, event);
            mReferenceMap.put(reference, mapOfEvents);
        }
    }

    /**
     * Remove an event subscription for a provided object reference.
     *
     * @param reference an object that is associated with an event (activity, fragment, etc)
     * @param event     an event that is associated with a provided object
     */
    public void unregisterEvent(Object reference, Event event) {
        unregisterEvent(reference, event.name);
    }

    /**
     * Remove an event subscription for a provided object reference given the event's name.
     *
     * @param reference an object that is associated with an event (activity, fragment, etc)
     * @param eventName an event that is associated with a provided object
     */
    public void unregisterEvent(Object reference, String eventName) {
        if (mReferenceMap.containsKey(reference)) {
            Map<String, Event> mapOfEvents = mReferenceMap.get(reference);
            if (mapOfEvents.containsKey(eventName)) {
                mapOfEvents.remove(eventName);
                if (mapOfEvents.isEmpty()) {
                    mReferenceMap.remove(reference);
                }
            }
        }
    }

    /**
     * Trigger an event by its name. Objects associated with the event will execute their event's
     * onEvent methods, receiving a provided data object as an argument.
     *
     * @param eventName a unique name of an event that should be triggered
     * @param data      a data object that should be passed to an event
     */
    public void triggerEvent(String eventName, Object data) {
        for (Map<String, Event> mapOfEvents : mReferenceMap.values()) {
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
     * Remove all event subscriptions for a provided object reference (activity, fragment, etc).
     *
     * @param reference an object that its subscriptions should be removed (activity, fragment, etc)
     */
    public void unregisterClassEvents(Object reference) {
        mReferenceMap.remove(reference);
    }
}
