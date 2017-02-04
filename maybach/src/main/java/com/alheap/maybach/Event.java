package com.alheap.maybach;

/**
 * Created by ALENHEART.APPS (ALHEAP)
 * <p>
 * This class represents an event that happens inside the application. Every event has a name
 * that identifies it. That name is used to trigger the event at runtime.
 *
 * @author Alex Kamenkov
 */
public abstract class Event<Data> {
    /**
     * A name of the event, used to identify and trigger it.
     */
    protected final String name;

    /**
     * Create a new Event with a provided name
     *
     * @param name a name of the event
     */
    protected Event(final String name) {
        this.name = name;
    }

    /**
     * This method is called when the event is triggered. It accepts an argument of a type that
     * was defined as a type of the event.
     *
     * @param data an object of a type that was defined at the event's creation. It should contain
     *             the data that the event needs to process and perform some actions on.
     */
    protected abstract void onEvent(Data data);
}