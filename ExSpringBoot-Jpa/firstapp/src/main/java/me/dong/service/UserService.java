package me.dong.service;


import me.dong.domain.User;

public interface UserService {

    User get(Long id);

    User save(User user);

    void delete(Long id);
}
