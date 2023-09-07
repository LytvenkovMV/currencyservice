package com.example.mycurrencycomparator.controller;

import com.example.mycurrencycomparator.dto.comparator.MyComparatorResponseDto;
import com.example.mycurrencycomparator.service.MyComparatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping(path = "/mycurrencycomparator/api/")
public class Controller {

    @Autowired
    private MyComparatorService myComparatorService;

    @GetMapping(path = {"/compare", "/compare/{code}"})
    public ResponseEntity<MyComparatorResponseDto> compare(@PathVariable(required = false) Optional<String> code) {

        return myComparatorService.compare(code);
    }
}
