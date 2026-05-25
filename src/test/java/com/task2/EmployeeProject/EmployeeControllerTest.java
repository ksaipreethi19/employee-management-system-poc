package com.task2.EmployeeProject;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.task2.EmployeeProject.Controller.EmployeeController;
import com.task2.EmployeeProject.Model.EmployeeEntity;
import com.task2.EmployeeProject.Service.EmployeeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(EmployeeController.class)
@AutoConfigureMockMvc(addFilters = false)

public class EmployeeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EmployeeService employeeService;

    @Autowired
    private ObjectMapper objectMapper;

    private EmployeeEntity employee;

    @BeforeEach
    public void setup() {
        employee = new EmployeeEntity(1L, "Saipreethi", "Kothapelli", "ksai@capgemini.com", "CSE", "Java Developer", 26000.00);
    }

    @Test
    void testCreateEmployee() throws Exception {
        when(employeeService.createEmployee(any(EmployeeEntity.class))).thenReturn(employee);

        String response = mockMvc.perform(post("/EmployeeEntity/CreateEmployee")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(employee)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString();

        EmployeeEntity result = objectMapper.readValue(response, EmployeeEntity.class);

        assertEquals(employee.getEmpId(), result.getEmpId());
        verify(employeeService, times(1)).createEmployee(any(EmployeeEntity.class));
    }
    @Test
    void testUpdateEmployee() throws Exception {
        Long empId = 1L;
        when(employeeService.updateEmployee(eq(empId), any(EmployeeEntity.class))).thenReturn(employee);

        String response = mockMvc.perform(put("/EmployeeEntity/UpdateEmployee/{id}", empId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(employee)))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        EmployeeEntity result = objectMapper.readValue(response, EmployeeEntity.class);
        assertEquals(employee.getEmpId(), result.getEmpId());
        verify(employeeService, times(1)).updateEmployee(eq(empId), any(EmployeeEntity.class));
    }

    @Test
    void testGetEmployeeById() throws Exception {
        Long empId = 1L;
        when(employeeService.getEmployeeById(empId)).thenReturn(Optional.ofNullable(employee));

        String response = mockMvc.perform(get("/EmployeeEntity/Employee/{id}", empId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        EmployeeEntity result = objectMapper.readValue(response, EmployeeEntity.class);

        assertEquals(employee.getEmpId(), result.getEmpId());
        verify(employeeService, times(1)).getEmployeeById(empId);
    }

    @Test
    void testGetAllEmployees_Paginated() throws Exception {
        List<EmployeeEntity> employeeList = List.of(employee);
        Page<EmployeeEntity> employeePage = new PageImpl<>(employeeList, PageRequest.of(0, 10), 1);

        when(employeeService.getAllEmployees(any(PageRequest.class))).thenReturn(employeePage);

        String response = mockMvc.perform(get("/EmployeeEntity/AllEmployees")
                        .param("page", "0")
                        .param("size", "10")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        // Parse the paginated response and extract the content array
        JsonNode root = objectMapper.readTree(response);
        JsonNode contentNode = root.get("content");
        List<EmployeeEntity> result = objectMapper.readValue(
                contentNode.toString(),
                objectMapper.getTypeFactory().constructCollectionType(List.class, EmployeeEntity.class)
        );

        assertFalse(result.isEmpty());
        assertEquals(employeeList.size(), result.size());
        verify(employeeService, times(1)).getAllEmployees(any(PageRequest.class));
    }


    @Test
    void testDeleteEmployee() throws Exception {
        Long empId = 1L;
        doNothing().when(employeeService).deleteEmployee(empId);

        mockMvc.perform(delete("/EmployeeEntity/DeleteEmployee/{id}", empId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(employeeService, times(1)).deleteEmployee(empId);
    }
}
