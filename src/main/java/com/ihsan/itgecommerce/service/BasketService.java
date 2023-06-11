package com.ihsan.itgecommerce.service;

import com.ihsan.itgecommerce.entity.Basket;
import com.ihsan.itgecommerce.entity.Product;
import com.ihsan.itgecommerce.entity.UserEntity;
import com.ihsan.itgecommerce.entity.enums.ProductState;
import com.ihsan.itgecommerce.repository.IBasketRepository;
import com.ihsan.itgecommerce.repository.IProductRepository;
import com.ihsan.itgecommerce.repository.IUserRepository;
import com.ihsan.itgecommerce.utils.CodeGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BasketService {

    private final IBasketRepository basketRepository;
    private final IUserRepository userRepository;
    private final IProductRepository productRepository;
    private final JavaMailSender javaMailSender;

    public boolean createBasket(Long userid){
        Basket basket = new Basket();
        Optional<UserEntity> user = userRepository.findById(userid);
        basket.setUser(user.get());

        basketRepository.save(basket);
        return true;
    }

    public boolean addProductToBasket(Long userid, Long productid){
        Optional<UserEntity> user = userRepository.findById(userid);
        if (user.isEmpty()){
            throw new RuntimeException("UserEntity not found exception");
        }

        Optional<Product> product = productRepository.findById(productid);
        if (product.isEmpty()){
            throw new RuntimeException("Product not found exception");
        }

        user.get().getBasket().getProducts().add(product.get());
        userRepository.save(user.get());

        return true;
    }

    public List<Product> listProducts(Long basketid) {

        Optional<Basket> basket = basketRepository.findById(basketid);
        if (basket.isEmpty()){
            throw new RuntimeException("Basket not found exception");
        }

        return basket.get().getProducts();
    }

    public Boolean removeProductFromBasket(Long userid, Long productid) {
        Optional<UserEntity> user = userRepository.findById(userid);
        if (user.isEmpty()){
            throw new RuntimeException("UserEntity not found exception");
        }

        Optional<Product> product = productRepository.findById(productid);
        if (product.isEmpty()){
            throw new RuntimeException("Product not found exception");
        }
        user.get().getBasket().getProducts().remove(product.get());
        userRepository.save(user.get());
        return true;
    }

    public Boolean purchaseProducts(Long basketid) {

        Optional<Basket> basket = basketRepository.findById(basketid);

        String text = "";
        for (int i=0; i<basket.get().getProducts().size(); i++){

            basket.get().getProducts().get(i).setUser(basket.get().getUser());
            basket.get().getProducts().get(i).setProductState(ProductState.SOLD);
            basket.get().getUser().getProducts().add(basket.get().getProducts().get(i));
            text += basket.get().getProducts().get(i).getTitle()+" --- ";

        }

        UserEntity user = basket.get().getUser();



            try{

                SimpleMailMessage mailMessage = new SimpleMailMessage();

                mailMessage.setFrom("ihsancb99@gmail.com");
                mailMessage.setTo(user.getEmail());
                mailMessage.setSubject("Sipariş ettiğiniz ürünler...:");
                mailMessage.setText(text);
                javaMailSender.send(mailMessage);


            }catch (Exception e){
                e.printStackTrace();

            }


        basket.get().setProducts(new ArrayList<>());
        basketRepository.save(basket.get());

        return true;

    }

    public List<Product> listProductsFromUserBasket(Long userid) {
        Optional<UserEntity> user = userRepository.findById(userid);
        if (user.isEmpty()){
            throw new RuntimeException("UserEntity not found exception");
        }

        return user.get().getBasket().getProducts();

    }

    public Boolean purchaseProductsInUserBasket(Long userid) {
        Optional<UserEntity> user = userRepository.findById(userid);
        if (user.isEmpty()){
            throw new RuntimeException("UserEntity not found exception");
        }

        return purchaseProducts(user.get().getBasket().getId());

    }
}
