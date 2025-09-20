package com.gmail.merikbest2015.ecommerce.service;

import com.gmail.merikbest2015.ecommerce.domain.User;
import java.util.Map;

public interface AuthenticationService {

    Map<String, Object> login(String email, String password);

    String getEmailByPasswordResetCode(String code);

    String registerUser(User user, String password, String confirmPassword);

    String activateUser(String activationCode);

    String sendPasswordResetCode(String email);

    String passwordReset(String email, String code, String newPassword);
}
