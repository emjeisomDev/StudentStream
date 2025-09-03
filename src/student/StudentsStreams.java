package student;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import student.records.CourseGradesRecord;
import student.records.CourseQuantityRecord;
import student.records.StudentShortListRecord;
import student.records.StudentsShortListGradesRecord;

public class StudentsStreams {
	List<Student> students = new Student().getStudents();
	
	public StudentsStreams() {}
	
	public List<StudentShortListRecord>  filterStudentListByGender(String gender){
		return students.stream()
					   .filter(stu -> stu.getGender().equals(gender))
					   .map(stu -> new StudentShortListRecord(stu.getRegistration(), stu.getName(), stu.getCourse()))
					   .toList();
	}
	
	public List<StudentShortListRecord> studentByBirthDate(Integer month, Integer year){
		return students.stream()
					     .filter(stu -> stu.getBirthDate().getMonthValue() == month && stu.getBirthDate().getYear() == year)
					     .map(stu -> new StudentShortListRecord(
									  stu.getRegistration(), 
									  stu.getName(), 
									  stu.getCourse()))
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
	
	public StudentShortListRecord verifyRegistration(Integer registration) {
		var itExists = students.stream().anyMatch(stu -> stu.getRegistration().equals(registration));
		if (itExists) {
			return students.stream()
						   .filter(stu -> stu.getRegistration().equals(registration))
						   .findFirst()
						   .map(stu -> new StudentShortListRecord(stu.getRegistration(), stu.getName(), stu.getCourse()))
						   .orElseThrow(() -> new IllegalArgumentException("Estudante não encontrado para a matricula fornecida."));
		}
		return null;
	}
	
	public List<StudentsShortListGradesRecord> filterTop10Grades(){
		return students.stream()
						 .sorted(Comparator.comparing(Student::getGrade).reversed())
						 .map(stu -> new StudentsShortListGradesRecord(
								 stu.getRegistration(), 
								 stu.getName(),
								 stu.getGrade()
								 ))
						 .limit(10)
						 .toList();
	}
	
	public List<StudentsShortListGradesRecord> filterTop10Grades(String course){
		return students.stream()
						 .filter(stu -> stu.getCourse().equals(course.toLowerCase()))
						 .sorted(Comparator.comparing(Student::getGrade).reversed())
						 .map(stu -> new StudentsShortListGradesRecord(
								 stu.getRegistration(), 
								 stu.getName(),
								 stu.getGrade()
								 ))
						 .limit(10)
						 .toList();
	}
	
	public List<StudentsShortListGradesRecord> filterApprovedStudents(){
		return students.stream()
				       .filter(stu -> stu.getGrade() >= 7.0)
				       .map(stu -> new StudentsShortListGradesRecord(
				    		   stu.getRegistration(), stu.getName(), stu.getGrade()   ))
				       .sorted(Comparator.comparing(StudentsShortListGradesRecord::grade).reversed())
				       .toList();
	}
	
	public List<StudentsShortListGradesRecord> filterApprovedStudents(String course){
		return students.stream()
				       .filter(stu -> stu.getGrade() >= 7.0 && stu.getCourse().equals(course))
				       .map(stu -> new StudentsShortListGradesRecord(
				    		   stu.getRegistration(), stu.getName(), stu.getGrade()   ))
				       .sorted(Comparator.comparing(StudentsShortListGradesRecord::grade).reversed())
				       .toList();
	}
	
	public List<StudentsShortListGradesRecord> filterRepprovedStudents(){
		return students.stream()
				       .filter(stu -> stu.getGrade() < 7.0)
				       .map(stu -> new StudentsShortListGradesRecord(
				    		   stu.getRegistration(), stu.getName(), stu.getGrade()   ))
				       .sorted(Comparator.comparing(StudentsShortListGradesRecord::grade))
				       .toList();
	}
	
	public List<StudentsShortListGradesRecord> filterRepprovedStudents(String course){
		return students.stream()
				       .filter(stu -> stu.getGrade() < 7.0 && stu.getCourse().equals(course))
				       .map(stu -> new StudentsShortListGradesRecord(
				    		   stu.getRegistration(), stu.getName(), stu.getGrade()   ))
				       .sorted(Comparator.comparing(StudentsShortListGradesRecord::grade))
				       .toList();
	}
	
	public List<CourseGradesRecord> averageGradeByCourse(){
		return students.stream()
						.collect(Collectors.groupingBy(
									Student::getCourse,
									Collectors.averagingDouble(Student::getGrade)
								))
						.entrySet().stream()
						.map(stu -> new CourseGradesRecord(
									stu.getKey(),
									BigDecimal.valueOf(stu.getValue()).setScale(2, RoundingMode.HALF_UP).doubleValue()
								))
						.sorted(Comparator.comparing(CourseGradesRecord::grade).reversed())
						.toList();
	}
	
	public List<CourseQuantityRecord> qtdStudentsByCourse(){
		return students.stream()
				          .collect(Collectors.groupingBy(
				        		  Student::getCourse,
				        		  Collectors.counting()
				          ))
				          .entrySet().stream()
				          .map(stu -> new CourseQuantityRecord(
				        		  stu.getKey(), 
				        		  stu.getValue().intValue() ))
				          .sorted(Comparator.comparing(CourseQuantityRecord::course))
				          .toList();
	}
	
	
}
