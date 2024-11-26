package com.g2.Progweb_II_EducaDin_Backend.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Tip {
    private String type;
    private String message;
}