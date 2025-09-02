package student;

import java.util.List;

import student.records.StudentShortListRecord;

public class StudentsStreams {
	List<Student> students = new Student().getStudents();
	
	public StudentsStreams() {}
	
	public List<StudentShortListRecord>  filterStudentListByGender(String gender){
		return students.stream()
					   .filter(std -> std.getGender().equals(gender))
					   .map(std -> new StudentShortListRecord(std.getRegistration(), std.getName(), std.getCourse()))
					   .toList();
	}
	
}
