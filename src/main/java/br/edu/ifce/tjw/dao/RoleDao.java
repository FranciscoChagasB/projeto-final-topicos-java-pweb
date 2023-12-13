package br.edu.ifce.tjw.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.ifce.tjw.model.Role;

public interface RoleDao extends JpaRepository<Role, Long> {

    Role findByName(String name);
}
