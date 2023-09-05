package org.example.apiPresentation.user;

import org.example.Domain.user.UserDomain;
import org.example.Domain.user.UserService;
import org.example.apiPresentation.dto.inDto.UserInDto;
import org.example.apiPresentation.dto.inDto.UserPin;
import org.example.apiPresentation.dto.outDto.UserOutDto;
import org.example.apiPresentation.util.Response;
import org.example.apiPresentation.util.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;
import java.util.UUID;

@RestController
@RequestMapping("/account")
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping
    public ResponseEntity<Response> createAccount(@RequestBody UserInDto userInDto) {
        try {
            UserDomain userDomain = userService.createAccount(toDomain(userInDto));
            return ResponseEntity.status(Status.SUCCESS).body(Response.builder().result(toOutDto(userDomain)).message(null).build());
        } catch (Exception e) {
            return ResponseEntity.status(Status.BAD_REQUEST).body(Response.builder().result(null).message(Status.USER_EXISTS).build());
        }
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Response> getAccountById(@PathVariable("id") String accountId) {
        UserDomain userDomain = userService.getAccountById(accountId);
        if (Objects.isNull(userDomain)) {
            return ResponseEntity.status(Status.BAD_REQUEST).body(Response.builder().result(null).message(Status.ID_DOEST_MATCH).build());
        }
        return ResponseEntity.status(Status.SUCCESS).body(Response.builder().result(toOutDto(userDomain)).message(null).build());
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<Response> getAccountByName(@PathVariable("name") String accountName) {
        UserDomain userDomain = userService.getAccountByName(accountName);
        if (Objects.isNull(userDomain)) {
            return ResponseEntity.status(Status.BAD_REQUEST).body(Response.builder().result(null).message(Status.ID_DOEST_MATCH).build());
        }
        return ResponseEntity.status(Status.SUCCESS).body(Response.builder().result(toOutDto(userDomain)).message(null).build());
    }

    @PutMapping("/password")
    public ResponseEntity<Response> updatePassword(@RequestBody UserPin userPin) {
        UserDomain userDomain = userService.getAccountById(userPin.getAccountId());
        // Account Doesn't EXIST
        if (Objects.isNull(userDomain)) {
            return ResponseEntity.status(Status.BAD_REQUEST).body(Response.builder().result(null).message(Status.ID_DOEST_MATCH).build());
        }
        // Account Password Doesn't MATCH
        else if (!userDomain.getPassword().equals(userPin.getOldPassword())) {
            return ResponseEntity.status(Status.BAD_REQUEST).body(Response.builder().result(null).message(Status.PASSWORD_DOEST_MATCH).build());
        }
        // Update Password
        userDomain.setPassword(userPin.getNewPassword());
        userService.updatePassword(userDomain);
        return ResponseEntity.status(Status.SUCCESS).body(Response.builder().result(toOutDto(userDomain)).message(null).build());
    }

    @PostMapping("/login")
    public ResponseEntity<Response> login(@RequestBody UserInDto userInDto) {
        UserDomain userDomain = userService.getAccountByName(userInDto.getUserName());
        // Account Name Doesn't EXIST
        if (Objects.isNull(userDomain)) {
            return ResponseEntity.status(Status.BAD_REQUEST).body(Response.builder().result(null).message(Status.NAME_DOEST_MATCH).build());
        }
        else if (!userDomain.getPassword().equals(userInDto.getPassword())) {
            return ResponseEntity.status(Status.BAD_REQUEST).body(Response.builder().result(null).message(Status.PASSWORD_DOEST_MATCH).build());
        }
        // Login
        return ResponseEntity.status(Status.SUCCESS).body(Response.builder().result(toOutDto(userDomain)).message(null).build());
    }

    private UserDomain toDomain(UserInDto userInDto) {
        return UserDomain.builder()
                .userId(UUID.randomUUID().toString())
                .userName(userInDto.getUserName())
                .password(userInDto.getPassword())
                .build();

    }
    private UserOutDto toOutDto(UserDomain userDomain) {
        return UserOutDto.builder()
                .userId(userDomain.getUserId())
                .userName(userDomain.getUserName())
                .build();
    }

}
