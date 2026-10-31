package auca.AiGrading.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import auca.AiGrading.service.DashboardService;

@RestController 
@RequestMapping ("/api/dashboard")
public class DashboardController {
    @Autowired DashboardService dashboardServ;

    @GetMapping ("/summary")
    public Map<String, Long> getSummary() {
        return dashboardServ.getSummary();
    }
}
