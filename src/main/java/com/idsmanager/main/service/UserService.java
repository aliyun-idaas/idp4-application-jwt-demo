package com.idsmanager.main.service;


import com.idsmanager.main.domain.security.User;
import com.idsmanager.main.service.dto.user.UserFormDto;
import com.idsmanager.main.service.dto.user.UserPaginated;

public interface UserService {
    boolean isExistedUsername(String username);

    User getUserByUsername(String username);

    UserPaginated loadUserPaginated(UserPaginated paginated);

    String createUser(UserFormDto formDto);

    String initialDefaultUser();

    User updateLoginTimes(String username);

    UserFormDto generateUserFormDto();

    boolean isExistedDisplayName(String displayName);

    boolean isExistedUserEmail(String email);
}
