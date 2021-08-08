package org.nhrin.eventorganizer.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.nhrin.eventorganizer.dao.EventDAO;
import org.nhrin.eventorganizer.entity.EventEntity;

import java.util.Random;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class EventServiceTest {

    @Mock
    private EventDAO eventDAO;

    @InjectMocks
    private EventService eventService;


    @Test
    void testFindAllEvents() {
        long eventId = new Random().nextLong();
        final EventEntity eventEntity = new EventEntity();
        eventEntity.setEventId(eventId);
        eventEntity.setEventName("Some celebration");
        when(eventDAO.findById(eq(eventId))).thenReturn(eventEntity);

        final EventEntity result = eventService.findById(eventId);

        assertThat(result.getEventId()).isEqualTo(eventEntity.getEventId());
        assertThat(result.getEventName()).isEqualTo(eventEntity.getEventName());
    }
}
