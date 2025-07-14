package com.vt.example.service;

import java.util.List;
import java.util.concurrent.Executor;

import org.springframework.stereotype.Service;

import com.vt.example.entity.Customer;
import com.vt.example.repository.CustomerRepository;
import com.vt.example.util.CsvReportUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class VirtualReportService
{
	private final CustomerRepository repository;
	private final CsvReportUtil csvReportUtil;

	private final Executor virtualThreadExecutor;

	public VirtualReportService(CustomerRepository repository, CsvReportUtil csvReportUtil, Executor virtualThreadExecutor)
	{
		this.repository = repository;
		this.csvReportUtil = csvReportUtil;
		this.virtualThreadExecutor = virtualThreadExecutor;
	}

	public void generateReportForRegion(String region)
	{
		virtualThreadExecutor.execute(() ->
		{
			log.info("Virtual generating report for region: {} | {}", region, Thread.currentThread());

			List<Customer> customers = repository.findByRegion(region);// 1
			try
			{
				csvReportUtil.writeCustomersToCsv("virtual_" + region, customers);// 2
			}
			catch(Exception e)
			{
				System.out.println("❌ Virtual Error writing report for region: " + region);
			}
		});
	}
}
