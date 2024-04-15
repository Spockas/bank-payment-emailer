package com.example.demo.utilities;


import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.*;

class TemplateFillerTest {

    @Test
    public void testReplaceString() {
        String template = "Name: {{name}}";
        TemplateFiller filler = new TemplateFiller(template);

        filler.replace("name", "Some Name");

        assertEquals("Name: Some Name", filler.getFilledTemplate());
    }

    @Test
    public void testReplaceLocalDate() {
        String template = "Date: {{date}}";
        TemplateFiller filler = new TemplateFiller(template);
        LocalDate currentDate = LocalDate.now();
        String expectedDate = currentDate.format(DateTimeFormatter.ISO_DATE);

        filler.replace("date", currentDate);

        assertEquals("Date: " + expectedDate, filler.getFilledTemplate());
    }

    @Test
    public void testReplaceDouble() {
        String template = "Amount: {{amount}}";
        TemplateFiller filler = new TemplateFiller(template);

        filler.replace("amount", 10.50);

        assertEquals("Amount: 10.5", filler.getFilledTemplate());
    }

    @Test
    public void testReplaceMultiple() {
        String template = "Name: {{name}}; Amount: {{amount}}";
        TemplateFiller templateFiller = new TemplateFiller(template);

        templateFiller.replace("name", "Other Name");
        templateFiller.replace("amount", 3.14);

        assertEquals("Name: Other Name; Amount: 3.14", templateFiller.getFilledTemplate());
    }


    @Test
    public void testReplaceDontReplace() {
        String template = "Amount: {{amount}}";
        TemplateFiller templateFiller = new TemplateFiller(template);

        templateFiller.replace("name", "Other Name");

        assertEquals(template, templateFiller.getFilledTemplate());
    }
}