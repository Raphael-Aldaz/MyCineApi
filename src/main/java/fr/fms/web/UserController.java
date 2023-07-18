package fr.fms.web;

import fr.fms.entities.AppRole;
import fr.fms.entities.AppUser;
import fr.fms.service.AccountServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/api")
public class UserController {
    @Autowired
    AccountServiceImpl accountService;
    @PostMapping("/newuser")
    public ResponseEntity<String> saveUser(@RequestParam("username") String username,
                                           @RequestParam("password") String password,
                                           @RequestParam("role") String[] roles){
        try {
            AppUser user = new AppUser(username, password);
            accountService.saveUser(user);
            for (String role : roles) {
                accountService.addRoleToUser(username, role);
            }
            return ResponseEntity.ok().body("Save user");

        } catch (Exception e){
            return ResponseEntity.badRequest().body("Erreur" + e.getMessage() );
        }
    }

    @DeleteMapping("/user/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable("id") Long id){
        AppUser user = accountService.findUserById(id);
        return  accountService.deleteUser(user);
    }
    @GetMapping("/users")
    public ResponseEntity<List<AppUser>>listUser(){
       return accountService.listUser();
    };
}
