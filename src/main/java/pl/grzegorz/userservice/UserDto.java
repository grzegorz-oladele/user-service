package pl.grzegorz.userservice;

import lombok.Builder;

import java.util.List;

@Builder
public record UserDto(
        Long id,
        String name,
        String age,
        String city,
        List<PhoneDto> phones
) {
}