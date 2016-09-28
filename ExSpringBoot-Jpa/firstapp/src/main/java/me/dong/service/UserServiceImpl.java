package me.dong.service;


import me.dong.domain.User;
import me.dong.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Controller가 요청한 비즈니스 로직을 처리하는 서비스 클래스
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User get(Long id) {
        return userRepository.findOne(id);
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public void delete(Long id) {
        userRepository.delete(id);
    }
}
