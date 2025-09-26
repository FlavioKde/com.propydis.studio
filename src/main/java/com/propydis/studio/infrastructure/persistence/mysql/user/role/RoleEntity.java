package com.propydis.studio.infrastructure.persistence.mysql.user.role;

import com.propydis.studio.domain.user.role.RoleType;
import jakarta.persistence.*;


@Entity
@Table(name = "role")
public class RoleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name="role_type", unique=true, nullable=false)
    private RoleType roleType;

    public RoleEntity() {}

    public RoleEntity(RoleType roleType) {
        this.roleType = roleType;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public RoleType getRoleType() {
        return roleType;
    }

    public void setRoleType(RoleType roleType) {
        this.roleType = roleType;
    }
}
