package org.example.Domain.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;
    public UserDomain createAccount(UserDomain userDomain) {
        userRepository.createAccount(userDomain);
        return userDomain;
    }
    public UserDomain getAccountById(String accountId) {
        UserDomain userDomain = userRepository.getAccountById(accountId);
        return userDomain;
    }
    public UserDomain getAccountByName(String accountName) {
        UserDomain userDomain = userRepository.getAccountByName(accountName);
        return userDomain;
    }
    public UserDomain updatePassword(UserDomain userDomain) {
        userRepository.updatePassword(userDomain);
        return userDomain;
    }
}
