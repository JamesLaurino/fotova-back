package com.fotova;

import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackages = {"com.drools.droolConfig",
        "com.drools.service"})
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
    }
}
