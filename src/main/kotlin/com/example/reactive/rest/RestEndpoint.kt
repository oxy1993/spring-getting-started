package com.example.reactive.rest

import com.example.reactive.dataprovider.DataProvider
import com.example.reactive.model.Car
import io.swagger.annotations.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody

@Controller
@Api
class RestEndpoint {

    @Autowired
    lateinit var dataProvider: DataProvider

    @Autowired
    lateinit var streamResponse: CarStreamResponseOutput

    @GetMapping(path = ["cars"], produces = [MediaType.APPLICATION_STREAM_JSON_VALUE])
    @ResponseBody
    @ApiResponses(value = [ApiResponse(code = 200, message = "Cars")])
    @ApiParam("nothing")
    @CrossOrigin(origins = ["http://localhost:8084"])
    fun getCarsAsStream(): StreamingResponseBody {
        return streamResponse
    }

    @GetMapping(path = ["cars-locust"], produces = [MediaType.APPLICATION_STREAM_JSON_VALUE])
    @CrossOrigin(origins = ["http://localhost:8084"])
    fun getCarsAsStreamWithExtraEndpointForLocust(): StreamingResponseBody {
        return streamResponse
    }

    @GetMapping(path = ["cars"], produces = [MediaType.APPLICATION_JSON_VALUE])
    @ResponseBody
    @CrossOrigin(origins = ["http://localhost:8084"])
    fun getCarsAsJson(): List<Car> {
        return dataProvider.getDataStream().toList().blockingGet()
    }

}
