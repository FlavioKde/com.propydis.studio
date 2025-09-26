package com.propydis.studio.infrastructure.persistence.mysql.user.user;

import com.propydis.studio.domain.user.user.User;
import com.propydis.studio.infrastructure.persistence.mysql.user.role.RoleEntityMapper;

import java.util.stream.Collectors;

public class UserEntityMapper {

            public static User toDomain(UserEntity userEntity) {
                if (userEntity == null) {
                    return null;
                }

                User user = new User();
                user.setId(userEntity.getId());
                user.setUsername(userEntity.getUsername());
                user.setPassword(userEntity.getPassword());
                user.setEmail(userEntity.getEmail());
                user.setRoles(
                        userEntity.getRoles()
                                .stream()
                                .map(RoleEntityMapper::toDomain)
                                .collect(Collectors.toList())
                );

                return user;

            }

            public static UserEntity toEntity(User user) {
                if (user == null) {
                    return null;
                }

                UserEntity userEntity = new UserEntity();
                userEntity.setId(user.getId());
                userEntity.setUsername(user.getUsername());
                userEntity.setPassword(user.getPassword());
                userEntity.setEmail(user.getEmail());
                userEntity.setRoles(
                                user.getRoles()
                                .stream()
                                .map(RoleEntityMapper::toEntity)
                                        .collect(Collectors.toList())
                );

                return userEntity;
            }

}
