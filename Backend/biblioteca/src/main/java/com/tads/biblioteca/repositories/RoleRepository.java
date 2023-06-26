package com.tads.biblioteca.repositories;

import com.tads.biblioteca.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role,Long> {
   @Query("SELECT obj from Role obj where obj.authority = :name")
    Role findRoleByName(String name);
}
