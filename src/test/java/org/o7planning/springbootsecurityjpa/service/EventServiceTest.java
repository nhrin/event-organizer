package org.o7planning.springbootsecurityjpa.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.o7planning.springbootsecurityjpa.dao.EventDAO;
import org.o7planning.springbootsecurityjpa.entity.AppEvent;

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
        final AppEvent appEvent = new AppEvent();
        appEvent.setEventId(eventId);
        appEvent.setEventName("Some celebration");
        when(eventDAO.findById(eq(eventId))).thenReturn(appEvent);

        final AppEvent result = eventService.findById(eventId);

        assertThat(result.getEventId()).isEqualTo(appEvent.getEventId());
        assertThat(result.getEventName()).isEqualTo(appEvent.getEventName());
    }
}
