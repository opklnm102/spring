package me.dong.builder;


import me.dong.domain.user.User;

public class UserBuilder {

    private Long id;
    private String name;
    private String phone;


    public UserBuilder withId(Long id){
        this.id = id;
        return this;
    }
    public UserBuilder withName(String name){
        this.name = name;
        return this;
    }

    public UserBuilder withPhone(String phone){
        this.phone = phone;
        return this;
    }

    public User build(){
        User user = new User();
        user.setId(id);
        user.updateInfo(name, phone);
        return user;
    }
}
