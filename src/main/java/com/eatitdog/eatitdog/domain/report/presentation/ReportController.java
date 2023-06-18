package com.eatitdog.eatitdog.domain.report.presentation;

import com.eatitdog.eatitdog.domain.report.presentation.dto.request.CreateReportRequest;
import com.eatitdog.eatitdog.domain.report.service.ReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RequiredArgsConstructor
@RequestMapping("/report")
@RestController
public class ReportController {

    private final ReportService reportService;

    @PostMapping
    public void createReport(@RequestBody @Valid CreateReportRequest request) {
        reportService.createReport(request);
    }
}
