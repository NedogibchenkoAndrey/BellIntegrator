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
import ru.bellintegrator.task.view.user.UserFilterView;
import ru.bellintegrator.task.view.user.UserToSaveView;
import ru.bellintegrator.task.view.user.UserToUpdateView;

import java.sql.Date;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class UserIntegrationTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper objectMapper;

    private static final String LIST_SQL = "/api/user/list";
    private static final String BY_ID_SQL = "/api/user";
    private static final String SAVE_SQL = "/api/user/save";
    private static final String UPDATE_SQL = "/api/user/update";

    @Test
    public void findAllTest() throws Exception {
        UserFilterView userFilter = new UserFilterView();

        userFilter.setOfficeId(1);
        userFilter.setCitizenshipCode("056");
        userFilter.setDocCode("21");

        String jsonMapper = objectMapper.writeValueAsString(userFilter);

        mvc.perform(MockMvcRequestBuilders.post(LIST_SQL).contentType(MediaType.APPLICATION_JSON_VALUE).content(jsonMapper))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data").isNotEmpty())
                .andExpect(MockMvcResultMatchers.jsonPath("$.data").isArray())
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.[0].firstName").value("Иван"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.[0].position").value("Продавец"));
    }

    @Test
    public void findByIdTest() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get(BY_ID_SQL + "/1"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data").isNotEmpty())
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.firstName").value("Иван"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.position").value("Продавец"));
    }


    @Test
    public void addTest() throws Exception {
        UserToSaveView userSave = new UserToSaveView();

        userSave.setCitizenshipCode("056");
        userSave.setDocCode("21");
        userSave.setDocDate(new Date(2021 - 11 - 26));
        userSave.setDocName("Паспорт гражданина РФ");
        userSave.setDocNumber("35535353");
        userSave.setFirstName("Сергей");
        userSave.setMiddleName("Сергеев");
        userSave.setSecondName("Андреевич");
        userSave.setOfficeId(1);
        userSave.setPhone("5353535353");
        userSave.setPosition("Продавец");

        String jsonMapper = objectMapper.writeValueAsString(userSave);

        mvc.perform(MockMvcRequestBuilders.post(SAVE_SQL).contentType(MediaType.APPLICATION_JSON).content(jsonMapper))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$.data").isNotEmpty())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$.data.result").value("success"));

        mvc.perform(MockMvcRequestBuilders.get(BY_ID_SQL + "/8"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data").isNotEmpty())
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.firstName").value("Сергей"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.phone").value("5353535353"));
    }

    @Test
    public void updateTest() throws Exception {
        UserToUpdateView userUpdate = new UserToUpdateView();
        userUpdate.setId(3);
        userUpdate.setFirstName("Петрович");
        userUpdate.setPhone("8888888888");
        userUpdate.setPosition("Маркетолог");
        userUpdate.setCitizenshipCode("056");
        userUpdate.setDocDate(new Date(2021-11-25));
        userUpdate.setDocNumber("45109908");
        userUpdate.setMiddleName("Иванович");
        userUpdate.setOfficeId(1);
        userUpdate.setSecondName("Петрофан");


        String jsonMapper = objectMapper.writeValueAsString(userUpdate);

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
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.firstName").value("Петрович"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.phone").value("8888888888"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.position").value("Маркетолог"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.secondName").value("Петрофан"));

    }
}
