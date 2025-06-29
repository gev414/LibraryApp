package com.libraryapp.models;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Abstract user class that provides a base for all other roles such as Member, Admin, Librarian etc.
 * Provides common functionality and behavior
 */

public abstract class User {
    /**
     * Thread safe ID counter.
     */
    private static final AtomicInteger idCounter = new AtomicInteger(0);

    protected final int id;
    protected final String name;

    public User(String name){
        this.id= idCounter.incrementAndGet();
        this.name = name;
    }

    public int getId() { return id; }
    public String getName() { return name; }

    public abstract void doAction();
}
