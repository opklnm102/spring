package me.dong.domain.user;

import me.dong.TestWithContext;
import me.dong.builder.CartBuilder;
import me.dong.builder.UserBuilder;
import me.dong.domain.cart.Cart;
import me.dong.domain.cart.CartRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.is;

public class UserServiceIntegrationTest extends TestWithContext {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private UserService userService;

    private User testUser;

    @Before
    public void setup() {
        testUser = new UserBuilder()
                .withName("dong")
                .withPhone("01099178835")
                .build();
        userRepository.save(testUser);
    }

    @After
    public void tearDown() {
        userRepository.delete(testUser.getId());
    }

    @Test
    public void testAddCart() {
        Cart cart = new CartBuilder()
                .withTitle("특가 상품")
                .withQuantity(1)
                .withPrice(1000000)
                .build();

        userService.addCart(testUser.getId(), cart);

        User user = userRepository.findOne(testUser.getId());
        assertThat(user.getTotalCartQuantity(), is(1));
        assertThat(user.getTotalCartPrice(), is(1000000));

        List<Cart> userCarts = cartRepository.findByUserId(testUser.getId());
        assertThat(userCarts.size(), is(1));
    }
}
