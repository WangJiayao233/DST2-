package cn.edu.zju.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class BiomedControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @WithMockUser(value = "zju")
    @Test
    public void testDrugLabels() throws Exception {
        this.mockMvc.perform(get("/drugLabels")).andDo(print()).andExpect(status().isOk());
    }

    @WithMockUser(value = "zju")
    @Test
    public void testDrugs() throws Exception {
        this.mockMvc.perform(get("/drugs")).andDo(print()).andExpect(status().isOk());
    }

    @WithMockUser(value = "zju")
    @Test
    public void testDosingGuideline() throws Exception {
        this.mockMvc.perform(get("/dosingGuideline")).andDo(print()).andExpect(status().isOk());
    }

    @WithMockUser(value = "zju")
    @Test
    public void testIndex() throws Exception {
        this.mockMvc.perform(get("/")).andDo(print()).andExpect(status().isOk());
    }
}
