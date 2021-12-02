package com.idsmanager.main.infrastructure.hibernate;


import com.google.common.collect.ImmutableMap;
import com.idsmanager.main.domain.security.SecurityUtils;
import com.idsmanager.main.domain.security.User;
import com.idsmanager.main.domain.security.UserRepository;
import com.idsmanager.main.infrastructure.hibernate.queryhelper.UserQueryHelper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author Guilty_Crown
 */
@Repository("userRepositoryHibernate")
public class UserRepositoryHibernate extends AbstractRepositoryHibernate<User> implements UserRepository {


    @Override
    public void saveUser(User user) {
        saveOrUpdate(user);
    }

    @Override
    public User findByUsername(String username) {
        return findOne("from User where username = :username", ImmutableMap.of("username", username));
    }

    @Override
    public User findUserByTenantId(String tenantId) {
        return findOne("from User where tenantId = :tenantId", ImmutableMap.of("tenantId", tenantId));
    }

    @Override
    public User findUserByDisplayName(String displayName) {
        return findOne("from User where displayName = :displayName", ImmutableMap.of("displayName", displayName));
    }

    @Override
    public void removeUser(User user) {
        delete(user);
    }

    @Override
    public User findByGuid(String guid) {
        return findByUuid(User.class, guid);
    }

    @Override
    public User currentUser() {
        return findByGuid(SecurityUtils.currentUserGuid());
    }


    @Override
    public List<User> findUserPaginated(Map<String, Object> map) {
        UserQueryHelper queryHelper = new UserQueryHelper(session(), map);
        return queryHelper.getResults();
    }

    @Override
    public long totalUserPaginated(Map<String, Object> map) {
        UserQueryHelper queryHelper = new UserQueryHelper(session(), map);
        return queryHelper.getAmount();
    }

    @Override
    public void updateUserPassword(User user) {

    }

    @Override
    public long countUsers() {
        return count("select count(id) from User ", null);
    }


    @Override
    public User findByEmail(String email) {
        return findOne("from User where email = :email", ImmutableMap.of("email", email));
    }
}
