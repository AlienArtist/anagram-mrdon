package com.mrdon.anagram.controller;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
class FrontEndControllerTest {
    @Autowired
    private MockMvc mvc;

    @Test
    void getAnagrams() throws Exception{
        this.mvc
                .perform(MockMvcRequestBuilders.get("/anagrams/iceman"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    void getAnagramsBadString() throws Exception{
        this.mvc
                .perform(MockMvcRequestBuilders.get("/anagrams/ice-man"))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    void isAnagramEP() throws Exception{
        this.mvc
                .perform(MockMvcRequestBuilders.get("/anagrams/iceman/cinema"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(content().string(FrontEndController.ARE_ANAGRAMS_TRUE));
        this.mvc
                .perform(MockMvcRequestBuilders.get("/anagrams/iceman/mandrake"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().string(FrontEndController.ARE_ANAGRAMS_FALSE));
    }


    @Test
    void isAnagramBadString() throws Exception{
        this.mvc
                .perform(MockMvcRequestBuilders.get("/anagrams/iceman/cin*ema"))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }
}