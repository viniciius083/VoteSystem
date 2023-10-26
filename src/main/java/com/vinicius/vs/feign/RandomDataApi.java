package com.vinicius.vs.feign;

import com.vinicius.vs.associate.dtos.RandomInfoDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(url= "https://random-data-api.com/api/v2/users" , name = "random-data-api")
public interface RandomDataApi {
    @GetMapping()
    RandomInfoDTO searchRandomData();
}
