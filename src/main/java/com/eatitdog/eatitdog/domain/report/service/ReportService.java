package com.eatitdog.eatitdog.domain.report.service;

import com.eatitdog.eatitdog.domain.product.domain.Product;
import com.eatitdog.eatitdog.domain.product.domain.repository.ProductRepository;
import com.eatitdog.eatitdog.domain.product.exception.ProductNotFoundException;
import com.eatitdog.eatitdog.domain.report.domain.Report;
import com.eatitdog.eatitdog.domain.report.domain.repository.ReportRepository;
import com.eatitdog.eatitdog.domain.report.presentation.dto.request.CreateReportRequest;
import com.eatitdog.eatitdog.global.annotation.ServiceWithTransactionalReadOnly;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@ServiceWithTransactionalReadOnly
public class ReportService {

    private final ReportRepository reportRepository;
    private final ProductRepository productRepository;

    @Transactional(rollbackFor = Exception.class)
    public void createReport(CreateReportRequest request) {
        Product product = productRepository.findById(request.getProductId())
                        .orElseThrow(() -> ProductNotFoundException.EXCEPTION);
        Report report = reportRepository.findByProduct(product)
                .orElseGet(() -> new Report(product));
        report.increaseCount();
        reportRepository.save(report);
    }
}
