package com.store_sample.store.app;

import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
public class HelloApiTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void helloText() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.get("/hello?name=Lavulite")
                        // 非推奨だがテストコードのため利用を許可
                        .accept(MediaType.APPLICATION_JSON_UTF8))
                        .andExpect(MockMvcResultMatchers.status().isOk())
                        .andExpect((result) -> JSONAssert.assertEquals("""
                        {
                          "message": "Hello, Lavulite"
                        }
                        """,
                        result.getResponse().getContentAsString(),
                        false));
    }
}
