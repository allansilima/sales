package com.ilegra.sales.service;

import java.io.File;
import java.io.IOException;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.ilegra.sales.utils.ConstantUtil;

@Component
public class IOScheduled {
	
	@Scheduled(cron = "0/1 * * * * *")
	public static void getFiles() throws IOException {
		File path = new File(ConstantUtil.PATH_IN);
		File[] files = path.listFiles(File::isFile);
		for (File file : files) {
			FileInput.process(file);
		}
	}
}
