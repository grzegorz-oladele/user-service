package pl.grzegorz.userservice;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "phoneService", url = "${service.phone.url}")
public interface PhoneFeignClient {

    @GetMapping("/phones/{userId}")
    List<PhoneDto> getPhonesByUserId(@PathVariable long userId);
}