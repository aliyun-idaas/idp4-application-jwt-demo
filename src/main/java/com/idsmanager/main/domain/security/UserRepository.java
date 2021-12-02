package com.idsmanager.main.domain.security;


import java.util.List;
import java.util.Map;

public interface UserRepository {
    void saveUser(User user);

    User findByUsername(String username);

    void removeUser(User user);

    User findByGuid(String guid);

    User currentUser();

    List<User> findUserPaginated(Map<String, Object> map);

    long totalUserPaginated(Map<String, Object> map);

    void updateUserPassword(User user);

    long countUsers();

    User findUserByTenantId(String tenantId);

    User findUserByDisplayName(String displayName);

    User findByEmail(String email);
}
