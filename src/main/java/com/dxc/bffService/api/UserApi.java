package com.dxc.bffService.api;

import com.dxc.bffService.api.model.User;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "bffService", url = "${user.url}")
public interface UserApi
{
    @GetMapping("/v1/users/{username}")
    User getUserByUsername(@PathVariable("username") String username);

    @GetMapping("/v1/users/login")
    String loginUser(@RequestParam("username") String username, @RequestParam("password") String password);

    @GetMapping("/v1/users/logout")
    String logoutUser();

    @PostMapping("/v1/users/")
    String upsertUser(@RequestBody User user);

    @DeleteMapping("/v1/users/{username}")
    String deleteUser(@PathVariable("username") String username);

}