package com.example.demo.service;

import com.example.demo.dto.PaymentInfo;
import com.example.demo.utilities.TemplateFiller;
import com.example.demo.utilities.TemplateReader;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Log4j2
@Service
@RequiredArgsConstructor
public class PaymentInfoService {
    private final EmailService emailService;

    public void sendPaymentInfo(PaymentInfo paymentInfo, String email) {
        log.info("Sending payment email to: {}", email);

        TemplateReader templateReader = new TemplateReader();
        String bodyTemplate = templateReader.readTemplate("PaymentInfoEmail");
        String body = fillPaymentTemplate(bodyTemplate, paymentInfo);

        emailService.sendEmail(email, body, "Bank Payment Confirmation");
    }

    private String fillPaymentTemplate(String bodyTemplate, PaymentInfo paymentInfo) {
        TemplateFiller templateFiller = new TemplateFiller(bodyTemplate);
        templateFiller.replace("senderName", paymentInfo.getSender().getName());
        templateFiller.replace("senderIban", paymentInfo.getSender().getIban());
        templateFiller.replace("receiverName", paymentInfo.getReceiver().getName());
        templateFiller.replace("receiverIban", paymentInfo.getReceiver().getIban());
        templateFiller.replace("documentId", paymentInfo.getDocumentId());
        templateFiller.replace("amount", paymentInfo.getAmount());
        templateFiller.replace("paymentDate", paymentInfo.getPaymentDate());
        templateFiller.replace("paymentStatus", paymentInfo.getStatus());
        templateFiller.replace("paymentPurpose", paymentInfo.getPurpose());

        return templateFiller.getFilledTemplate();
    }
}
