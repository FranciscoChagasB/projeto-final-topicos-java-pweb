package br.edu.ifce.tjw.service;

import br.edu.ifce.tjw.model.User;

public interface UserService {

    User loadUserByEmail(String email);

    User createUser(String email, String password);

    void assignRoleToUser(String email, String roleName);

    boolean doesCurrentUserHasRole(String roleName);
}
