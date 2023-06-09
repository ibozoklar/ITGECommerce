
import com.ihsan.itgecommerce.dto.request.LoginRequestDto;
import com.ihsan.itgecommerce.dto.request.RegisterRequestDto;
import com.ihsan.itgecommerce.dto.request.VerifyEmailRequestDto;
import com.ihsan.itgecommerce.entity.UserEntity;
import com.ihsan.itgecommerce.entity.enums.State;
import com.ihsan.itgecommerce.repository.IBasketRepository;
import com.ihsan.itgecommerce.repository.IRoleRepository;
import com.ihsan.itgecommerce.repository.IUserRepository;
import com.ihsan.itgecommerce.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mail.javamail.JavaMailSender;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

public class UserServiceTest {

    @Mock
    private IUserRepository userRepository;

    @Mock
    private IRoleRepository roleRepository;

    @Mock
    private JavaMailSender javaMailSender;

    @Mock
    private IBasketRepository basketRepository;

    @InjectMocks
    private UserService userService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testRegister() {
        // Mocking the userRepository and roleRepository
        when(roleRepository.findById(anyLong())).thenReturn(Optional.empty());
        when(userRepository.save(any(UserEntity.class))).thenReturn(null);

        RegisterRequestDto dto = new RegisterRequestDto();
        // Set the necessary properties on dto

        assertTrue(userService.register(dto));

        // Verify that the save method was called on userRepository
        verify(userRepository, times(1)).save(any(UserEntity.class));
    }

    @Test
    public void testVerifyEmail() {
        UserEntity user = new UserEntity();
        user.setId(1L);
        user.setState(State.PENDING);
        user.setActivationCode("12345");

        // Mocking the userRepository
        when(userRepository.findById(anyLong())).thenReturn(Optional.of(user));
        when(userRepository.save(any(UserEntity.class))).thenReturn(null);

        VerifyEmailRequestDto dto = new VerifyEmailRequestDto();
        dto.setId(1L);
        dto.setActivationCode("12345");

        assertTrue(userService.verifyEmail(dto));

        // Verify that the save method was called on userRepository
        verify(userRepository, times(1)).save(any(UserEntity.class));
    }

    @Test
    public void testLogin() {
        UserEntity user = new UserEntity();
        user.setId(1L);
        user.setEmail("test@example.com");
        user.setPassword("password");
        user.setState(State.ACTIVE);

        // Mocking the userRepository
        when(userRepository.findByEmail(anyString())).thenReturn(Optional.of(user));
        when(userRepository.save(any(UserEntity.class))).thenReturn(null);

        LoginRequestDto dto = new LoginRequestDto();
        dto.setEmail("test@example.com");
        dto.setPassword("password");

        assertNotNull(userService.login(dto));

        // Verify that the save method was called on userRepository
        verify(userRepository, times(1)).save(any(UserEntity.class));
    }

    @Test
    public void testFindAllPurchasedProducts() {
        UserEntity user = new UserEntity();
        user.setId(1L);

        // Mocking the userRepository
        when(userRepository.findById(anyLong())).thenReturn(Optional.of(user));

        assertNotNull(userService.findAllPurchasedProducts(1L));

        // Verify that the findById method was called on userRepository
        verify(userRepository, times(1)).findById(anyLong());
    }
}
