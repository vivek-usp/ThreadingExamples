package com.threadlocalusage;

import java.util.concurrent.TimeUnit;

/**
 * To Study the behavior of ThreadLocal in the setup where you want the same alogrithm to be run n
 * number of times by n number of thread without the other instance being 
 * @author vivekeagle
 *
 */
public class ThreadLocalExample {

	static class Thread1 implements Runnable{
		private ThreadLocal<String> localData = new ThreadLocal<>();

		public void run() {
			double randomNo = Math.random();
			localData.set("Vivek Thakare "+ randomNo);
			System.out.println("The instance "+ randomNo + " is running.... ");
			try {
				
				TimeUnit.SECONDS.sleep(2);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("THe value for local Data set is "+ localData.get());
		}
		
		public String getData () {
			return localData.get();
		}
		
	}
	
	public static void main(String[] args) {
		Thread1 thrd1 = new Thread1();
		
		Thread sample1 = new Thread(thrd1);
		Thread sample2 = new Thread(thrd1);
		
		sample1.start();
	 	sample2.start();
	 }
}
