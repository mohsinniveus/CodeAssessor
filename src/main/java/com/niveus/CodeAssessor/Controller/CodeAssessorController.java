package com.niveus.CodeAssessor.Controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.springframework.web.bind.annotation.RestController;

import com.niveus.CodeAssessor.model.CodeResult;

@RestController
public class CodeAssessorController {

	public void doCodeAssessment(String folderPath) {

		CodeResult codeResult = new CodeResult();

		List<File> filesArrayList = new ArrayList<File>();
		List<File> directoryArrayList = new ArrayList<File>();
		ArrayList<String> excludeArrayList = new ArrayList<String>();
		excludeArrayList.add("target");
		excludeArrayList.add(".mvn");
		excludeArrayList.add(".settings");
		excludeArrayList.add(".git");

		File[] files = new File(folderPath).listFiles();
		// If this pathname does not denote a directory, then listFiles() returns null.

		for (File file : files) {

			if (file.isFile()) {

				String fileName = file.getName();

				if ("pom.xml".equalsIgnoreCase(fileName)) {

					codeResult.setApplicationType("SPRINGBOOT");

				} else if ("package.json".equalsIgnoreCase(fileName)) {
					codeResult.setApplicationType("NODEJS");
				}

				filesArrayList.add(file);
			} else if (file.isDirectory()) {

				boolean isExclude = excludeArrayList.contains(file.getName());
				if (!isExclude) {
					directoryArrayList.add(file);
				}

			}
		}

		System.out.println("" + filesArrayList);
		System.out.println("" + directoryArrayList);

		for (File f : directoryArrayList) {
			processDirectory(f.getAbsolutePath(), codeResult);
		}

	}

	public static void processDirectory(String path, CodeResult codeResult) {

		File root = new File(path);
		File[] list = root.listFiles();

		if (list == null)
			return;

		for (File f : list) {
			if (f.isDirectory()) {
				processDirectory(f.getAbsolutePath(), codeResult);
				System.out.println("Dir:" + f.getAbsoluteFile());
			} else {

				if (f.getName().equalsIgnoreCase("application.properties")) {
					parseApplicationProperties(f.getAbsolutePath());
				}

				System.out.println("File:" + f.getAbsoluteFile());
			}
		}

	}

	public static void parseApplicationProperties(String absolatePath) {

		try {
			InputStream input = new FileInputStream(absolatePath);
			Properties prop = new Properties();
			prop.load(input);

			System.out.println(prop.getProperty("spring.datasource.url"));
			System.out.println(prop.getProperty("spring.datasource.username"));
			System.out.println(prop.getProperty("spring.datasource.driver-class-name"));
			System.out.println(prop.getProperty("spring.jpa.database-platform"));

		} catch (Exception e) {

		}

	}
}
