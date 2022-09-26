package be.pxl.boardgame;

import be.pxl.boardgame.model.dto.BoardGameRequest;
import be.pxl.boardgame.repository.BoardGameRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@Testcontainers
@AutoConfigureMockMvc
public class BoardgameAppApplicationTests {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper mapper;
    @Autowired
    private BoardGameRepository boardGameRepository;

    @Container
    static MySQLContainer mySQLContainer =
            new MySQLContainer("mysql:5:7:37");

    @DynamicPropertySource
    static void registerMySQLProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", mySQLContainer::getJdbcUrl);
        registry.add("spring.datasource.username", mySQLContainer::getUsername);
        registry.add("spring.datasource.password", mySQLContainer::getPassword);
    }

    @Test
    public void addBoardGameTest() throws Exception {
        BoardGameRequest request = BoardGameRequest.builder()
                .name("Chess")
                .build();

        String requestString = mapper.writeValueAsString(request);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/boardgame")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestString))
                .andExpect(status().isCreated());

        Assertions.assertEquals(1, boardGameRepository.findAll().size());
    }

}
