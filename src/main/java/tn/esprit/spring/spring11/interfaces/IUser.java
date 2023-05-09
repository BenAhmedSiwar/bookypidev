package tn.esprit.spring.spring11.interfaces;

import tn.esprit.spring.spring11.entities.User;

import java.util.List;

public interface IUser {
    public User createUser(User user) ;
    public List<User> getAllUsers();
}
