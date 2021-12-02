package com.idsmanager.main.web.validation;

import com.idsmanager.commons.utils.MatchUtils;
import com.idsmanager.main.service.UserService;
import com.idsmanager.main.service.dto.user.UserFormDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 * Created by IDsManager.Feng on 2016/2/23.
 */
@Component
public class UserFormDtoValidator implements Validator {

    @Autowired
    private UserService userService;

    @Override
    public boolean supports(Class<?> clazz) {
        return UserFormDto.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", null, "password is required");

        UserFormDto emmUserDto = (UserFormDto) target;
        validateUsername(emmUserDto.getUsername(), errors);
        validateDisplayName(emmUserDto.getDisplayName(), errors);

        validateEmail(emmUserDto, errors);
    }

    private void validateEmail(UserFormDto emmUserDto, Errors errors) {
        final String email = emmUserDto.getEmail();
        if (!MatchUtils.isEmail(email)) {
            errors.rejectValue("email", null, "邮箱格式错误");
        } else {
            boolean existed = userService.isExistedUserEmail(email);
            if (existed) {
                errors.rejectValue("email", null, "邮箱已存在");
            }
        }
    }

    private void validateDisplayName(String displayName, Errors errors) {
        if (StringUtils.isEmpty(displayName)) {
            errors.rejectValue("displayName", null, "displayName can not be null");
            return;
        }
        if (userService.isExistedDisplayName(displayName)) {
            errors.rejectValue("displayName", null, "displayName { " + displayName + " } is Exist");
        }
    }

    private void validateUsername(String username, Errors errors) {
        if (StringUtils.isEmpty(username)) {
            errors.rejectValue("username", null, "username can not be null");
            return;
        }
        if (userService.isExistedUsername(username)) {
            errors.rejectValue("username", null, "username { " + username + " } is Exist");
        }
    }

}
