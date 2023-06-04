package com.ihsan.itgecommerce.repository;

import com.ihsan.itgecommerce.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IRoleRepository  extends JpaRepository<Role, Long> {
}
