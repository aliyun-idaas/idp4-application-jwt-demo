package com.idsmanager.demo.jwt.service.impl;


import com.idsmanager.demo.jwt.commons.utils.paginated.PaginatedLoader;
import com.idsmanager.demo.jwt.domain.security.IdsUserDetails;
import com.idsmanager.demo.jwt.domain.security.User;
import com.idsmanager.demo.jwt.domain.security.UserRepository;
import com.idsmanager.demo.jwt.service.UserService;
import com.idsmanager.demo.jwt.service.business.CreateUserHandler;
import com.idsmanager.demo.jwt.service.business.DefaultUserInitializer;
import com.idsmanager.demo.jwt.service.dto.user.UserFormDto;
import com.idsmanager.demo.jwt.service.dto.user.UserPaginated;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service("userService")
public class UserServiceImpl implements UserService, UserDetailsService {


    private static final Logger LOG = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserRepository userRepository;

    @Override
    @Transactional(readOnly = true)
    public boolean isExistedUsername(String username) {
        final User user = userRepository.findByUsername(username);
        return user != null;
    }


    @Override
    @Transactional()
    public User updateLoginTimes(String username) {
        User user = getUserByUsername(username);
        user.setLoginTimes(user.getLoginTimes() + 1);
        userRepository.saveUser(user);
        return user;
    }

    @Override
    public UserFormDto generateUserFormDto() {
        UserFormDto formDto = new UserFormDto();
        return formDto;
    }

    @Override
    @Transactional(readOnly = true)
    public boolean isExistedDisplayName(String displayName) {
        return null != userRepository.findUserByDisplayName(displayName);
    }

    @Override
    @Transactional(readOnly = true)
    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }


    @Override
    @Transactional(readOnly = true)
    public UserPaginated loadUserPaginated(UserPaginated paginated) {
        final Map<String, Object> map = paginated.queryMap();
        return paginated.load(new PaginatedLoader<User>() {
            @Override
            public List<User> loadList() {
                return userRepository.findUserPaginated(map);
            }

            @Override
            public long loadTotalSize() {
                return userRepository.totalUserPaginated(map);
            }
        });
    }

    @Override
    @Transactional()
    public String createUser(UserFormDto formDto) {
        CreateUserHandler handler = new CreateUserHandler(formDto);
        return handler.handle();
    }


    @Override
    @Transactional()
    public String initialDefaultUser() {
        DefaultUserInitializer initializer = new DefaultUserInitializer();
        return initializer.initial();
    }

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("Not found by username: " + username);
        }
        return new IdsUserDetails(user);
    }


    @Override
    @Transactional(readOnly = true)
    public boolean isExistedUserEmail(String email) {
        User user = userRepository.findByEmail(email);
        return user != null;
    }
}
