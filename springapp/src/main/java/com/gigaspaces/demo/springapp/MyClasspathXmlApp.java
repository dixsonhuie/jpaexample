package com.gigaspaces.demo.springapp;


import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class MyClasspathXmlApp {



    public static void main(String[] args) {

        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("/application-context.xml");

        MyTransactionalClient client = (MyTransactionalClient) applicationContext.getBean("transactionalClient");

        client.write(1234, false);

        System.out.println("About to exit...");
        System.exit(0);
    }

}









