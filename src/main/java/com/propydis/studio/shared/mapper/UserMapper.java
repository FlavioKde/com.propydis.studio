package com.propydis.studio.shared.mapper;


import com.propydis.studio.dto.user.role.UserCreateDTO;
import com.propydis.studio.dto.user.user.UserDTO;
import com.propydis.studio.domain.user.user.User;

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
