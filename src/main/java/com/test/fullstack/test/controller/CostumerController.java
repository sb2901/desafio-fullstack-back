package com.test.fullstack.test.controller;

import com.test.fullstack.test.dto.CostumerDto;
import com.test.fullstack.test.services.CostumerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/costumer")
public class CostumerController {

    @Autowired
    private CostumerService costumerService;

    @PostMapping
    public ResponseEntity<Void> add(@RequestBody CostumerDto costumerDto) {
        costumerService.add(costumerDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Transactional
    @PutMapping
    public ResponseEntity<Void> update(@RequestBody CostumerDto costumerDto) {
        costumerService.update(costumerDto);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @DeleteMapping
    public ResponseEntity<Void> delete(@RequestBody CostumerDto costumerDto) {
        costumerService.delete(costumerDto);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @Transactional
    @GetMapping("/list")
    public List<CostumerDto> getAll() {
        return costumerService.getAll();
    }

    @Transactional
    @GetMapping("/{id}")
    public ResponseEntity<CostumerDto>  getById(@PathVariable( value = "id") Long id) {
        return ResponseEntity.ok().body(costumerService.get(id.intValue()));
    }

}
