package com.g2.Progweb_II_EducaDin_Backend.service.impl;

import org.apache.commons.lang3.RandomStringUtils;

public class RanderPassword {

    public static String generatepassword() {

        int length = 10;
        boolean useLetters = true;
        boolean useNumbers = false;
        return RandomStringUtils.random(length, useLetters, useNumbers);
    }
}
