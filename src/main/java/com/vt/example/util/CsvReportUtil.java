package com.vt.example.util;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.springframework.stereotype.Component;

import com.vt.example.entity.Customer;

@Component
public class CsvReportUtil
{
	public void writeCustomersToCsv(String region, List<Customer> customers) throws IOException
	{
		Path path = Paths.get("reports", region + "_report.csv");
		Files.createDirectories(path.getParent());

		try(BufferedWriter writer = Files.newBufferedWriter(path);
				CSVPrinter csvPrinter = new CSVPrinter(writer,
						CSVFormat.DEFAULT.builder()
								.setHeader("ID", "Name", "Email", "Gender", "Region")
								.get()))
		{
			for(Customer customer : customers)
			{
				csvPrinter.printRecord(customer.getId(), customer.getName(), customer.getEmail(), customer.getGender(), customer.getRegion());
			}
		}
	}
}
