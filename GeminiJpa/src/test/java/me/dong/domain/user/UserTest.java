package me.dong.domain.user;

import me.dong.builder.CartBuilder;
import me.dong.builder.UserBuilder;
import me.dong.domain.cart.Cart;
import org.hamcrest.Matchers;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.time.LocalDateTime;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

//@RunWith(SpringRunner.class)
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@ActiveProfiles(profiles = "test")
public class UserTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void testUpdateUserInfo() {
        User user = new UserBuilder().build();

        user.updateInfo("dong", "0100000111");

        assertThat(user.getName(), is("dong"));
        assertThat(user.getPhone(), is("0100000111"));
    }

    @Test
    public void testCreated() {
        User user = new UserBuilder()
                .withName("dong")
                .withPhone("0100000111")
                .build();

        LocalDateTime date = user.getCreatedAt();

        // when
        User afterUser = userRepository.save(user);

        // then
        org.assertj.core.api.Assertions.assertThat(date).isEqualTo(afterUser.getCreatedAt());
    }
}
