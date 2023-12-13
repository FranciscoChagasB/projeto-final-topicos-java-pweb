package com.javacorner.admin.service.impl;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import br.edu.ifce.tjw.dao.RoleDao;
import br.edu.ifce.tjw.serviceImpl.RoleServiceImpl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class RoleServiceImplTest {

    @Mock
    private RoleDao roleDao;

    @InjectMocks
    private RoleServiceImpl roleService;

    @Test
    void testLoadRoleByName() {
        roleService.loadRoleByName("Admin");
        verify(roleDao).findByName(any());
    }

    @Test
    void testCreateRole() {
        roleService.createRole("Admin");
        verify(roleDao, times(1)).save(any());
    }
}