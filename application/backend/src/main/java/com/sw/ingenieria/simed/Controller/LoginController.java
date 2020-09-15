package com.sw.ingenieria.simed.Controller;

import com.sw.ingenieria.simed.dto.LoginInput;
import com.sw.ingenieria.simed.dto.LoginOutput;
import com.sw.ingenieria.simed.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author Johan Céspedes Ortega at PUJ
 * @project SiMed
 * @date 07/09/2020
 */

@RestController
@RequestMapping("")
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST, RequestMethod.PUT,RequestMethod.DELETE})
public class LoginController{

    @Autowired
    private final LoginService loginService;

    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/login")
    @ResponseBody
    public LoginOutput login(@Valid @RequestBody LoginInput loginInput) throws Exception{
        return loginService.login(loginInput);
    }




}
