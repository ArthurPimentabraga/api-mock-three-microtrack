package org.microtrack.controller;

import jakarta.validation.Valid;
import org.microtrack.dto.ProductDTO;
import org.microtrack.service.MockThreeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/status-update")
public class MockThreeController {

    public final MockThreeService service = new MockThreeService();

    @PostMapping
    public ResponseEntity<String> updateStatus(@RequestBody @Valid ProductDTO body) throws IOException, InterruptedException {
        try {
            service.updateStatusPayment(body);
            return ResponseEntity.ok("Deu certo!");
        } catch (Exception ex) {
            // TODO add print
            return ResponseEntity.internalServerError().body(ex.getMessage());
        }
    }

}
