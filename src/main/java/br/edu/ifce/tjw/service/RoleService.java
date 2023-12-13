package br.edu.ifce.tjw.service;

import br.edu.ifce.tjw.model.Role;

public interface RoleService {

    Role loadRoleByName(String roleName);

    Role createRole(String roleName);
}
