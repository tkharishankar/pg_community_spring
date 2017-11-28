package com.pg.controller;

import com.pg.constants.Messages;
import com.pg.constants.UrlName;
import com.pg.model.Login;
import com.pg.model.ResponseMessage;
import com.pg.model.User;
import com.pg.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    UserRepository userRepository;

    @PostMapping(UrlName.REGISTRATION)
    public ResponseMessage createUser(@Valid @RequestBody User user) {
        if (!verifyUserExist(user.getMailId()))
            return new ResponseMessage(Messages.REG_USER_EXIST, false);
        else {
            userRepository.save(user);
            return new ResponseMessage(Messages.REG_SUCCESS, true);
        }
    }

    @PostMapping(UrlName.LOGIN)
    public ResponseMessage verifyLogin(@RequestBody Login login) {
        if (!findByMailIdAndPassword(login.getMailId(), login.getPassword()))
            return new ResponseMessage(Messages.LOG_SUCCESS, true);
        else
            return new ResponseMessage(Messages.LOG_FAILURE, false);
    }

    private boolean findByMailIdAndPassword(String mailId, String password) {
        User user = userRepository.findByMailIdAndPassword(mailId, password);
        if (user != null)
            return false;
        else
            return true;
    }

    private boolean verifyUserExist(String mailId) {
        User user = userRepository.findByMailId(mailId);
        if (user != null)
            return false;
        else
            return true;
    }

}
