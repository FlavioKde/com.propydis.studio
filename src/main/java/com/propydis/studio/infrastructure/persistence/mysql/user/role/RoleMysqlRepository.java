package com.propydis.studio.infrastructure.persistence.mysql.user.role;

import com.propydis.studio.domain.user.role.Role;
import com.propydis.studio.domain.user.role.repository.RoleRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class RoleMysqlRepository implements RoleRepository {

        private final RoleEntityRepository roleEntityRepository;

        public RoleMysqlRepository(RoleEntityRepository roleEntityRepository) {
            this.roleEntityRepository = roleEntityRepository;
        }

        @Override
        public Role save(Role role) {
            RoleEntity roleEntity = RoleEntityMapper.toEntity(role);

            return  RoleEntityMapper.toDomain(roleEntityRepository.save(roleEntity));
        }

        @Override
        public Optional<Role>findById(Long id) {

            return roleEntityRepository.findById(id).map(RoleEntityMapper::toDomain);
        }

        @Override
        public List<Role> findAll(){

            return roleEntityRepository.findAll().stream().map(RoleEntityMapper::toDomain).collect(Collectors.toList());
        }

        @Override
        public void deleteById(Long id) {

            RoleEntity existing = roleEntityRepository.findById(id).orElse(null);

            roleEntityRepository.deleteById(existing.getId());

        }

}
