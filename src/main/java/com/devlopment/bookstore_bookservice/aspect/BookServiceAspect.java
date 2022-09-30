package com.devlopment.bookstore_bookservice.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class BookServiceAspect {

    @Before(value = "execution(* com.devlopment.bookstore_bookservice.service.BookService.*(..)) && args(name,bookId)")
    public void beforeAdvice(JoinPoint joinPoint, String name, String bookId) {
        System.out.println("Before method:" + joinPoint.getSignature());

        System.out.println("Creating Employee with name - " + name + " and id - " + bookId);
    }

    @After(value = "execution(* com.devlopment.bookstore_bookservice.service.BookService.*(..)) && args(name,bookId)")
    public void afterAdvice(JoinPoint joinPoint, String name, String bookId) {
        System.out.println("After method:" + joinPoint.getSignature());

        System.out.println("Successfully created Employee with name - " + name + " and id - " + bookId);
    }
}

