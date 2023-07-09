package uz.ba.finance.mapper;

import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import uz.ba.finance.dto.user.UserCreateDTO;
import uz.ba.finance.dto.user.UserDTO;
import uz.ba.finance.entity.User;

@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        nullValueMappingStrategy = NullValueMappingStrategy.RETURN_NULL,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.SET_TO_NULL
)
@Component
public abstract class UserMapper {

    protected PasswordEncoder passwordEncoder;

    @Autowired
    public void setPasswordEncoder(@Lazy PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Mapping(target = "password", expression = "java(passwordEncoder.encode(dto.getPassword()))")
    public abstract User toEntity(UserCreateDTO dto);

    public abstract UserDTO fromEntity(User user);
}
