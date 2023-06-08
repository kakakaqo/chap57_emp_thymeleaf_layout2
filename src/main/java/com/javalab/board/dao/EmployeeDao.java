package com.javalab.board.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.javalab.board.vo.Criteria;
import com.javalab.board.vo.Department;
import com.javalab.board.vo.EmployeeCommonDto;
import com.javalab.board.vo.Employees;
import com.javalab.board.vo.Job;



/*
 * 매퍼 인터페이스 : Service Layer와 매퍼xml(sql쿼리문)을 연결해주는 역할(bridge)
 */
@Mapper
public interface EmployeeDao {
	
	List<EmployeeCommonDto> getEmployeesList(EmployeeCommonDto dto);
	EmployeeCommonDto getEmployees(int employeeId);
	int getTotalEmployees(Criteria cri);	// 페이징을 위한 사원숫자 조회	
	int register(Employees emp);
	List<Department> getDepartmentList();
	List<Job> getJobList();
	List<Department> getManagerList();

}