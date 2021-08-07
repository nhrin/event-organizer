package org.o7planning.springbootsecurityjpa.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.o7planning.springbootsecurityjpa.dao.EventDAO;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class EventServiceTest {

    @Mock
    private EventDAO eventDAO;

    @InjectMocks
    private EventService eventService;


    @Test
    void testFindAllEvents() {
        }
}
