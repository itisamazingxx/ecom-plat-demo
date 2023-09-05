package org.example.infrastructure.repoImpl;

import org.example.Domain.user.UserDomain;
import org.example.Domain.user.UserRepository;
import org.example.infrastructure.jooq.tables.User;
import org.example.infrastructure.jooq.tables.records.UserRecord;
import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class JooqUserRepo implements UserRepository {
    @Autowired
    DSLContext dslContext;
    public static final User USER_T = new User();
    @Override
    public void createAccount(UserDomain userDomain) {
        dslContext.executeInsert(toRecord(userDomain));
    }
    @Override
    public UserDomain getAccountById(String accountId) {
        Optional<UserDomain> userDomainOptional = dslContext.selectFrom(USER_T).where(USER_T.USER_ID.eq(accountId)).fetchOptional(this::toDomain);
        return userDomainOptional.orElse(null);
    }
    @Override
    public UserDomain getAccountByName(String accountName) {
        Optional<UserDomain> userDomainOptional =dslContext.selectFrom(USER_T).where(USER_T.USER_NAME.eq(accountName)).fetchOptional(this::toDomain);
        return userDomainOptional.orElse(null);
    }
    @Override
    public void updatePassword(UserDomain userDomain) {
        dslContext.executeUpdate(toRecord(userDomain));

    }

    private UserDomain toDomain(UserRecord userRecord) {
        UserDomain userDomain = new UserDomain();
        userDomain.setPassword(userRecord.getPassword());
        userDomain.setUserName(userRecord.getUserName());
        userDomain.setUserId(userRecord.getUserId());
        return userDomain;
    }
    private UserRecord toRecord(UserDomain userDomain) {
        UserRecord userRecord = new UserRecord();
        userRecord.setPassword(userDomain.getPassword());
        userRecord.setUserName(userDomain.getUserName());
        userRecord.setUserId(userDomain.getUserId());
        return userRecord;
    }
}

