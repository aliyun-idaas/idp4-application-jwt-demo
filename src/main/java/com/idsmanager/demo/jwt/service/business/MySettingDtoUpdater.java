package com.idsmanager.demo.jwt.service.business;


import com.idsmanager.demo.jwt.commons.web.context.BeanProvider;
import com.idsmanager.demo.jwt.domain.security.SecurityUtils;
import com.idsmanager.demo.jwt.domain.security.User;
import com.idsmanager.demo.jwt.domain.security.UserRepository;
import com.idsmanager.demo.jwt.service.dto.user.MySettingDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 2015/12/21
 *
 * @author Shengzhao Li
 */
public class MySettingDtoUpdater {


    private static final Logger LOG = LoggerFactory.getLogger(MySettingDtoUpdater.class);

    private final transient UserRepository userRepository = BeanProvider.getBean(UserRepository.class);

    private final MySettingDto formDto;

    public MySettingDtoUpdater(MySettingDto formDto) {
        this.formDto = formDto;
    }

    public void update() {

        final User user = userRepository.currentUser();
        user.setPassword(formDto.getNewPassword());

        userRepository.updateUserPassword(user);
        LOG.debug("{}|Update my-setting: change password", SecurityUtils.username());
    }
}
