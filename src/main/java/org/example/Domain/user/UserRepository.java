package org.example.Domain.user;

public interface UserRepository {
    void createAccount(UserDomain userDomain);
    UserDomain getAccountById(String accountId);
    UserDomain getAccountByName(String accountName);
    void updatePassword(UserDomain userDomain);
}
