package com.reader;

public class Employee {
    private int id;
    private String name;
    private double salary;
    private int age;
    private int grade;
     
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }

     
    public double getSalary() {
		return salary;
	}
	public void setSalary(double salary) {
		this.salary = salary;
	}
	public int getGrade() {
		return grade;
	}
	public void setGrade(int grade) {
		this.grade = grade;
	}
	@Override
    public String toString() {
        return "Employee:: ID="+this.id+" Name=" + this.name + " Age=" + this.age + " Salary=" + this.salary +
                " Grade=" + this.grade;
    }
     
}