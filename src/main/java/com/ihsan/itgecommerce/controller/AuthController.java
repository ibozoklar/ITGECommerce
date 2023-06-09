package com.ihsan.itgecommerce.controller;



import com.ihsan.itgecommerce.dto.request.CreateAdminRequestDto;
import com.ihsan.itgecommerce.dto.request.LoginRequestDto;
import com.ihsan.itgecommerce.dto.request.RegisterRequestDto;
import com.ihsan.itgecommerce.dto.request.VerifyEmailRequestDto;
import com.ihsan.itgecommerce.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;

    @GetMapping("/sayHello")
    public String sayHello(){
        return "Hello";
    }


    @PostMapping ("/register")
    public ResponseEntity<Boolean> register(@RequestBody RegisterRequestDto dto){

        return ResponseEntity.ok(userService.register(dto));
    }

    @PostMapping("/createAdmin")
    public ResponseEntity<Boolean> createAdmin(@RequestBody CreateAdminRequestDto dto){
        return ResponseEntity.ok(userService.createAdmin(dto));

    }

    @PostMapping ("/verifyEmail")
    public ResponseEntity<Boolean> verifyEmail(@RequestBody VerifyEmailRequestDto dto){

        return ResponseEntity.ok(userService.verifyEmail(dto));
    }

    @PostMapping("/login")
    public ResponseEntity<Long> login(@RequestBody LoginRequestDto dto){

        return ResponseEntity.ok(userService.login(dto));

    }

    @PostMapping("/loginWithSecurity")
    public ResponseEntity<String> loginWithSecurity(@RequestBody LoginRequestDto dto){

        return ResponseEntity.ok(userService.loginWithSecurity(dto));

    }
}
