package com.g2.Progweb_II_EducaDin_Backend.controller;

import com.g2.Progweb_II_EducaDin_Backend.model.dto.TipDTO;
import com.g2.Progweb_II_EducaDin_Backend.service.TipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "${api.version}/tips")
@CrossOrigin
public class TipController {

    @Autowired
    private TipService tipService;

    @GetMapping("/{type}")
    public ResponseEntity<TipDTO> getRandomTipByType(@PathVariable String type, @RequestParam Long userId) {
        TipDTO tip = tipService.getRandomTipByType(type, userId);
        return ResponseEntity.ok(tip);
    }
}