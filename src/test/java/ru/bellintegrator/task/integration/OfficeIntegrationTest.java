package ru.bellintegrator.task.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import ru.bellintegrator.task.view.office.OfficeFilterView;
import ru.bellintegrator.task.view.office.OfficeToSaveView;
import ru.bellintegrator.task.view.office.OfficeToUpdateView;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class OfficeIntegrationTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper objectMapper;

    private static final String LIST_URL = "/api/office/list";
    private static final String BY_ID_SQL = "/api/office";
    private static final String SAVE_SQL = "/api/office/save";
    private static final String UPDATE_SQL = "/api/office/update";

    @Test
    public void findAllTest() throws Exception {
        OfficeFilterView officeFilter = new OfficeFilterView();

        officeFilter.setOrgId(1);
        officeFilter.setPhone("89641238855");
        String jsonMapper = objectMapper.writeValueAsString(officeFilter);

        mvc.perform(MockMvcRequestBuilders.post(LIST_URL).contentType(MediaType.APPLICATION_JSON_VALUE).content(jsonMapper))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$.data").isNotEmpty())
                .andExpect(jsonPath("$.data").isArray())
                .andExpect(jsonPath("$.data.[0].name").value("Магазин"))
                .andExpect(jsonPath("$.data.[0].phone").value("89641238855"));
    }

    @Test
    public void findByIdTest() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get(BY_ID_SQL + "/1"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$.data").isNotEmpty())
                .andExpect(jsonPath("$.data.name").value("Магазин"))
                .andExpect(jsonPath("$.data.phone").value("89641238855"));

    }

    @Test
    public void addTest() throws Exception {
        OfficeToSaveView officeSave = new OfficeToSaveView();

        officeSave.setOrgId(1);
        officeSave.setName("Юредический офис");
        officeSave.setAddress("г.Москва, ул. Васелецкого, дом 5");
        officeSave.setPhone("86565653453");
        officeSave.setIsActive(true);

        String jsonMapper = objectMapper.writeValueAsString(officeSave);

        mvc.perform(MockMvcRequestBuilders.post(SAVE_SQL).contentType(MediaType.APPLICATION_JSON).content(jsonMapper))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data").isNotEmpty())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$.data.result").value("success"));

        mvc.perform(MockMvcRequestBuilders.get(BY_ID_SQL + "/8"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$.data").isNotEmpty())
                .andExpect(jsonPath("$.data.name").value("Юредический офис"))
                .andExpect(jsonPath("$.data.phone").value("86565653453"));
    }

    @Test
    public void updateTest() throws Exception {
        OfficeToUpdateView officeUpdate = new OfficeToUpdateView();

        officeUpdate.setId(3);
        officeUpdate.setName("ОфисМаг");
        officeUpdate.setAddress("г.Москва, ул. Васелецкого, дом 5");
        officeUpdate.setPhone("89648787103");
        officeUpdate.setIsActive(true);

        String jsonMapper = objectMapper.writeValueAsString(officeUpdate);

        mvc.perform(MockMvcRequestBuilders.post(UPDATE_SQL).contentType(MediaType.APPLICATION_JSON).content(jsonMapper))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data").isNotEmpty())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$.data.result").value("success"));

        mvc.perform(MockMvcRequestBuilders.get(BY_ID_SQL + "/3"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$.data").isNotEmpty())
                .andExpect(jsonPath("$.data.name").value("ОфисМаг"))
                .andExpect(jsonPath("$.data.phone").value("89648787103"));
    }
}
