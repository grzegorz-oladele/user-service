package pl.grzegorz.userservice;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserMapper {

    UserDto toUserDto(UserEntity userEntity, List<PhoneDto> phonesByUserId) {
        return UserDto.builder()
                .id(userEntity.getId())
                .name(userEntity.getName())
                .age(userEntity.getAge())
                .city(userEntity.getCity())
                .phones(phonesByUserId)
                .build();
    }

    UserEntity toUserEntity(UserDto userDto) {
        return UserEntity.builder()
                .name(userDto.name())
                .age(userDto.age())
                .city(userDto.city())
                .build();
    }
}