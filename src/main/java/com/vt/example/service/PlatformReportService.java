package com.vt.example.service;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import org.springframework.stereotype.Service;

import com.vt.example.entity.Customer;
import com.vt.example.repository.CustomerRepository;
import com.vt.example.util.CsvReportUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class PlatformReportService
{
	private final CustomerRepository repository;
	private final CsvReportUtil csvReportUtil;

	private final Executor executor;

	public PlatformReportService(CustomerRepository repository, CsvReportUtil csvReportUtil)
	{
		this.repository = repository;
		this.csvReportUtil = csvReportUtil;
		this.executor = Executors.newFixedThreadPool(5); // Initialize the executor here
	}

	public void generateReportForRegion(String region)
	{
		executor.execute(() ->
		{
			log.info("Platform generating report for region: {} | {}", region, Thread.currentThread());

			List<Customer> customers = repository.findByRegion(region);// 1
			try
			{
				csvReportUtil.writeCustomersToCsv("platform_" + region, customers);// 2
			}
			catch(Exception e)
			{
				System.out.println("❌ Platform Error writing report for region: " + region);
			}
		});
	}
}
