package ru.itpark.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import ru.itpark.model.User;
import ru.itpark.service.UserService;


@Component
public class UserValidator implements Validator {

    @Autowired
    private UserService userService;

    @Override
    public boolean supports(Class<?> aClass) {
        return User.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        User user = (User) o;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "NotEmpty");
        if (user.getUsername().length() < 3 || user.getUsername().length() > 8) {
            errors.rejectValue("username", "Size.userForm.username");
        } else{
            String  expression="^[a-zA-Z0-9]*$";
            CharSequence inputStr = user.getUsername();
            Pattern pattern = Pattern.compile(expression,Pattern.CASE_INSENSITIVE);
            Matcher matcher = pattern.matcher(inputStr);
            if(!matcher.matches()){
                errors.rejectValue("username", "Invalid.userForm.username");
            }
        }
        if (userService.findByUsername(user.getUsername()) != null) {
            errors.rejectValue("username", "Duplicate.userForm.username");
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "NotEmpty");
        if (user.getPassword().length() < 3 || user.getPassword().length() > 8) {
            errors.rejectValue("password", "Size.userForm.password");
        } else{
            String  expression="^[a-zA-Z0-9]*$";
            CharSequence inputStr = user.getPassword();
            Pattern pattern = Pattern.compile(expression,Pattern.CASE_INSENSITIVE);
            Matcher matcher = pattern.matcher(inputStr);
            if(!matcher.matches()){
                errors.rejectValue("password", "Invalid.userForm.password");
            }
        }

        if (!user.getPasswordConfirm().equals(user.getPassword())) {
            errors.rejectValue("passwordConfirm", "Diff.userForm.passwordConfirm");
        }
    }
}
