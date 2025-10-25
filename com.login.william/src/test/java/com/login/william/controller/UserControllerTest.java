package com.login.william.controller;

import com.login.william.config.SecurityConfig;
import com.login.william.dto.UserRequest;
import com.login.william.dto.UserResponse;
import com.login.william.service.UserService;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@Tag("api")
@WebMvcTest(UserController.class)
@Import(SecurityConfig.class) // garante que sua SecurityConfig (com CSRF desabilitado) seja aplicada
class UserControllerTest {

    @Autowired
    MockMvc mvc;

    @MockBean
    UserService service;

    @Tag("api")
    @Test
    @WithMockUser(username = "cliente", roles = {"CLIENTE"})
    void deveCriarUsuario() throws Exception {
        when(service.create(any(UserRequest.class)))
                .thenReturn(new UserResponse(10L, "novo", "novo@example.com"));

        String body = """
      {"username":"novo","password":"abc12345","email":"novo@example.com"}
      """;

        mvc.perform(post("/users")
                        .with(csrf()) // mesmo se alguém reativar CSRF no futuro, o teste segue ok
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(10))
                .andExpect(jsonPath("$.username").value("novo"))
                .andExpect(jsonPath("$.email").value("novo@example.com"));
    }

    @Test
    void deveExigirAutenticacao() throws Exception {
        String body = """
      {"username":"x","password":"abc12345","email":"x@example.com"}
      """;

        mvc.perform(post("/users")
                        .with(csrf()) // evita 403 por CSRF; aqui queremos 401 por falta de auth
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body))
                .andExpect(status().isUnauthorized());
    }
}
