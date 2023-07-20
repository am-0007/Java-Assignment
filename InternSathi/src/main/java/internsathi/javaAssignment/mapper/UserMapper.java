package internsathi.javaAssignment.mapper;


import internsathi.javaAssignment.Enum.Role;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
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

        return roles.stream()
                .filter(userRole-> userRole.equals(Role.USER) || userRole.equals(Role.ADMIN))
                .map(Role::valueOf)
                .collect(Collectors.toList());
    }
}
