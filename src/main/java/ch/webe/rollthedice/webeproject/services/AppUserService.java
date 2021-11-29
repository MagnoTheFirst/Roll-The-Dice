package ch.webe.rollthedice.webeproject.services;

import ch.webe.rollthedice.webeproject.model.AppUser;
import ch.webe.rollthedice.webeproject.repos.AppUserRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class AppUserService implements UserDetailsService {

    private final static String USER_NOT_FOUND="User with email %s not found";
    private final AppUserRepository appUserRepository;

    @Override
    public UserDetails loadUserByUsername(String userEmail) throws UsernameNotFoundException {
        return appUserRepository.findByEmail(userEmail).orElseThrow(() -> new UsernameNotFoundException(String.format(USER_NOT_FOUND, userEmail)));
    }


    public int enableAppUser(String email) {
        return appUserRepository.enableAppUser(email);
    }

    public ResponseEntity<AppUser> getByEmail(String email){
        AppUser appUser = appUserRepository.findByEmail(email).get();
        return new ResponseEntity<AppUser>(appUser, HttpStatus.OK);
    }
}
