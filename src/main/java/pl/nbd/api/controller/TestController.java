package pl.nbd.api.controller;

import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import pl.nbd.api.model.Test;
import pl.nbd.api.service.TestService;

@RestController
@RequiredArgsConstructor
@Validated
@RequestMapping("/test")
public class TestController {

    private final TestService testService;

    @GetMapping("/{id}")
    public Test getTest(@PathVariable String id) {
        return testService.getTest(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Test addTest(@RequestBody @Valid Test test) {
        return testService.addTest(test);
    }
}
