package com.starapp.incshift.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.starapp.incshift.dto.EmployeeRequest;
import com.starapp.incshift.dto.ManagerRequest;
import com.starapp.incshift.entity.Timesheet;
import com.starapp.incshift.repository.TimesheetRepository;

@RestController
public class TimesheetController {
	
	@Autowired
	TimesheetRepository timesheetRepository;
	
	
	//Update status of Time sheet of employees by manager	
	@CrossOrigin("*")
	@PatchMapping("/java/Manager/Timesheet/Approve/{timesheetid}")
	public Timesheet updatestatus(@PathVariable String timesheetid) {
		Timesheet timesheet = timesheetRepository.findBytimesheetId(timesheetid);
		timesheet.setApproval(1);
	
		return timesheetRepository.save(timesheet);
		
	}

	//Show Time sheet project wise
	@CrossOrigin("*")	
	@GetMapping("/java/showtimesheettomanagerprojectwise/{managerid}/{projectid}")
	public List<Timesheet> fetchAlltimesheetOfUserBasedOnProjectId(@PathVariable("managerid") int employeeid,@PathVariable("projectid") String projectid){
		
		return timesheetRepository.findByemployeeId(employeeid,projectid);
	}
	
	//show all time sheet to manager
	@CrossOrigin("*")	
	@GetMapping("/java/Manager/Timesheet/{employeeid}")
	public List<Timesheet> fetchAlltimesheetOfUser(@PathVariable("employeeid") int employeeid){
	
		return timesheetRepository.findByemployeeId(employeeid);
	}
	
	
	@CrossOrigin("*")
	 @PostMapping("java/Employee/Timesheet/approved")
	    public ResponseEntity<List<Timesheet>> fetchEmployee(@RequestBody EmployeeRequest employeeRequest){


	return ResponseEntity.ok(timesheetRepository.findByemployeeIdapproval( employeeRequest.getEmployeeId(),employeeRequest.getApproval()));
	    }

	//Alternate API..........
	@CrossOrigin("*")	
	@PostMapping("/java/Manager/Timesheet")
	public ResponseEntity<List<Timesheet>> fetchAlltimesheetOfManager(@RequestBody EmployeeRequest employeeRequest ){
		System.out.println(employeeRequest.getEmployeeId());
		return ResponseEntity.ok(timesheetRepository.findByemployeeId(employeeRequest.getEmployeeId()));
	}	
	//Alternate API..........
	@CrossOrigin("*")	
	@PostMapping("/java/Manager/Timesheet/Project")
	public ResponseEntity<List<Timesheet>> fetchAlltimesheetTOManagerBasedOnProjectId(@RequestBody ManagerRequest managerRequest){
		
		return ResponseEntity.ok(timesheetRepository.findByemployeeId(managerRequest.getEmployeeId(),managerRequest.getProjectId()));
	}
	//Show All Time sheet To  Manager (Just for testing)
		@CrossOrigin("*")
		@GetMapping("/java/showtimesheettomanager/{managerid}")
		public List<Timesheet> fetchAlltimesheet(@PathVariable("managerid") int managerid){
		  
			return timesheetRepository.findAllTimesheet(managerid);
		}

		//Show all  time sheet to employee based on approval status 1-approved and 0-rejected (Just for testing)
		@CrossOrigin("*")	
		@GetMapping("/java/showtimesheettoemployee/{employeeid}/{approval}")
		public List<Timesheet> fetchAlltimesheetOfUser(@PathVariable("employeeid") int employeeid,@PathVariable("approval")int approval){
		
			return timesheetRepository.findByemployeeIdapproval(employeeid,approval);
		}
}
