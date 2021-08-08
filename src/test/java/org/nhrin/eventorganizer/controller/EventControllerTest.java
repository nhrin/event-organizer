package org.nhrin.eventorganizer.controller;

import org.junit.jupiter.api.Test;
import org.nhrin.eventorganizer.entity.EventEntity;
import org.nhrin.eventorganizer.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Date;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@EnableAutoConfiguration(exclude = { SecurityAutoConfiguration.class })
public class EventControllerTest {

    @MockBean
    private EventService eventService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithMockUser(username="admin",roles={"ADMIN"})
    public void testSaveEvent() throws Exception {
        when(eventService.save(any())).thenReturn(new EventEntity());

        mockMvc.perform(post("/save-event"))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username="admin",roles={"ADMIN"})
    public void testAcceptEvent() throws Exception {
        when(eventService.acceptEvent(anyLong(), anyLong()))
                .thenReturn(new EventEntity());

        mockMvc.perform(get("/accept-event")
                        .param("eventId", "1")
                        .sessionAttr("userId", 1L))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username="admin",roles={"ADMIN"})
    public void testDeleteEvent() throws Exception {
        mockMvc.perform(get("/delete-event")
                        .param("eventId", "1"))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username="admin",roles={"ADMIN"})
    public void testGetEventDetails() throws Exception {
        EventEntity event = new EventEntity();
        event.setEventName("name");
        event.setEventPlace("place");
        event.setEventDate(new Date());
        event.setEventDescription("descr");
        when(eventService.findById(anyLong())).thenReturn(event);

        mockMvc.perform(get("/event-details")
                        .param("eventId", "1"))
                .andExpect(status().isOk());
    }
}
