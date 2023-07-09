package uz.ba.finance.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import uz.ba.finance.dto.login.LoginDTO;
import uz.ba.finance.dto.login.SessionDTO;
import uz.ba.finance.dto.user.UserCreateDTO;
import uz.ba.finance.dto.user.UserDTO;
import uz.ba.finance.entity.User;
import uz.ba.finance.exceptions.BadRequestException;
import uz.ba.finance.mapper.UserMapper;
import uz.ba.finance.repository.UserRepository;
import uz.ba.finance.security.JwtTokenUtils;

import java.text.MessageFormat;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserService  implements UserDetailsService {

    private final UserMapper mapper;
    private final UserRepository repository;
    private final AuthenticationManager authManager;

    @Autowired
    public UserService(UserMapper mapper, UserRepository repository, AuthenticationManager authManager) {
        this.mapper = mapper;
        this.repository = repository;
        this.authManager = authManager;
    }

    public UserDTO register(UserCreateDTO dto) {
        if(repository.existsAllByUsername(dto.getUsername())){
            throw new BadRequestException("User with this username already exists!");
        }
        User user = mapper.toEntity(dto);
        return mapper.fromEntity(repository.save(user));
    }

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return repository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("Bad credentials!"));
    }


    public SessionDTO login(LoginDTO dto) {
        try {
            Authentication authenticate = authManager.authenticate(new UsernamePasswordAuthenticationToken(dto.getUsername(), dto.getPassword()));
            User user = (User) authenticate.getPrincipal();
            Set<String> authorities = user.getAuthorities()
                    .stream()
                    .map(GrantedAuthority::getAuthority)
                    .collect(Collectors.toSet());
            return new SessionDTO(MessageFormat.format("Bearer {0}", JwtTokenUtils.generateToken(user.getUsername(), authorities)));
        } catch (BadCredentialsException e) {
            throw new BadRequestException("Bad credentials !!!");
        }
    }

}
