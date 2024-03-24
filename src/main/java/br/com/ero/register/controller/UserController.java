package br.com.ero.register.controller;

import br.com.ero.register.controller.mapper.UserMapper;
import br.com.ero.register.controller.request.UserRequest;
import br.com.ero.register.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserMapper userMapper;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void registerUser(@Valid @RequestBody UserRequest userRequest){
        userService.register(userMapper.toUser(userRequest));

    }
}
