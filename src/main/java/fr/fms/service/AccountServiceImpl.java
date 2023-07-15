package fr.fms.service;

import fr.fms.dao.RoleRepository;
import fr.fms.dao.UserRepository;
import fr.fms.entities.AppRole;
import fr.fms.entities.AppUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
@Service
@Transactional
@Slf4j
public class AccountServiceImpl implements AccountService{
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private RoleRepository roleRepository;
    @Override
    public AppUser saveUser(AppUser user) {
        String hashPW = bCryptPasswordEncoder.encode(user.getPassword());
        user.setPassword(hashPW);
        log.info("Sauvegarde d'un nouvel utilisateur {}", user);
        return userRepository.save(user);
    }
    @Override
    public AppRole saveRole(AppRole role) {
        log.info("sauvegarde d'un nouveau role en base ");
        return roleRepository.save(role);
    }
    @Override
    public void addRoleToUser(String username, String rolename) {
        AppRole role = roleRepository.findByRoleName(rolename);
        AppUser user = userRepository.findByUsername(username);
        user.getRole().add(role);
        log.info("Association d'un role à un utilisateur");

    }
    @Override
    public AppUser findUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }
    @Override
    public ResponseEntity<List<AppUser>> listUser() {
        return ResponseEntity.ok().body(userRepository.findAll());
    }
}
