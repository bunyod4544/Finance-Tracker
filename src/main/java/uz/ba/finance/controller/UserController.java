package uz.ba.finance.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.ba.finance.dto.login.LoginDTO;
import uz.ba.finance.dto.login.SessionDTO;
import uz.ba.finance.dto.user.UserCreateDTO;
import uz.ba.finance.dto.user.UserDTO;
import uz.ba.finance.response.ResponseData;
import uz.ba.finance.service.UserService;

import javax.validation.Valid;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserController {

    private final UserService service;

    @PostMapping("/login")
    public ResponseEntity<ResponseData<SessionDTO>> login(@Valid @RequestBody LoginDTO dto) {
        return ResponseData.ok(service.login(dto));
    }

    @PostMapping("/register")
    public ResponseEntity<ResponseData<UserDTO>> register(@Valid @RequestBody UserCreateDTO dto) {
        return ResponseData.ok(service.register(dto));
    }


}
