package com.dev.CursoOnline;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;

import java.util.Collections;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import com.dev.CursoOnline.controller.AuthController;
import com.dev.CursoOnline.controller.CursoController;
import com.dev.CursoOnline.controller.CursoRegistroController;
import com.dev.CursoOnline.controller.RoleController;
import com.dev.CursoOnline.controller.UsuarioController;
import com.dev.CursoOnline.model.Curso;
import com.dev.CursoOnline.model.CursoRegistro;
import com.dev.CursoOnline.model.Role;
import com.dev.CursoOnline.model.Usuario;
import com.dev.CursoOnline.repository.RoleRepository;
import com.dev.CursoOnline.security.JwtUtil;
import com.dev.CursoOnline.service.CursoRegistroService;
import com.dev.CursoOnline.service.CursoService;
import com.dev.CursoOnline.service.RoleService;
import com.dev.CursoOnline.service.UsuarioService;

@WebMvcTest({
     CursoController.class,
     UsuarioController.class,
     RoleController.class,
     CursoRegistroController.class,
     AuthController.class
})
public class AllControllersTest {

 @Autowired
 private MockMvc mockMvc;

 // CursoController dependencies
 @MockBean
 private CursoService cursoService;

 // UsuarioController dependencies
 @MockBean
 private UsuarioService usuarioService;

 // RoleController dependencies
 @MockBean
 private RoleService roleService;

 // CursoRegistroController dependencies
 @MockBean
 private CursoRegistroService cursoRegistroService;

 // AuthController dependencies
 @MockBean
 private JwtUtil jwtUtil;
 @MockBean
 private PasswordEncoder passwordEncoder;
 @MockBean
 private RoleRepository roleRepository;

    // Métodos auxiliares para crear objetos de prueba
    private Curso crearCurso(Long id) {
        Curso curso = new Curso();
        curso.setId(id);
        return curso;
    }
    private Usuario crearUsuario(Long id, String email) {
        Usuario usuario = new Usuario();
        usuario.setId(id);
        usuario.setEmail(email);
        return usuario;
    }
    private Role crearRole(Long id, String nombre) {
        Role role = new Role();
        role.setId(id);
        role.setNombre(nombre);
        return role;
    }
    private CursoRegistro crearCursoRegistro(Long id) {
        CursoRegistro registro = new CursoRegistro();
        // Asume que tienes un setId en CursoRegistro
        // registro.setId(id);
        return registro;
    }

    // --- CursoController tests ---
    @Test
    @WithMockUser
    void testObtenerCurso_DevuelveOk_SiExisteCurso() throws Exception {
        Curso curso = crearCurso(1L);
        Mockito.when(cursoService.obtenerCursoPorId(1L)).thenReturn(Optional.of(curso));

        mockMvc.perform(get("/cursos/1"))
                .andExpect(status().isOk())
                .andExpect(org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath("$.id").value(1L));
    }

    @Test
    @WithMockUser
    void testObtenerCurso_Devuelve404_SiNoExisteCurso() throws Exception {
        Mockito.when(cursoService.obtenerCursoPorId(2L)).thenReturn(Optional.empty());
        mockMvc.perform(get("/cursos/2"))
                .andExpect(status().isNotFound());
    }

    @Test
    @WithMockUser
    void testListarCursos_DevuelveListaVacia() throws Exception {
        Mockito.when(cursoService.listarCursos()).thenReturn(Collections.emptyList());
        mockMvc.perform(get("/cursos"))
                .andExpect(status().isOk())
                .andExpect(org.springframework.test.web.servlet.result.MockMvcResultMatchers.content().json("[]"));
    }

    // --- UsuarioController tests ---
    @Test
    @WithMockUser
    void testObtenerUsuario_DevuelveOk_SiExisteUsuario() throws Exception {
        Usuario usuario = crearUsuario(1L, "test@example.com");
        Mockito.when(usuarioService.obtenerUsuarioPorId(1L)).thenReturn(Optional.of(usuario));
        mockMvc.perform(get("/usuarios/1"))
                .andExpect(status().isOk())
                .andExpect(org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath("$.id").value(1L));
    }

    @Test
    @WithMockUser
    void testObtenerUsuario_Devuelve404_SiNoExisteUsuario() throws Exception {
        Mockito.when(usuarioService.obtenerUsuarioPorId(2L)).thenReturn(Optional.empty());
        mockMvc.perform(get("/usuarios/2"))
                .andExpect(status().isNotFound());
    }

    @Test
    @WithMockUser
    void testListarUsuarios_DevuelveListaVacia() throws Exception {
        Mockito.when(usuarioService.listarUsuarios()).thenReturn(Collections.emptyList());
        mockMvc.perform(get("/usuarios"))
                .andExpect(status().isOk())
                .andExpect(org.springframework.test.web.servlet.result.MockMvcResultMatchers.content().json("[]"));
    }

    // --- RoleController tests ---
    @Test
    @WithMockUser
    void testObtenerRole_DevuelveOk_SiExisteRole() throws Exception {
        Role role = crearRole(1L, "ROLE_ADMIN");
        Mockito.when(roleService.obtenerRolePorId(1L)).thenReturn(Optional.of(role));
        mockMvc.perform(get("/roles/1"))
                .andExpect(status().isOk())
                .andExpect(org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath("$.id").value(1L));
    }

    @Test
    @WithMockUser
    void testObtenerRole_Devuelve404_SiNoExisteRole() throws Exception {
        Mockito.when(roleService.obtenerRolePorId(2L)).thenReturn(Optional.empty());
        mockMvc.perform(get("/roles/2"))
                .andExpect(status().isNotFound());
    }

    @Test
    @WithMockUser
    void testListarRoles_DevuelveListaVacia() throws Exception {
        Mockito.when(roleService.listarRoles()).thenReturn(Collections.emptyList());
        mockMvc.perform(get("/roles"))
                .andExpect(status().isOk())
                .andExpect(org.springframework.test.web.servlet.result.MockMvcResultMatchers.content().json("[]"));
    }

    // --- CursoRegistroController tests ---
    @Test
    @WithMockUser
    void testObtenerCursoRegistro_DevuelveOk_SiExisteRegistro() throws Exception {
        CursoRegistro registro = crearCursoRegistro(1L);
        Mockito.when(cursoRegistroService.obtenerCursoRegistroPorId(1L)).thenReturn(Optional.of(registro));
        mockMvc.perform(get("/cursoregistros/1"))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser
    void testObtenerCursoRegistro_Devuelve404_SiNoExisteRegistro() throws Exception {
        Mockito.when(cursoRegistroService.obtenerCursoRegistroPorId(2L)).thenReturn(Optional.empty());
        mockMvc.perform(get("/cursoregistros/2"))
                .andExpect(status().isNotFound());
    }

    @Test
    @WithMockUser
    void testListarCursoRegistros_DevuelveListaVacia() throws Exception {
        Mockito.when(cursoRegistroService.listarCursoRegistros()).thenReturn(Collections.emptyList());
        mockMvc.perform(get("/cursoregistros"))
                .andExpect(status().isOk())
                .andExpect(org.springframework.test.web.servlet.result.MockMvcResultMatchers.content().json("[]"));
    }

    // --- AuthController tests ---
    @Test
    void testLogin_DevuelveOk_SiCredencialesValidas() throws Exception {
        Usuario usuario = crearUsuario(1L, "test@example.com");
        usuario.setPassword("encodedpass");
        Mockito.when(usuarioService.obtenerPorEmail("test@example.com")).thenReturn(usuario);
        Mockito.when(passwordEncoder.matches("1234", "encodedpass")).thenReturn(true);
        Mockito.when(jwtUtil.generateToken("test@example.com")).thenReturn("token");
        mockMvc.perform(post("/auth/login")
                .with(csrf())
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"email\":\"test@example.com\",\"password\":\"1234\"}"))
                .andExpect(status().isOk())
                .andExpect(org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath("$.token").value("token"));
    }

    @Test
    void testLogin_Devuelve401_SiCredencialesInvalidas() throws Exception {
        Usuario usuario = crearUsuario(1L, "test@example.com");
        usuario.setPassword("encodedpass");
        Mockito.when(usuarioService.obtenerPorEmail("test@example.com")).thenReturn(usuario);
        Mockito.when(passwordEncoder.matches("wrongpass", "encodedpass")).thenReturn(false);
        mockMvc.perform(post("/auth/login")
                .with(csrf())
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"email\":\"test@example.com\",\"password\":\"wrongpass\"}"))
                .andExpect(status().isUnauthorized());
    }

    @Test
    void testRegisterWithInvalidPassword_Devuelve400() throws Exception {
        mockMvc.perform(post("/auth/register")
                .with(csrf())
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"email\":\"test@example.com\",\"password\":\"12\",\"nombre\":\"Test\",\"role\":\"ROLE_ADMIN\"}"))
                .andExpect(status().isBadRequest());
    }
}