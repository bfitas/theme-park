package com.exemple;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.AutoConfigureDataMongo;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.exemple.model.ThemeParkRide;
import com.exemple.repository.ThemeParkRideRepository;

@AutoConfigureMockMvc
@AutoConfigureDataMongo
@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ThemeParkRideGradleApplicationTests {
    @Autowired
    private transient MockMvc mockMvc;

    @Autowired
    private transient ThemeParkRideRepository tprr;
    
    @BeforeAll
    public void setup(){
        tprr.save(ThemeParkRide.builder()
                                .id("thisisid1")
                                .name("Rollercoaster")
                                .description("Train ride that speeds you along.")
                                .thrillFactor(5)
                                .vomitFactor(3)
                                .build());
        
        tprr.save(ThemeParkRide.builder()
                                .id("thisisid2")
                                .name("Log flume")
                                .description("Boat ride with plenty of splashes.")
                                .thrillFactor(3)
                                .vomitFactor(2)
                                .build());

        tprr.save(ThemeParkRide.builder()
                                .id("thisisid3")
                                .name("Teacups")
                                .description("Spinning ride in a giant tea-cup.")
                                .thrillFactor(2)
                                .vomitFactor(4)
                                .build());
    }

    @Test
	public void addsNewRide() throws Exception {
        String newRide = "{\"name\":\"Monorail\",\"description\":\"Sedate travelling ride.\",\"thrillFactor\":2,\"vomitFactor\":1}";
        
        mockMvc.perform(MockMvcRequestBuilders.post("/ride")
                .contentType(MediaType.APPLICATION_JSON)
                .content(newRide)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
	}
	
    @Test
	public void getsAllRides() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/ride")
	            .accept(MediaType.APPLICATION_JSON))
	            .andExpect(status().isOk())
	            .andReturn();
	}
	
    @Test
	public void getsSingleRide() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/ride/thisisid1")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
	}

	@Test
	public void returnsNotFoundForInvalidSingleRide() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/ride/thisisid4")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andReturn();
	}
}
