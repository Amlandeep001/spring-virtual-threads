package com.vt.example.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.vt.example.entity.Customer;
import com.vt.example.repository.CustomerRepository;
import com.vt.example.util.CsvReportUtil;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class ReportService
{
	private final CustomerRepository repository;
	private final CsvReportUtil csvReportUtil;

	// 300
	// tomcat default thread 200
	// 200 request processing
	// 100 request waiting in queue
	public void generateReportForRegion(String region)
	{
		log.info("generating report for region: {} | {}", region, Thread.currentThread());

		List<Customer> customers = repository.findByRegion(region);// 1
		try
		{
			csvReportUtil.writeCustomersToCsv("simple_" + region, customers);// 2
		}
		catch(Exception e)
		{
			System.out.println("❌ Error writing report for region: " + region);
		}
	}
}
