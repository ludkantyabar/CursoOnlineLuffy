package com.dev.CursoOnline;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;


@SpringBootTest
@AutoConfigureMockMvc
public class AllConsultTest {

    @Autowired
    MockMvc mockMvc;

    private String generateTestToken() {
        // TODO: Implement token generation or use a static valid token for testing
        return "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJsdWRrYW50QGVtYWlsLmNvbSIsInJvbGUiOiJBRE1JTiIsImlhdCI6MTc2MDM2ODk2NSwiZXhwIjoxNzYyOTYwOTY1fQ.LX6A7rTnzrgJibtbiCKQBXC56b_BxZOfYO0Af7BysAg";
    }

    
    @Test
    public void testConsultarRoles() throws Exception {
        mockMvc.perform(get("/roles")
                .header("Authorization", "Bearer " + generateTestToken()))
            .andExpect(status().isOk())
            .andExpect(content().contentType("application/json"))
            .andExpect(jsonPath("$").isArray());
    }

    @Test
    public void testConsultarUsuarios() throws Exception {
        mockMvc.perform(get("/usuarios")
                .header("Authorization", "Bearer " + generateTestToken()))
            .andExpect(status().isOk())
            .andExpect(content().contentType("application/json"))
            .andExpect(jsonPath("$").isArray());
    }
    

    @Test
    public void testConsultarCursoRegistros() throws Exception {
        mockMvc.perform(get("/cursoregistros")
                .header("Authorization", "Bearer " + generateTestToken()))
            .andExpect(status().isOk())
            .andExpect(content().contentType("application/json"))
            .andExpect(jsonPath("$").isArray());
    }

   
    @Test
	public void testConsultarCursos() throws Exception {
		mockMvc.perform(get("/cursos")
				.header("Authorization", "Bearer " + generateTestToken()))
			.andExpect(status().isOk())
			.andExpect(content().contentType("application/json"))
			.andExpect(jsonPath("$").isArray());
	}
    
    
    @Test
	public void testConsultarCategorias() throws Exception {
		mockMvc.perform(get("/categorias")
				.header	("Authorization", "Bearer " + generateTestToken()))
			.andExpect(status().isOk())
			.andExpect(content().contentType("application/json"))
			.andExpect(jsonPath("$").isArray());
    }
   
    
}