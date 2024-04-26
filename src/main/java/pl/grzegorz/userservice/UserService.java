package pl.grzegorz.userservice;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {

    private final UserRepository userRepository;
    private final PhoneFeignClient phoneFeignClient;
    private final UserMapper userMapper;

    UserDto getUserById(long userId) {
        List<PhoneDto> phonesByUserId = phoneFeignClient.getPhonesByUserId(userId);
        var userEntity = userRepository.findById(userId)
                .orElseGet(() -> {
                    log.error("User using id -> [{}] not found", userId);
                    return null;
                });
        return userMapper.toUserDto(userEntity, phonesByUserId);
    }

    List<UserDto> getUsers() {
        return userRepository.findAll().stream()
                .map(userEntity -> {
                    var phones = phoneFeignClient.getPhonesByUserId(userEntity.getId());
                    return userMapper.toUserDto(userEntity, phones);
                })
                .collect(Collectors.toList());
    }

    void addUser(UserDto userDto) {
        var userEntity = userMapper.toUserEntity(userDto);
        var newUserEntity = userRepository.save(userEntity);
        log.info("Creating user using id -> [{}]", newUserEntity.getId());
    }

    void removeUserById(long userId) {
        userRepository.deleteById(userId);
        log.info("Removing user using id -> [{}]", userId);
    }
}