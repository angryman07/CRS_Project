package com.lt.crs.bean;

public class Course {
	private int courseId;
	private String courseName;
	private int onlineFees;
	private int offlieFees;
	private boolean courseAvailable = false;
	private int enrollmentCounter = 0;
//	private Professor professoreDetails;
//	private Department departmentDetails;
//	private String prerequisite;
	
	public int getCourseId() {
		return courseId;
	}
	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}
	public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	public int getOnlineFees() {
		return onlineFees;
	}
	public void setOnlineFees(int onlineFees) {
		this.onlineFees = onlineFees;
	}
	public int getOfflieFees() {
		return offlieFees;
	}
	public void setOfflieFees(int offlieFees) {
		this.offlieFees = offlieFees;
	}
	public boolean isCourseAvailable() {
		return courseAvailable;
	}
	public void setCourseAvailable(boolean courseAvailable) {
		this.courseAvailable = courseAvailable;
	}
	public int getEnrollmentCounter() {
		return enrollmentCounter;
	}
	public void setEnrollmentCounter(int enrollmentCounter) {
		this.enrollmentCounter = enrollmentCounter;
	}
	public String toString() {
		return this.courseId + " " + this.courseName;
	}
	@Override
    public boolean equals(Object o) {
 
        if (o == this) {
            return true;
        }
 
        if (!(o instanceof Course)) {
            return false;
        }

        Course c = (Course) o;

        return this.getCourseId() == c.getCourseId();
    }
//	public Professor getProfessoreDetails() {
//		return professoreDetails;
//	}
//	public void setProfessoreDetails(Professor professoreDetails) {
//		this.professoreDetails = professoreDetails;
//	}
//	public Department getDepartment() {
//		return departmentDetails;
//	}
//	public void setDepartment(Department departmentDetails) {
//		this.departmentDetails = departmentDetails;
//	}
//	public String getPrerequisite() {
//		return prerequisite;
//	}
//	public void setPrerequisite(String prerequisite) {
//		this.prerequisite = prerequisite;
//	}
	
}
