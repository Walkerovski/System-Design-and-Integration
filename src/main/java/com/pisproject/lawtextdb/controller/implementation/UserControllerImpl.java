package com.pisproject.lawtextdb.controller.implementation;

import com.pisproject.lawtextdb.controller.UserController;
import com.pisproject.lawtextdb.service.implementation.UserAuthServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;

@CrossOrigin(origins = {"http://localhost:3000"})
@RestController
@RequestMapping("/")
public class UserControllerImpl implements UserController {
    @Autowired
    UserAuthServiceImpl userAuthService;

    @Override
    @PostMapping("/users")
    public String createUser(@RequestBody LoginRequest req){
        return userAuthService.createUser(req.username, req.password);
    }

    @Override
    @PostMapping("/login")
    public String getLoginToken(@RequestBody LoginRequest req) throws NoSuchAlgorithmException {
        return userAuthService.addToken(req.username, req.password);
    }

    @Override
    @PostMapping("/logout")
    public void deleteLoginToken(@RequestBody AuthRequest req) {
        userAuthService.deleteToken(req.username, req.token);
    }

}
