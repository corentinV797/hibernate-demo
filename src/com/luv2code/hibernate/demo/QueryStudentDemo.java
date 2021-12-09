package com.luv2code.hibernate.demo;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Student;

public class QueryStudentDemo {

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
			session.beginTransaction();
	
			List<Student> theStudents = session.createQuery("from Student").getResultList();
			displayStudents(theStudents);
			
			theStudents = session.createQuery("from Student s where s.lastName='Doe'").getResultList();
			System.out.println("\n\nStudents who have last name of Doe");
			displayStudents(theStudents);
			
			theStudents = 
					session.createQuery("from Student s where"
							+ " s.lastName='Doe' OR s.firstName='Daffy'").getResultList();
			System.out.println("\n\nStudents who have last name of Doe or first name of Daffy");
			displayStudents(theStudents);
			
			theStudents =
					session.createQuery("from Student s where"
							+ " s.email LIKE '%gmail.com'").getResultList();
			System.out.println("\n\nStudents who have email address ends with gmail.com");
			displayStudents(theStudents);
			
			session.getTransaction().commit();
			System.out.println("Done!");
		}
		finally {
			factory.close();
		}
	}

	private static void displayStudents(List<Student> theStudents) {
		for (Student tempStudent : theStudents) {
			 System.out.println(tempStudent);
		}
	}

}
