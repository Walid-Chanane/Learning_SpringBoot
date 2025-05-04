package com.example.demosecurity.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

//we can name this controller SecurityController
//we can also put this mapping in the demo controller it's just to regroup the security pages together
@Controller
public class LoginController {
    
    @GetMapping("/showMyLoginPage")
    public String showMyLoginPage(){
        
        return "fancy-login";
    }

    //add request mapping for access denied
    @GetMapping("/access-denied")
    public String showAccessDenied(){

        return "access-denied";
    }
}
