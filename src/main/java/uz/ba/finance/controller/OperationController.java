package uz.ba.finance.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.ba.finance.dto.login.PeriodDTO;
import uz.ba.finance.dto.operation.OperationCreateDTO;
import uz.ba.finance.dto.operation.OperationDTO;
import uz.ba.finance.response.ResponseData;
import uz.ba.finance.service.OperationService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/operation")
public class OperationController {

    private final OperationService service;

    @PostMapping
    public ResponseEntity<ResponseData<OperationDTO>> create (OperationCreateDTO dto){
        return ResponseData.ok(service.create(dto));
    }

//    @GetMapping("/betweenTwoDates")
//    public ResponseEntity<ResponseData<List<Map<String, Object>>>> period (@Valid @RequestBody PeriodDTO dto){
//        return ResponseData.ok(service.betweenTwoDates(dto));
//    }

}
