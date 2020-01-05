package com.fan.neworderUserClient.feign;

import com.fan.neworderUserCommon.bo.UserBO;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * Auther: zhengfan
 * Date: 2020/1/3
 */
@Component
@org.springframework.cloud.openfeign.FeignClient(name = "user")
public interface UserClient {
    @PostMapping("/user/checkToken")
    UserBO checkToken(@RequestBody String token);
}
