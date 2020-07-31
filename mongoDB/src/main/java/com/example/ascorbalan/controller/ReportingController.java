package com.example.ascorbalan.controller;

import com.example.ascorbalan.model.AvgRatingModel;
import com.example.ascorbalan.persistence.ReportService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("store/api/reports")
public class ReportingController {
    private ReportService reportService;

    public ReportingController(ReportService reportService) {
        this.reportService = reportService;
    }

    @GetMapping("avgRatingsReport")
    public List<AvgRatingModel> avgRatingReport(){
        return this.reportService.getAvgRatingReport();
    }
}
