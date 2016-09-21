package me.dong.domain.user;


import me.dong.domain.cart.Cart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {
    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public void addCart(final long userId, final Cart cart){
        User user = userRepository.findOne(userId);
        user.addCart(cart);
    }
}
