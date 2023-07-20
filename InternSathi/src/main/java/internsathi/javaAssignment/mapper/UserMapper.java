package internsathi.javaAssignment.mapper;


import internsathi.javaAssignment.Enum.Role;
import internsathi.javaAssignment.dto.UserRegistrationDto;
import internsathi.javaAssignment.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserMapper USER_MAPPER_INSTANCE = Mappers.getMapper(UserMapper.class);


    default List<Role> parseRole(String role) {
        List<String> roles = Arrays.stream(role.split(","))
                .map(String::trim)
                .toList();

        List<Role> roleList = new ArrayList<>();
        roles.forEach(authority -> {
            if (authority.equals(Role.USER.name())) {
                roleList.add(Role.USER);
            } else if (authority.equals(Role.ADMIN.name())) {
                roleList.add(Role.ADMIN);
            }
        });
        return roleList;
    }

    default User conversionFromRegistrationDtoToUser(UserRegistrationDto registrationDto) {
        return User.builder()
                .username(registrationDto.getUsername())
                .name(registrationDto.getName())
                .address(registrationDto.getAddress())
                .dateOfBirth(registrationDto.getDateOfBirth())
                .email(registrationDto.getEmail())
                .phoneNumber(registrationDto.getPhoneNumber())
                .password(registrationDto.getPassword())
                .role(registrationDto.getRole())
                .build();

    }
}
