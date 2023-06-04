package com.ihsan.itgecommerce.service;

import com.ihsan.itgecommerce.entity.Basket;
import com.ihsan.itgecommerce.entity.User;
import com.ihsan.itgecommerce.repository.IBasketRepository;
import com.ihsan.itgecommerce.repository.IUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BasketService {

    private final IBasketRepository basketRepository;
    private final IUserRepository userRepository;

    public boolean createBasket(Long userid){
        Basket basket = new Basket();
        Optional<User> user = userRepository.findById(userid);
        basket.setUser(user.get());

        basketRepository.save(basket);
        return true;
    }
}
