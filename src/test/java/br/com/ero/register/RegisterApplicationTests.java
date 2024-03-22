package br.com.ero.register;
import br.com.ero.register.entity.User;
import br.com.ero.register.respositories.UserRepository;
import br.com.ero.register.services.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

@SpringBootTest
class RegisterApplicationTests {

	@Mock
	private UserRepository	 userRespository;

	@InjectMocks
	private UserService userService;

	@Test
	void should_register_user_successfully() {
		User userBeforeSave = new User("Lucas", "1234", LocalDate.of(2001, 4, 30));
		User userAfterSave = userBeforeSave;
		userAfterSave.setId("1");
		Mockito.when(userRespository.save(userBeforeSave)).thenReturn(userAfterSave);

		var user = userService.register(userBeforeSave);

		Mockito.verify(userRespository).save(userBeforeSave);
		Assertions.assertEquals(userAfterSave, user);
	}

}
