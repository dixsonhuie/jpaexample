package com.gigaspaces.demo.common;

import org.hibernate.annotations.Type;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "EMP")
public class Employee {

   @Id
   private int id;
   private String name;
   private double salary;
   private String degree;

   @Type(type="com.gigaspaces.demo.common.PhoneUserType")
   private Phone phone;
   
   public Employee(int id, String name, double salary, String degree) {
      super( );
      this.id = id;
      this.name = name;
      this.salary = salary;
      this.degree = degree;
   }

   public Employee( ) {
      super();
   }

   public int getId( ) {
      return id;
   }
   
   public void setId(int id) {
      this.id = id;
   }
   
   public String getName( ) {
      return name;
   }
   
   public void setName(String name) {
      this.name = name;
   }

   public double getSalary( ) {
      return salary;
   }
   
   public void setSalary(double salary) {
      this.salary = salary;
   }

   public String getDegree( ) {
      return degree;
   }
   
   public void setDegree(String degree) {
      this.degree = degree;
   }

   public void setPhone(Phone phone) {this.phone = phone; }

   public Phone getPhone() { return phone; }
   
   @Override
   public String toString() {
      return "Employee [id=" + id + ", name=" + name + ", salary=" + salary + ", degree=" + degree + ", phone=" + phone + "]";
   }
}
