package com.luv2code.hibernate.demo;

import java.text.ParseException;
import java.util.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Student;

public class PrimaryKeyDemo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// create session factory
		SessionFactory factory = new Configuration()
									.configure("hibernate.cfg.xml")
									.addAnnotatedClass(Student.class)
									.buildSessionFactory();
		// create session
		Session session = factory.getCurrentSession();
		
		try {
			System.out.println("Creating new student object...");
			//Student tempStudent = new Student("Paul", "Wall", "paul@luv2code.com");
			session.beginTransaction();
			System.out.println("Saving the student...");
			System.out.println("Creating new student object...");
			String theDateOfBirthStr = "31/12/1998";
	        Date theDateOfBirth;
			theDateOfBirth = DateUtils.parseDate(theDateOfBirthStr);
			session.save(new Student("John", "Doe", "john@luv2code.com", theDateOfBirth));
			session.save(new Student("Mary", "Public", "mary@luv2code.com", theDateOfBirth));
			session.save(new Student("Bonita", "Applebum", "bonita@luv2code.com", theDateOfBirth));
			session.getTransaction().commit();
			System.out.println("Done!");
		}
		catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			factory.close();
		}
	}

}
