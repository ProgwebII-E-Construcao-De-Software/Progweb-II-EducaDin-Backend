package com.g2.Progweb_II_EducaDin_Backend.controller;

import br.ueg.progweb2.arquitetura.reflection.ModelReflection;
import com.g2.Progweb_II_EducaDin_Backend.model.dto.ModelTest;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "${api.version}/test")
@CrossOrigin()
public class TestController {

    @GetMapping(path = "/")
    @Operation(description = "End point to test")
    public String testModelReflection()
    {
        ModelTest md = new ModelTest(5L, "Nome", "", "blá");
        System.out.println();
        var response = ModelReflection.getInvalidMandatoryFields(md);
        return response.toString();

    }


}