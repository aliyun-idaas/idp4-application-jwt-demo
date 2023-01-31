
package com.idsmanager.demo.jwt.service.business;


import com.idsmanager.demo.jwt.commons.utils.PasswordHandler;
import com.idsmanager.demo.jwt.commons.web.context.BeanProvider;
import com.idsmanager.demo.jwt.domain.security.Privilege;
import com.idsmanager.demo.jwt.domain.security.User;
import com.idsmanager.demo.jwt.domain.security.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * When the system firstly startup, checking and initialed default-user.
 *
 * @author Shengzhao Li
 */
public class DefaultUserInitializer {


    private static final Logger LOG = LoggerFactory.getLogger(DefaultUserInitializer.class);

    private final transient UserRepository userRepository = BeanProvider.getBean(UserRepository.class);

    public DefaultUserInitializer() {
    }

    public String initial() {

        long count = userRepository.countUsers();
        if (count > 0) {
            return "Already initialed, Ignore";
        }

        String username = "admin";
        String password = PasswordHandler.encryptPassword("admin@jwtdemo");
        User user = new User(username, password);
        user.getPrivileges().addAll(Privilege.availablePrivileges());

        userRepository.saveUser(user);
        LOG.info("jwt-demo initialed default-user: {}", user);

        return user.username();
    }


}
