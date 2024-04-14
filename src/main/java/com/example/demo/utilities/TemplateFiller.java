package com.example.demo.utilities;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class TemplateFiller {
    private String template;

    public TemplateFiller(String template) {
        this.template = template;
    }

    public void replace(String target, String replacement) {
        template = template.replace(String.format("{{%s}}", target), replacement);
    }

    public void replace(String target, LocalDate replacement) {
        replace(target, replacement.format(DateTimeFormatter.ISO_DATE));
    }

    public void replace(String target, Double replacement) {
        replace(target, replacement.toString());
    }

    public void replace(String target, Long replacement) {
        replace(target, replacement.toString());
    }

    public String getFilledTemplate() {
        return template;
    }
}
