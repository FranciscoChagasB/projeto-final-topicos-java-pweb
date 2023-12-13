package br.edu.ifce.tjw.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.ifce.tjw.model.User;

public interface UserDao extends JpaRepository<User, Long> {

    User findByEmail(String email);
}
