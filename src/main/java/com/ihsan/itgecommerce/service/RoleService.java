package com.ihsan.itgecommerce.service;


import com.ihsan.itgecommerce.entity.Role;
import com.ihsan.itgecommerce.repository.IRoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RoleService {

    private final IRoleRepository roleRepository;

    public boolean saveRole(Role role){
        roleRepository.save(role);
        return true;
    }

    public Role findByid(Long id){
        Optional<Role> role =  roleRepository.findById(id);

        if (role.isEmpty()){
            throw new RuntimeException("Role not found exception");
        }
        return role.get();
    }
}
