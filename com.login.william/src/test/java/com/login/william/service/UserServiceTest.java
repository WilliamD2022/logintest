package com.login.william.service;

import com.login.william.dto.UserRequest;
import com.login.william.repository.UserRepository;
import com.login.william.model.UserAccount;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class UserServiceTest {
    @Tag("unit")
    @Tag("api")
    @Test
    void deveCriarUsuarioComSenhaCriptografada() {
        var repo = mock(UserRepository.class);
        var encoder = mock(PasswordEncoder.class);

        when(repo.existsByUsername("novo")).thenReturn(false);
        when(repo.existsByEmail("novo@example.com")).thenReturn(false);
        when(encoder.encode("123456")).thenReturn("$bcrypt$");
        when(repo.save(Mockito.any(UserAccount.class)))
                .thenAnswer(inv -> {
                    var u = inv.getArgument(0, UserAccount.class);
                    u.setId(1L);
                    return u;
                });

        var service = new UserService(repo, encoder);

        var req = new UserRequest();
        req.setUsername("novo");
        req.setPassword("123456");
        req.setEmail("novo@example.com");

        var resp = service.create(req);

        assertEquals(1L, resp.getId());
        assertEquals("novo", resp.getUsername());
        assertEquals("novo@example.com", resp.getEmail());

        var captor = ArgumentCaptor.forClass(UserAccount.class);
        verify(repo).save(captor.capture());
        assertEquals("$bcrypt$", captor.getValue().getPasswordHash());
    }

    @Test
    void naoDevePermitirUsernameDuplicado() {
        var repo = mock(UserRepository.class);
        var encoder = mock(PasswordEncoder.class);
        when(repo.existsByUsername("will")).thenReturn(true);

        var service = new UserService(repo, encoder);

        var req = new UserRequest();
        req.setUsername("will");
        req.setPassword("abc12345");
        req.setEmail("w@example.com");

        assertThrows(IllegalArgumentException.class, () -> service.create(req));
    }
}
