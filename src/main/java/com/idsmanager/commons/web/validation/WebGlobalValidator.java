package com.idsmanager.commons.web.validation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import java.util.ArrayList;
import java.util.List;

/**
 * 15-1-3
 *
 * @author Shengzhao Li
 */
@Service("webGlobalValidator")
public class WebGlobalValidator extends LocalValidatorFactoryBean {

    private List<Validator> customValidators = new ArrayList<>();

    @Autowired
    private ApplicationContext applicationContext;

    @Override
    public void afterPropertiesSet() {
        super.afterPropertiesSet();
        String[] validatorBeanNames = applicationContext.getBeanNamesForType(Validator.class);
        if (validatorBeanNames != null && validatorBeanNames.length > 0) {
            for (String beanName : validatorBeanNames) {
                Validator validator = (Validator) applicationContext.getBean(beanName);
                if (!this.equals(validator)) {
                    customValidators.add(validator);
                }
            }
        }
    }

    @Override
    public void validate(Object target, Errors errors, Object... validationHints) {
        for (Validator validator : customValidators) {
            if (validator.supports(target.getClass())) {
                validator.validate(target, errors);
            }
        }
        super.validate(target, errors, validationHints);
    }
}
