package com.ksupwlt.stepcounttracker.controller.prototype;

import com.ksupwlt.stepcounttracker.entity.Target;
import com.ksupwlt.stepcounttracker.service.TargetService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/target")
public class TargetController {
    private TargetService targetService;

    public TargetController(TargetService targetService) {
        this.targetService = targetService;
    }

    @GetMapping
    public ResponseEntity<List<Target>> getAllTargets(){
        List<Target> targets = targetService.getAllTargets();
        return new ResponseEntity<List<Target>>(targets, HttpStatus.OK);
    }
}
