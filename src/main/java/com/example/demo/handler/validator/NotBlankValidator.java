package com.example.demo.handler.validator;

import com.example.demo.annotations.NotBlank;
import com.example.demo.utils.StringUtil;
import org.springframework.util.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class NotBlankValidator implements ConstraintValidator<NotBlank, Object> {

    @Override
    public void initialize(NotBlank annotation) {
    }

    @Override
    public boolean isValid(Object str, ConstraintValidatorContext constraintValidatorContext) {
        return !StringUtils.isEmpty(str);
    }

}
