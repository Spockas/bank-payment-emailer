package com.example.demo.utilities;

import lombok.extern.log4j.Log4j2;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpServerErrorException;

import java.io.IOException;

@Log4j2
public class TemplateReader {
    /**
     * Method to get template of html file
     * @param templateName name of template
     * @return String with retrieved template
     */
    public String readTemplate(String templateName) {
        String filePath = String.format("./templates/%s.html", templateName);
        try {
            byte[] encodedBytes = new ClassPathResource(filePath).getContentAsByteArray();
            return new String(encodedBytes);
        } catch (IOException exception) {
            log.error("Failed to read template at {}", filePath);
            log.error(exception);
            throw new HttpServerErrorException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
