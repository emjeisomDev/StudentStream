package student;

import java.util.List;
import java.util.stream.Collectors;

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
	
	public List<StudentShortListRecord> studentByBirthDate(Integer month, Integer year){
		return students.stream()
					     .filter(std -> std.getBirthDate().getMonthValue() == month && std.getBirthDate().getYear() == year)
					     .map(std -> new StudentShortListRecord(
									  std.getRegistration(), 
									  std.getName(), 
									  std.getCourse()))
					     .toList();
	}
	
	public List<StudentShortListRecord> filterStudentsByAgeGroup(Integer lowerLimit, Integer upperLimit){
		
		return students.stream()
				       .collect(Collectors.partitioningBy(
				    		   stu -> stu.getAge() >= lowerLimit && stu.getAge() <= upperLimit))
				       .entrySet().stream()
				       .flatMap(entry -> entry.getValue().stream())
				       .map(stu -> new StudentShortListRecord(
				    		   stu.getRegistration(), stu.getName(), stu.getCourse()))
				       .toList();

	}
	
}
