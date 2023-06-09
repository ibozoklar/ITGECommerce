
import com.ihsan.itgecommerce.entity.Role;
import com.ihsan.itgecommerce.repository.IRoleRepository;
import com.ihsan.itgecommerce.service.RoleService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

public class RoleServiceTest {

    @Mock
    private IRoleRepository roleRepository;

    @InjectMocks
    private RoleService roleService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSaveRole() {
        Role role = new Role();
        role.setRole("ROLE_USER");

        when(roleRepository.save(any(Role.class))).thenReturn(null);

        assertTrue(roleService.saveRole(role));

        verify(roleRepository, times(1)).save(any(Role.class));
    }
}
