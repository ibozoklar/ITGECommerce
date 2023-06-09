package com.ihsan.itgecommerce.service;


import com.ihsan.itgecommerce.dto.request.LoginRequestDto;
import com.ihsan.itgecommerce.dto.request.RegisterRequestDto;
import com.ihsan.itgecommerce.dto.request.VerifyEmailRequestDto;
import com.ihsan.itgecommerce.entity.Basket;
import com.ihsan.itgecommerce.entity.Product;
import com.ihsan.itgecommerce.entity.Role;
import com.ihsan.itgecommerce.entity.UserEntity;
import com.ihsan.itgecommerce.entity.enums.State;
import com.ihsan.itgecommerce.mapper.IUserMapper;
import com.ihsan.itgecommerce.repository.IBasketRepository;
import com.ihsan.itgecommerce.repository.IRoleRepository;
import com.ihsan.itgecommerce.repository.IUserRepository;
import com.ihsan.itgecommerce.utils.CodeGenerator;
import com.ihsan.itgecommerce.utils.JwtTokenManager;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserService {

    private final IUserRepository userRepository;
    private final IRoleRepository roleRepository;
    private final JavaMailSender javaMailSender;

    private final JwtTokenManager jwtTokenManager;
    private final IBasketRepository basketRepository;



    public boolean save(UserEntity user){
        userRepository.save(user);
        return true;
    }

    public Boolean register(RegisterRequestDto dto) {

        UserEntity user = IUserMapper.INSTANCE.toUser(dto);

        user.setState(State.PENDING);

        Set<Role> roles = new HashSet<>();
        Role roleUser = roleRepository.findById(Long.valueOf(2)).get();

        roles.add(roleUser);

        //Email

        try{

            String code = CodeGenerator.generateCode();
            user.setActivationCode(code);

            SimpleMailMessage mailMessage = new SimpleMailMessage();

            mailMessage.setFrom("ihsancb99@gmail.com");
            mailMessage.setTo(user.getEmail());
            mailMessage.setSubject("Activation Code...:");
            mailMessage.setText(code);
            javaMailSender.send(mailMessage);


        }catch (Exception e){
            e.printStackTrace();

        }

        userRepository.save(user);
        return true;
    }


    public boolean verifyEmail(VerifyEmailRequestDto dto) {

        Optional<UserEntity> user = userRepository.findById(dto.getId());

        if (user.isEmpty()){
            throw new RuntimeException("UserEntity not found exception");
        }

        if (user.get().getState().equals(State.ACTIVE)){
            throw new RuntimeException("UserEntity already verified exception");
        }

        if (user.get().getActivationCode().equals(dto.getActivationCode())){
            user.get().setState(State.ACTIVE);
            userRepository.save(user.get());
            return true;
        }else{
            throw new RuntimeException("Invalid activation code");
        }

    }

    public Long login(LoginRequestDto dto) {

        Optional<UserEntity> user = userRepository.findByEmail(dto.getEmail());

        if (user.isEmpty()){
            throw new RuntimeException("UserEntity not found exception");
        }
        if (user.get().getState().equals(State.PENDING)){
            throw new RuntimeException("E-Mail not verified exception");
        }

        if (user.get().getPassword().equals(dto.getPassword()) && user.get().getEmail().equals(dto.getEmail())){
            Basket basket = new Basket();
            basket.setUser(user.get());
            basketRepository.save(basket);
            user.get().setBasket(basket);
            userRepository.save(user.get());
            return user.get().getId();
        }else {
            throw new RuntimeException("Invalid credentials exception");
        }
    }

    public List<Product> findAllPurchasedProducts(Long userid) {

        Optional<UserEntity> user = userRepository.findById(userid);

        if (user.isEmpty()){
            throw new RuntimeException("UserEntity not found exception");
        }

        return user.get().getProducts();
    }

    public Optional<UserEntity> findById(Long userid) {
        return userRepository.findById(userid);
    }

    public String loginWithSecurity(LoginRequestDto dto) {
        Optional<UserEntity> user = userRepository.findByEmail(dto.getEmail());

        if (user.isEmpty()){
            throw new RuntimeException("UserEntity not found exception");
        }
        if (user.get().getState().equals(State.PENDING)){
            throw new RuntimeException("E-Mail not verified exception");
        }

        if (user.get().getPassword().equals(dto.getPassword()) && user.get().getEmail().equals(dto.getEmail())){
            Basket basket = new Basket();
            basket.setUser(user.get());
            basketRepository.save(basket);
            user.get().setBasket(basket);
            userRepository.save(user.get());
            Optional<String> token = jwtTokenManager.createToken(user.get().getId());
            if (token.isEmpty()){
                throw new RuntimeException("JWT Token Create Exception");
            }
            return token.get();
        }else {
            throw new RuntimeException("Invalid credentials exception");
        }

    }
}
