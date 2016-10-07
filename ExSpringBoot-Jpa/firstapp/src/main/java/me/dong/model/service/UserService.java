package me.dong.model.service;


import me.dong.model.domain.User;

public interface UserService {

    User get(Long id);

    User save(User user);

    void delete(Long id);
}
