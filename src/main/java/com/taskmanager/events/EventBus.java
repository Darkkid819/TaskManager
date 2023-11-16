package com.taskmanager.events;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.function.Consumer;

public class EventBus {

    private final Map<Class<?>, Set<Consumer<Object>>> subscribers = new HashMap<>();

    public <T> void subscribe(Class<T> eventType, Consumer<T> listener) {
        subscribers.computeIfAbsent(eventType, k -> new HashSet<>()).add((Consumer<Object>) listener);
    }

    public <T> void unsubscribe(Class<T> eventType, Consumer<T> listener) {
        Set<Consumer<Object>> eventSubscribers = subscribers.get(eventType);
        if (eventSubscribers != null) {
            eventSubscribers.remove(listener);
        }
    }
    
    public <T> void publish(T event) {
        Class<?> eventType = event.getClass();
        Set<Consumer<Object>> eventSubscribers = subscribers.get(eventType);
        if (eventSubscribers != null) {
            for (Consumer<Object> listener : eventSubscribers) {
                listener.accept(event);
            }
        }
    }
}
