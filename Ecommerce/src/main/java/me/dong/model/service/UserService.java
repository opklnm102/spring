package me.dong.model.service;

import me.dong.model.domain.Authority;
import me.dong.model.domain.User;
import me.dong.model.repository.AuthorityRepository;
import me.dong.model.repository.UserRepository;
import me.dong.security.Authorities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;

/**
 * User와 관련된 비즈니스 로직 처리
 */
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthorityRepository authorityRepository;

    public boolean existUserByUserName(String userName){
        return findByUserName(userName) != null;
    }

    @Transactional
    public User save(User user){
        Authority authority = authorityRepository.findByAuthority(Authorities.USER);

        userRepository.save(new HashSet<User>(){
            {
                add(new User(user.getName(),
                        user.getPassword(),
                        user.getUserName(),
                        new HashSet<Authority>(){{
                            add(authority);
                        }}
                ));
            }
        });
        return user;
    }

    public User findByUserName(String userName){
        User user = userRepository.findByUserName(userName);
        return user;
    }

    public Authority findByAuthority(String authority){
        return authorityRepository.findByAuthority(authority);
    }
}
