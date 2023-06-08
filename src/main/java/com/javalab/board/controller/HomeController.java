package com.javalab.board.controller;

import java.util.Date;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class HomeController {

		@GetMapping("/")
		public String home(Model model) {
			log.info("여기는 DemoController home()");
			//new Date() : java.util.Date 인식안되면 지웠다가 새로 타이핑
			model.addAttribute("date", new Date());	
			//return "index";
			return "redirect:emp/list";
		}	
	}