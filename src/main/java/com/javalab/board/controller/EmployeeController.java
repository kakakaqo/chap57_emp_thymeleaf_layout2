package com.javalab.board.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.javalab.board.service.EmployeeService;
import com.javalab.board.vo.Criteria;
import com.javalab.board.vo.Department;
import com.javalab.board.vo.EmployeeCommonDto;
import com.javalab.board.vo.Employees;
import com.javalab.board.vo.Job;
import com.javalab.board.vo.PageDto;

import lombok.extern.slf4j.Slf4j;


@Controller
@RequestMapping("/emp")
@Slf4j
public class EmployeeController {

	 @Autowired
	 private EmployeeService employeeService;
	
	 // 사원 목록 조회
	 /*
	 @GetMapping("/list1")
	 public String getEmployeeList1(EmployeeCommonDto dto, Model model){
		 List<EmployeeCommonDto> empList = employeeService.getEmployeesList(dto);
		 model.addAttribute("list", empList);
		 //return "/employee/list"; 
		 return "qlist"; 
	 }
	 */
	 
	 /*
	  * 사원 목록 조회
	  *  - @ModelAttribute("cri") Criteria cri 
	  *    : 뷰에서 cri 라는 이름으로 사용
	  */
	 @GetMapping("/list")
	 public String getEmployeeList2(EmployeeCommonDto dto, 
			 						@ModelAttribute("cri") Criteria cri, 
			 						Model model){
		 log.info("pageDto : " + dto.toString());
		 
		 List<EmployeeCommonDto> empList = employeeService.getEmployeesList(dto);
		 model.addAttribute("list", empList);
		 
		int total = employeeService.getTotalEmployees(cri); 
		PageDto pageDto = new PageDto(cri, total);
		  
		log.info("pageDto : " + pageDto.toString()); 
		model.addAttribute("pageMaker", pageDto);

		return "list"; 
	 }

	 // 사원 정보 보기
	 @GetMapping("/read")
	 public String getEmployees(@RequestParam("employeeId") int employeeIdid, Model model) {	
		 EmployeeCommonDto dto = employeeService.getEmployees(employeeIdid);
		 model.addAttribute("employee", dto);		 
		 return "view";
	 }
	 
	// 사원 등록 폼
	@GetMapping("/register")
	public String register(Employees emp, Model model){
		
		Map<String, Object> empMap = new HashMap<>();
		
		SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
		Date today = new Date();
		String hireDate = format1.format(today);
		// 사원 등록 폼 화면으로 이동
		
		// 직업 목록 조회
		List<Job> getJobList = employeeService.getJobList();
		List<Department> getDepartmentList = employeeService.getDepartmentList();
		List<Department> getManagerList = employeeService.getManagerList();
		empMap.put("hireDate", hireDate);
		empMap.put("jobList", getJobList);
		empMap.put("departmentList", getDepartmentList);
		empMap.put("managerList", getManagerList);
		
		 model.addAttribute("empMap", empMap);
		return "form"; 
	}	
	
	// 사원 등록 처리
	@PostMapping("/register")
	public String register(Employees emp){
		
		// 게시물 등록(저장)
		int result = employeeService.register(emp);
		
		// 저장후 목록 출력 컨트롤러 호출, redirect하면 사용자 화면의 주소창이 변경됨.
		return "redirect:list"; 
	}
	
}