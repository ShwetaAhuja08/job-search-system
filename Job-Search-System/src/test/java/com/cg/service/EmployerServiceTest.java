package com.cg.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.cg.dao.EmployerSpringDataDAO;
import com.cg.dao.JobSpringDataDAO;
import com.cg.entity.Employer;

@ExtendWith(SpringExtension.class)
public class EmployerServiceTest {
	
	@TestConfiguration
    static class EmployerServiceImplTestContextConfiguration {
        @Bean
        public EmployerService employerService() {
            return new EmployerServiceSpringDataImpl();
        }

    }
	@Autowired
    private EmployerService employerService;
	
    @MockBean
    private EmployerSpringDataDAO employerSpringDataDAO;
    @MockBean
    private JobSpringDataDAO jobSpringDataDAO;
    
    
    @BeforeEach
    public void setUp() {
        Employer Capgemini = new Employer("Capgemini");
        Capgemini.setId(2);

        //Employer bob = new Employer("bob");
        //Employer alex = new Employer("alex");

        List<Employer> allEmployers = Arrays.asList(Capgemini);

        Mockito.when(employerSpringDataDAO.findByOrganizationName(Capgemini.getOrganizationName())).thenReturn(Capgemini);
        //Mockito.when(employerSpringDataDAO.findByOrganizationName(alex.getOrganizationName())).thenReturn(alex);
        Mockito.when(employerSpringDataDAO.findByOrganizationName("wrong_name")).thenReturn(null);
        Mockito.when(employerSpringDataDAO.findById(Capgemini.getId())).thenReturn(Optional.of(Capgemini));
        Mockito.when(employerSpringDataDAO.findAll()).thenReturn(allEmployers);
        Mockito.when(employerSpringDataDAO.findById(2)).thenReturn(Optional.empty());
    }
    
    @Test
    public void whenValidName_thenEmployeeShouldBeFound() {
        String name = "Capgemini";
        Employer found = employerService.getEmployerByName("Capgemini");

        assertThat(found.getOrganizationName()).isEqualTo(name);
    }

    @Test
    public void whenInValidName_thenEmployeeShouldNotBeFound() {
        Employer fromDb = employerSpringDataDAO.findByOrganizationName("wrong_name");
        assertThat(fromDb).isNull();

        //verifyFindByNameIsCalledOnce("wrong_name");
    }
}
