package uz.ba.finance.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final UserMapper mapper;
    private final UserRepository repository;
    private final AuthenticationManager authManager;

    public UserDTO register(UserCreateDTO dto) {
        User user = mapper.toEntity(dto);
        return mapper.fromEntity(repository.save(user));
    }

    public SessionDTO login(LoginDTO dto) {
        try {
            Authentication authenticate = authManager.authenticate(new UsernamePasswordAuthenticationToken(dto.getUsername(), dto.getPassword()));
            User user = (User) authenticate.getPrincipal();
            return new SessionDTO(MessageFormat.format("Bearer {0}", JwtTokenUtils.generateToken(user.getUsername())));
        } catch (BadCredentialsException e) {
            throw new BadRequestException("Bad credentials!");
        }
    }

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return repository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("Bad credentials!"));
    }
}
