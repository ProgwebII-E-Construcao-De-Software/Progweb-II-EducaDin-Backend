package com.g2.Progweb_II_EducaDin_Backend.converter;

import com.g2.Progweb_II_EducaDin_Backend.enums.Repeatable;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class RepeatableConverter implements AttributeConverter<Repeatable, String> {
    @Override
    public String convertToDatabaseColumn(Repeatable repeatable) {
        if (repeatable == null) {
            return null;
        }
        return repeatable.getName();
    }

    @Override
    public Repeatable convertToEntityAttribute(String s) {
        return Repeatable.valueOf(s);
    }
}