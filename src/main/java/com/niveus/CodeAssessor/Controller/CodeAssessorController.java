package com.niveus.CodeAssessor.Controller;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.RestController;

import com.niveus.CodeAssessor.asessor.JavaAssessor;
import com.niveus.CodeAssessor.model.CodeResult;

@RestController
public class CodeAssessorController {

	public void doCodeAssessment(String folderPath) {

		CodeResult codeResult = new CodeResult();

		List<String> results = new ArrayList<String>();

		File[] files = new File(folderPath).listFiles();
		// If this pathname does not denote a directory, then listFiles() returns null.

		for (File file : files) {

			if (file.isFile()) {

				String fileName = file.getName();

				if ("pom.xml".equalsIgnoreCase(fileName)) {
					// It is java based app
					JavaAssessor javaAccessor = new JavaAssessor(codeResult);
					javaAccessor.processJavaCode();
				}

				results.add(fileName);
			}
		}

		// report gen - codeResult be concverted json file get pdf

		System.out.println("" + results);
		System.out.println(" Accessing the code......");
	}

}
