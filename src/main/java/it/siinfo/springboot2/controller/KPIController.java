package it.siinfo.springboot2.controller;

import it.siinfo.springboot2.dto.KpiDTO;
import it.siinfo.springboot2.service.KPIService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/kpi")





//【P】【i】【p】【p】【o】
//【P】【i】【p】【p】【o】
//【P】【i】【p】【p】【o】
//【P】【i】【p】【p】【o】
//【P】【i】【p】【p】【o】



public class KPIController {
    private final KPIService pippo;

    public KPIController(KPIService kpiService) {
        this.pippo = kpiService;
    }

    @GetMapping
    public KpiDTO pippo() {
        return pippo.pippo();
    }


}
