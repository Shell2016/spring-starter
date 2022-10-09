package ru.michaelshell.spring.listener;

import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
public class EntityListener {

    @EventListener(condition = "args[0].accessType.name() == 'READ'")
//    @Order(10)         чем меньше тем раньше срабатывает
    public void acceptEntity(EntityEvent entityEvent) {
        System.out.println("Entity: " + entityEvent);
    }
}
