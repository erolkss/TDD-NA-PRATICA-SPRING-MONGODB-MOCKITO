package br.com.ero.register.service;

import br.com.ero.register.entity.User;
import br.com.ero.register.repositories.UserRepository;
import br.com.ero.register.services.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

@SpringBootTest
class UserServiceTest {

    @Mock
    private UserRepository userRespository;

    @InjectMocks
    private UserService userService;

    @Test
    void should_register_user_successfully() {
        User userBeforeSave = new User(null, "Lucas", "1234", LocalDate.of(2001, 4, 30));
        User userAfterSave = userBeforeSave;
        userAfterSave.setId("1");
        Mockito.when(userRespository.save(userBeforeSave)).thenReturn(userAfterSave);

        var user = userService.register(userBeforeSave);

        Mockito.verify(userRespository).save(userBeforeSave);
        Assertions.assertEquals(userAfterSave, user);
    }

    
    @ParameterizedTest
    @ValueSource(ints = {2010, 1950})
    void should_return_error_when_age_is_invalid(Integer year) {
        User user = new User(null, "Lucas", "1234", LocalDate.of(year, 4, 30));

        var exception = Assertions.assertThrows(RuntimeException.class, () -> userService.register(user));

        Mockito.verifyNoInteractions(userRespository);

        Assertions.assertEquals("Idade não permitida.", exception.getMessage());
    }

}
