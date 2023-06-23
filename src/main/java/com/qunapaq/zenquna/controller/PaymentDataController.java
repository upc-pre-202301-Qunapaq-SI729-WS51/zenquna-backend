package com.qunapaq.zenquna.controller;

import com.qunapaq.zenquna.model.PaymentData;
import com.qunapaq.zenquna.repository.PaymentDataRepository;
import com.qunapaq.zenquna.exception.ValidationException;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/zq/v1")
@CrossOrigin(origins = "*")
public class PaymentDataController {
    private final PaymentDataRepository paymentDataRepository;

    public PaymentDataController(PaymentDataRepository paymentDataRepository) {
        this.paymentDataRepository = paymentDataRepository;
    }

    //EndPoint: http://localhost:8080/api/zq/v1/donor/{donorId}/paymentData
    //Method: GET
    @Transactional(readOnly = true)
    @RequestMapping("/donor/{donorId}/paymentData")
    public ResponseEntity<PaymentData> getPaymentDataByDonorId(@PathVariable Long donorId) {
        return new ResponseEntity<PaymentData>((PaymentData) paymentDataRepository.findByDonorId(donorId), HttpStatus.OK);
    }

    //EndPoint: http://localhost:8080/api/zq/v1/paymentData/{paymentDataId}
    //Method: GET
    @Transactional(readOnly = true)
    @RequestMapping("/paymentData/{paymentDataId}")
    public ResponseEntity<PaymentData> getPaymentDataById(@PathVariable Long paymentDataId) {
        return new ResponseEntity<PaymentData>(paymentDataRepository.findById(paymentDataId).orElseThrow(() -> new ValidationException("PaymentData not found")), HttpStatus.OK);
    }

    //EndPoint: http://localhost:8080/api/zq/v1/donor/{donorId}/paymentData
    //Method: POST
    @Transactional
    @PostMapping("/donor/{donorId}/paymentData")
    public ResponseEntity<PaymentData> createPaymentData(@RequestBody PaymentData paymentData, @PathVariable Long donorId) {
        return new ResponseEntity<PaymentData>(paymentDataRepository.save(paymentData), HttpStatus.CREATED);
    }
}
