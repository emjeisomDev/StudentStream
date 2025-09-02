package student;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

public class Student extends StudentsList {
	
	private Integer registration;
	private String name;
	private String gender;
	private Integer age;
	private String course;
	private Double grade;
	private LocalDate registrationDate;
	private LocalDate birthDate;
	
	public Student() {
		
	}
	
	public Student(Integer registration, String name, String gender, Integer age, String course, Double grade,
			LocalDate registrationDate, LocalDate birthDate) {
		this.registration = registration;
		this.name = name;
		this.gender = gender;
		this.age = age;
		this.course = course;
		this.grade = grade;
		this.registrationDate = registrationDate;
		this.birthDate = birthDate;
	}
	
	@Override
	public String toString() {
		return "{registration: " + registration + ", name: " + name + ", gender: " + gender + ", age: " + age + ", course: " + course + ", grade: " + grade + ", registrationDate: " + registrationDate + ", birthDate: " + birthDate + "}";
	}
	
    public List<Student> getStudents() {
        return StudentsList.getAllStudents();
    }

	public Integer getRegistration() {
		return registration;
	}

	public String getName() {
		return name;
	}

	public String getGender() {
		return gender;
	}

	public Integer getAge() {
		return age;
	}

	public String getCourse() {
		return course;
	}

	public Double getGrade() {
		return grade;
	}

	public LocalDate getRegistrationDate() {
		return registrationDate;
	}

	public LocalDate getBirthDate() {
		return birthDate;
	}

	@Override
	public int hashCode() {
		return Objects.hash(name, registration);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Student other = (Student) obj;
		return Objects.equals(name, other.name) && Objects.equals(registration, other.registration);
	}

	
	
}
