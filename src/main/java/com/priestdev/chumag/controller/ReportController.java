package com.priestdev.chumag.controller;

import com.priestdev.chumag.entity.StandardResponse;
import com.priestdev.chumag.service.ReportingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/reporting")
public class ReportController {

    @Autowired
    private ReportingService reportingService;

    @PutMapping("/updatecoupons")
    public ResponseEntity<StandardResponse> updateCellCoupons(@RequestParam("zoneId") Long zoneId,
                                                              @RequestParam("coupons") int coupons,
                                                              @RequestParam("calls") int calls){
        return reportingService.updateCellReport(zoneId, coupons, calls);
    }
    @GetMapping("/generateweeklyreport")
    public ResponseEntity<StandardResponse> generateWeeklyReport(){
        return reportingService.generateWeeklyReport();
    }

    @GetMapping("/generateweeklyreportbymonth")
    public ResponseEntity<StandardResponse> generateWeeklyReportByMonth(@RequestParam("month") String month){
        return reportingService.generateWeeklyReportByMonth(month);
    }

    @GetMapping("/generateweeklyreportbymonthandyear")
    public ResponseEntity<StandardResponse> generateWeeklyReportByMonthAndYear(@RequestParam("month") String month,
                                                                        @RequestParam("year") int year){
        return reportingService.generateWeeklyReportByMonthAndYear(month, year);
    }

    @GetMapping("/generatemonthlyreport")
    public ResponseEntity<StandardResponse> generateMonthlyReport(@RequestParam("year") int year){
        return reportingService.generateMonthlyReportByYear(year);
    }


}
