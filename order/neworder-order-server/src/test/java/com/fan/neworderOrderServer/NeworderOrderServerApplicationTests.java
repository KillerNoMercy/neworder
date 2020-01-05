package com.fan.neworderOrderServer;

import com.alibaba.fastjson.JSON;
import com.fan.neworderOrderServer.dto.ProductDTO;
import com.fan.neworderOrderServer.model.JsonTest;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

//@SpringBootTest
class NeworderOrderServerApplicationTests {

    @Test
    void contextLoads() {
        String json = "[\n" +
                "{\n" +
                "\"id\": \"5656\",\n" +
                "\"count\": 5\n" +
                "},\n" +
                "{\n" +
                "\"id\": 5\n" +
                "}\n" +
                "]";
//        JsonTest parse = (JsonTest)JSON.parse(json);
//        System.out.println(parse);
//        JsonTest jsonTest = JSON.parseObject(json, JsonTest.class);
//        System.out.println(jsonTest);
        List<ProductDTO> productDTOList = JSON.parseArray(json, ProductDTO.class);
        System.out.println(productDTOList);
    }

}
