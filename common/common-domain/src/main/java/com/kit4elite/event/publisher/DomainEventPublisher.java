package com.kit4elite.event.publisher;

import com.kit4elite.event.DomainEvent;

public interface DomainEventPublisher<T extends DomainEvent> {

    void publish(T event);
}
