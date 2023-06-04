package com.ihsan.itgecommerce.service;


import com.ihsan.itgecommerce.entity.Role;
import com.ihsan.itgecommerce.repository.IRoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoleService {

    private final IRoleRepository roleRepository;

    public boolean saveRole(Role role){
        roleRepository.save(role);
        return true;
    }
}
