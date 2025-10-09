package ru.vtb;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.vtb.configuration.ConnectionProperties;
import ru.vtb.configuration.HikaryPoolConfig;
import ru.vtb.controller.impl.LimitControllerImpl;
import ru.vtb.dto.LimitDTO;
import ru.vtb.dto.LimitOperationDto;
import ru.vtb.dto.LimitOperationResponseDto;
import ru.vtb.enums.TypeOperation;
import ru.vtb.services.LimitService;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = LimitControllerImpl.class)
@Import({HikaryPoolConfig.class, ConnectionProperties.class}) // needs EntityManager
@AutoConfigureMockMvc
public class LimitControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean


    private LimitService taskService;

    private LimitDTO limitDTO;
    private LimitOperationResponseDto limitOperationResponseDto;

    @BeforeEach
    public void setup() {
        limitDTO = new LimitDTO();
        limitDTO.setUserId(3L);
        limitDTO.setAmount(new BigDecimal("10000"));
        limitDTO.setOperDate(LocalDate.now());

        limitOperationResponseDto = new LimitOperationResponseDto(UUID.randomUUID().toString());
    }

    @Test
    public void testGetAllTasks() throws Exception {
        when(taskService.getActualLimit(3L)).thenReturn(limitDTO);
        mockMvc.perform(MockMvcRequestBuilders.get("/limits/actual").queryParam("userId", "3"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.userId").value(3L))
                .andExpect(jsonPath("$.amount").value("10000"));
    }

    public void testLimitOperation() throws Exception {
        LimitOperationDto limitOperationDto = new LimitOperationDto();
        limitOperationDto.setTypeOperation(TypeOperation.INCREASE);
        limitOperationDto.setUserId(3);
        limitOperationDto.setAmount(new BigDecimal("100"));
        when(taskService.action(3L, TypeOperation.INCREASE, new BigDecimal("100"))).thenReturn(limitOperationResponseDto);
        mockMvc.perform(MockMvcRequestBuilders.post("/limits")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(limitOperationDto)))
                .andExpect(status().isOk());
    }
//
//    @Test
//    public void testGetTaskById() throws Exception {
//        when(taskService.getTaskById(1L)).thenReturn(taskDto);
//
//        mockMvc.perform(MockMvcRequestBuilders.get("/tasks/1"))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.name").value("Test Task"));
//    }
//
//    @Test
//    public void testGetTasksByStatus() throws Exception {
//        when(taskService.getTasksByStatus(TaskStatus.NEW)).thenReturn(List.of(taskDto));
//
//        mockMvc.perform(MockMvcRequestBuilders.get("/tasks/status/NEW"))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$[0].status").value("NEW"));
//    }
//
//    @Test
//    public void testAddTask() throws Exception {
//        doNothing().when(taskService).createTask(any(TaskDto.class));
//
//        String json = "{" +
//                "\"name\": \"New Task\"," +
//                "\"status\": \"NEW\"}";
//
//        mockMvc.perform(MockMvcRequestBuilders.post("/tasks")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(json))
//                .andExpect(status().isOk());
//    }
//
//    @Test
//    public void testChangeTaskStatus() throws Exception {
//        doNothing().when(taskService).changeTaskStatus(1L, TaskStatus.DONE);
//
//        mockMvc.perform(MockMvcRequestBuilders.put("/tasks/1?taskStatus=DONE"))
//                .andExpect(status().isOk());
//    }
//
//    @Test
//    public void testDeleteTask() throws Exception {
//        doNothing().when(taskService).deleteTaskById(1L);
//
//        mockMvc.perform(MockMvcRequestBuilders.delete("/tasks/1"))
//                .andExpect(status().isOk());
//    }

    private static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}