package com.propydis.studio.dto.mysql.mapper;


import com.propydis.studio.dto.mysql.UserCreateDTO;
import com.propydis.studio.dto.mysql.UserDTO;
import com.propydis.studio.model.mysql.User;

import java.util.stream.Collectors;

public class UserMapper {

    public static User ToEntity(UserCreateDTO userCreateDTO) {
        User user = new User();

        user.setUsername(userCreateDTO.getUsername());
        user.setEmail(userCreateDTO.getEmail());
        user.setPassword(userCreateDTO.getPassword());


        return user;
    }

    public static UserDTO toDTO(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setUsername(user.getUsername());
        userDTO.setEmail(user.getEmail());
        userDTO.setRole(user.getRoles().stream()
                .map(RoleMapper::toDTO)
                .collect(Collectors.toList()));

        return userDTO;
    }

}
