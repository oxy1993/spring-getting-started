package com.example.reactive.rest

import io.swagger.annotations.Api
import org.springframework.http.MediaType
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ResponseBody

@Controller
@Api
class RestEndpoint {
    @GetMapping(path = ["hello"], produces = [MediaType.APPLICATION_JSON_VALUE])
    @ResponseBody
    @CrossOrigin(origins = ["http://localhost:8084"])
    fun getCarsAsJson(): String {
        return "Spring Boot Hello"
    }
}
