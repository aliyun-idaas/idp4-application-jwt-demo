
package com.idsmanager.main.service.business;


import com.idsmanager.commons.utils.PasswordHandler;
import com.idsmanager.commons.web.context.BeanProvider;
import com.idsmanager.main.domain.security.Privilege;
import com.idsmanager.main.domain.security.SecurityUtils;
import com.idsmanager.main.domain.security.User;
import com.idsmanager.main.domain.security.UserRepository;
import com.idsmanager.main.service.dto.user.UserFormDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * CreateUserHandler
 *
 * @author Guilty_Crown
 */
public class CreateUserHandler {


    private static final Logger LOG = LoggerFactory.getLogger(CreateUserHandler.class);

    private transient UserRepository userRepository = BeanProvider.getBean(UserRepository.class);

    private UserFormDto formDto;

    public CreateUserHandler(UserFormDto formDto) {
        this.formDto = formDto;
    }

    public String handle() {

        final String username = formDto.getUsername();
        final String pass = PasswordHandler.encryptPassword(formDto.getPassword(), username);
        User user = new User(username, pass).email(formDto.getEmail());
        user.getPrivileges().addAll(Privilege.tenantPrivileges());
        user.setDisplayName(formDto.getDisplayName());
        userRepository.saveUser(user);
        LOG.debug("{}|Create User: {}", SecurityUtils.username(), user);

        return user.getUuid();
    }
}
