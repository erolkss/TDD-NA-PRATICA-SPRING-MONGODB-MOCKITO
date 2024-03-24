package br.com.ero.register.controller.mapper;

import br.com.ero.register.controller.request.UserRequest;
import br.com.ero.register.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "id", ignore = true)
    User toUser(UserRequest userRequest);
}
