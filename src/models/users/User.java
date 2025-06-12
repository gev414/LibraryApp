package models.users;

import java.util.concurrent.atomic.AtomicInteger;

public abstract class User {
    private static final AtomicInteger idCounter = new AtomicInteger(0);

    protected final int id;
    protected String name;

    public User(String name){
        this.id= idCounter.incrementAndGet();
        this.name = name;
    }

    public int getId() { return id; }
    public String getName() { return name; }

    public abstract void doAction();
}
