package com.niveus.CodeAssessor;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.niveus.CodeAssessor.Controller.CodeAssessorController;

@SpringBootApplication
public class CodeAssessorApplication implements CommandLineRunner {
	
	@Autowired
	CodeAssessorController codeAssessorController;
	

	public static void main(String[] args) {
		SpringApplication.run(CodeAssessorApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		codeAssessorController.doCodeAssessment("/Users/apple/Documents/dev/NewJavaWorkSpace/springgreeting");
		
	}

}
