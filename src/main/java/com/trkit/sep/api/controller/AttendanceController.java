package com.trkit.sep.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.trkit.sep.api.entity.AttendanceRecord;
import com.trkit.sep.api.entity.Student;
import com.trkit.sep.api.entity.Subject;
import com.trkit.sep.api.entity.User;
import com.trkit.sep.api.model.AttendanceRecordRequest;
import com.trkit.sep.api.service.AttendanceRecordService;
import com.trkit.sep.api.service.StudentService;
import com.trkit.sep.api.service.SubjectService;
import com.trkit.sep.api.service.UserService;

@RestController
@RequestMapping("/attendance")
@CrossOrigin("http://localhost:4200")

public class AttendanceController {

	@Autowired
	private AttendanceRecordService attendanceRecordService;

	@Autowired
	private UserService userService;

	@Autowired
	private SubjectService subjectService;

	@Autowired
	private StudentService studentService;

	@GetMapping("/get-all-attendance-records")
	public List<AttendanceRecord> getAllAttendanceRecords() {
		return attendanceRecordService.getAllAttendanceRecords();
	}

	@GetMapping("/get-attendance-by-date-subjet/{date}/{subjectId}")
	public List<AttendanceRecord> getAllAttendanceRecords(@PathVariable String date, @PathVariable long subjectId) {

		return attendanceRecordService.getAllAttendanceRecords(date, subjectId);

	}

	@PostMapping("/take-attendance")
	public AttendanceRecord createAttendanceRecord(@RequestBody AttendanceRecordRequest request) {
		User user = userService.getUserByName(request.getUsername());
		Subject subject = subjectService.getSubjectById(request.getSubjectId());
		List<Student> students = studentService.getAllStudentsById(request.getStudentIds());

		AttendanceRecord attendanceRecord = new AttendanceRecord();
		attendanceRecord.setUser(user);
		attendanceRecord.setSubject(subject);
		attendanceRecord.setDate(request.getDate());
		attendanceRecord.setTime(request.getTime());
		attendanceRecord.setStudents(students);
		attendanceRecord.setNumberOfStudents(request.getStudentIds().size());

		System.out.println(attendanceRecord);

		return attendanceRecordService.saveAttendance(attendanceRecord);
	}
}
