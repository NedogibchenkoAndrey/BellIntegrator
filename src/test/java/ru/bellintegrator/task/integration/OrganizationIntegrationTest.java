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
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import ru.bellintegrator.task.view.organization.OrganizationFilterView;
import ru.bellintegrator.task.view.organization.OrganizationToSaveView;
import ru.bellintegrator.task.view.organization.OrganizationToUpdateView;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class OrganizationIntegrationTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper objectMapper;

    private static final String LIST_SQL = "/api/org/list";
    private static final String BY_ID_SQL = "/api/org";
    private static final String SAVE_SQL = "/api/org/save";
    private static final String UPDATE_SQL = "/api/org/update";

    @Test
    public void findAllTest() throws Exception {
        OrganizationFilterView organizationFilter = new OrganizationFilterView();

        organizationFilter.setName("ООО Автозапчасти");

        String jsonMapper = objectMapper.writeValueAsString(organizationFilter);

        mvc.perform(MockMvcRequestBuilders.post(LIST_SQL).contentType(MediaType.APPLICATION_JSON_VALUE).content(jsonMapper))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data").isNotEmpty())
                .andExpect(MockMvcResultMatchers.jsonPath("$.data").isArray())
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.[0].id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.[0].name").value("ООО Автозапчасти"));
    }

    @Test
    public void findByIdTest() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get(BY_ID_SQL + "/1"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data").isNotEmpty())
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.name").value("ООО Автозапчасти"));

    }

    @Test
    public void addTest() throws Exception {
        OrganizationToSaveView organizationSave = new OrganizationToSaveView();

        organizationSave.setAddress("г.Москва");
        organizationSave.setName("ООО Зубр");
        organizationSave.setInn("784535363");
        organizationSave.setFullName("Зубр");
        organizationSave.setKpp("764854745");
        organizationSave.setPhone("8565286528");
        organizationSave.setIsActive(true);

        String jsonMapper = objectMapper.writeValueAsString(organizationSave);

        mvc.perform(MockMvcRequestBuilders.post(SAVE_SQL).contentType(MediaType.APPLICATION_JSON).content(jsonMapper))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$.data").isNotEmpty())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$.data.result").value("success"));

        mvc.perform(MockMvcRequestBuilders.get(BY_ID_SQL + "/4"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data").isNotEmpty())
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.name").value("ООО Зубр"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.phone").value("8565286528"));
    }

    @Test
    public void updateTest() throws Exception {

        OrganizationToUpdateView organizationUpdate = new OrganizationToUpdateView();

        organizationUpdate.setId(3);
        organizationUpdate.setName("Nissan");
        organizationUpdate.setFullName("Салон Авто");
        organizationUpdate.setInn("157836375292");
        organizationUpdate.setKpp("475632789");
        organizationUpdate.setAddress("г.Самара, шоссе Автомобилиста");
        organizationUpdate.setPhone("89273954131");
        organizationUpdate.setIsActive(true);

        String jsonMapper = objectMapper.writeValueAsString(organizationUpdate);

        mvc.perform(MockMvcRequestBuilders.post(UPDATE_SQL).contentType(MediaType.APPLICATION_JSON).content(jsonMapper))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$.data").isNotEmpty())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$.data.result").value("success"));

        mvc.perform(MockMvcRequestBuilders.get(BY_ID_SQL + "/3"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data").isNotEmpty())
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.name").value("Nissan"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.fullName").value("Салон Авто"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.address").value("г.Самара, шоссе Автомобилиста"));

    }
}



