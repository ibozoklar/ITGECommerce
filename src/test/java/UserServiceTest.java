import com.ihsan.itgecommerce.entity.User;
import com.ihsan.itgecommerce.repository.IBasketRepository;
import com.ihsan.itgecommerce.repository.IRoleRepository;
import com.ihsan.itgecommerce.repository.IUserRepository;
import com.ihsan.itgecommerce.service.UserService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mail.javamail.JavaMailSender;

import static org.mockito.Mockito.when;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UserServiceTest {

    @Mock
    private IUserRepository userRepository;
    @Mock
    private IRoleRepository roleRepository;
    @Mock
    private JavaMailSender javaMailSender;
    @Mock
    private IBasketRepository basketRepository;
    private UserService userService;

    public UserServiceTest() {
    }

    @BeforeAll
    public void Init(){
        MockitoAnnotations.openMocks(this);
        userService= new UserService(userRepository, roleRepository, javaMailSender, basketRepository);
    }

    @Test
    public void testFindAllPurchasedProducts(){

        User user = new User();
        user.setId(3L);

        when(userRepository.findById(user.getId()).get().getProducts());



    }

}
