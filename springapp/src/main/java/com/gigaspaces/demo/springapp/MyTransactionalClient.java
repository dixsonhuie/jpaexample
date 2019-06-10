package com.gigaspaces.demo.springapp;

import com.gigaspaces.demo.common.Employee;

import com.gigaspaces.demo.common.Phone;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

@Component
public class MyTransactionalClient {

    protected Logger log = Logger.getLogger(this.getClass().getName());


    //@Transactional // ignore for now, transaction has not been wired
    public void write(int i, boolean abort) {
        log.info("Entered write()");
        log.info("About to write id: " + i);


        EntityManagerFactory emfactory = Persistence.createEntityManagerFactory( "Employee" );

        EntityManager entitymanager = emfactory.createEntityManager( );
        entitymanager.getTransaction( ).begin( );


        Employee employee = new Employee( );
        employee.setId( i );
        employee.setName( "Gopal" );
        employee.setSalary( 40000 );
        employee.setDegree( "Technical Manager" );
        Phone phone = new Phone();
        phone.setCountryCode("1");
        phone.setAreaCode("646");
        phone.setPhoneNum("555-1212");

        employee.setPhone(phone);

        entitymanager.persist( employee );
        entitymanager.getTransaction( ).commit( );

        entitymanager.close( );
        emfactory.close( );


        if( abort == true ) {
            log.info("About to throw exception");
            throw new RuntimeException("Simulating application error in transactional method");
        }
    }
}
