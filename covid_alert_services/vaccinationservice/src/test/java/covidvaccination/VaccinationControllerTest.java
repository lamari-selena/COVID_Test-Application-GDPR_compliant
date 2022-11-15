package covidvaccination;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import covidvaccination.controllers.VaccinationsController;
import covidvaccination.repositories.VaccinationRepository;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.junit.jupiter.api.Test;

@WebMvcTest(VaccinationsController.class)
@AutoConfigureMockMvc
public class VaccinationControllerTest {

    @MockBean
    private VaccinationRepository vaccinationRepository;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void shouldCreateMockMvc(){
        assertNotNull(mockMvc);
    }

}
