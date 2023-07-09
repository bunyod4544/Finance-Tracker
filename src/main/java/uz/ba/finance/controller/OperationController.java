package uz.ba.finance.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.ba.finance.criteria.OperationCriteria;
import uz.ba.finance.dto.login.PeriodDTO;
import uz.ba.finance.dto.operation.OperationCreateDTO;
import uz.ba.finance.dto.operation.OperationDTO;
import uz.ba.finance.response.ResponseData;
import uz.ba.finance.service.OperationService;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/operation")
public class OperationController {

    private final OperationService service;

    @PostMapping
    public ResponseEntity<ResponseData<OperationDTO>> create(@RequestBody OperationCreateDTO dto) {
        return ResponseData.ok(service.create(dto));
    }

    @GetMapping("/between-dates")
    public ResponseEntity<ResponseData<Map<String, Long>>> period(@Valid @RequestBody PeriodDTO dto) {
        return ResponseData.ok(service.betweenTwoDates(dto));
    }

    @GetMapping("/all")
    public ResponseEntity<ResponseData<List<OperationDTO>>> getAll(@Valid @RequestBody OperationCriteria criteria) {
        return ResponseData.ok(service.getAll(criteria));
    }

}
