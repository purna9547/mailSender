package com.purna.mailsender.Services;

import com.purna.mailsender.Model.User;
import com.purna.mailsender.Reposities.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class UserService {
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private PasswordEncoder passwordEncoder;
    public User registerNewUser(User user) {
        String name=user.getFullname().toLowerCase();
        String fName=name.split(" ")[0];
        Random random= new Random();
        int rand=random.nextInt(1000,10000);
        String userName=fName+rand;
        user.setUserName(userName);
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        return userRepo.save(user);
    }
}
