package org.example;


public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        NetworkServiceTest networkServiceTest = new NetworkServiceTest();
        networkServiceTest.testWith_pooLSize_6_and_threadSize_20();
        System.out.println("Next Test !");
//        networkServiceTest.testWith_pooLSize_10_and_threadSize_5();

    }
}

