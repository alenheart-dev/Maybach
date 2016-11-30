package com.alheap.maybach;

/**
 * Created by ALENHEART.APPS (ALHEAP)
 *
 * @author Alex Kamenkov
 */

public abstract class Event<Data> {
    protected final String name;

    protected Event(String name) {
        this.name = name;
    }

    protected abstract void onEvent(Data data);
}