package it.siinfo.springboot2.controller;

import it.siinfo.springboot2.dto.KpiDTO;
import it.siinfo.springboot2.service.KPIService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/kpi")
public class KPIController {
    private final KPIService kpiService;

    public KPIController(KPIService kpiService) {
        this.kpiService = kpiService;
    }

    @GetMapping
    public KpiDTO pippo() {
        return kpiService.pippo();
    }


}
