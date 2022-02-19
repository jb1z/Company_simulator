package com.company;

public class ThreadedTimer extends Thread{
    @Override
    public void run()
    {
        System.out.println("Привет из побочного потока!");
    }
}
