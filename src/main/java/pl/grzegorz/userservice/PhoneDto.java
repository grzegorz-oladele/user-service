package pl.grzegorz.userservice;

import lombok.Builder;

import java.time.LocalDate;

@Builder
public record PhoneDto(
        long id,
        String name,
        String model,
        LocalDate dateOfProduction
) {
}