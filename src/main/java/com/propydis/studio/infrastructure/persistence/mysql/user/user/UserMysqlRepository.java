package com.propydis.studio.infrastructure.persistence.mysql.user.user;


import com.propydis.studio.domain.user.user.User;
import com.propydis.studio.domain.user.user.repository.UserRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class UserMysqlRepository implements UserRepository {

        private final UserEntityRepository userEntityRepository;

        public UserMysqlRepository(UserEntityRepository userEntityRepository) {
            this.userEntityRepository = userEntityRepository;
        }

        @Override
        public User save(User user) {
            UserEntity userEntity = UserEntityMapper.toEntity(user);

            return UserEntityMapper.toDomain(userEntityRepository.save(userEntity));
        }

        @Override
        public Optional<User> findById(Long id){
            UserEntity userEntity = userEntityRepository.findById(id).orElse(null);

            return Optional.ofNullable(UserEntityMapper.toDomain(userEntity));
        }

        @Override
        public List<User> findAll(){
            return userEntityRepository.findAll().stream().map(UserEntityMapper::toDomain).collect(Collectors.toList());
        }

        @Override
        public void deleteById(Long id){
            UserEntity existing = userEntityRepository.findById(id).orElse(null);

            userEntityRepository.deleteById(existing.getId());
        }

        @Override
        public Optional<User> findByUsername(String username) {
            return userEntityRepository.findByUsername(username)
                    .map(UserEntityMapper::toDomain);
        }


}
