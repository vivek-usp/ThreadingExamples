package com.deamonthread;

import java.util.concurrent.TimeUnit;

public class ThreadGroupEx {

	public static void main(String[] args) {
//		Thread1 first = new Thread1(false);
		Thread1 second = new Thread1(true);
//		first.start();
		second.start();
		System.out.println("Main concluded....");
	}
	
	
	static class Thread1 extends Thread {
		public Thread1(boolean isDeamon) {
			super();
			this.setDaemon(isDeamon);
			System.out.println("The Thread with "+ getId() + " and isDeamon "+ isDeamon);
		}
		
		public void run() {
			try {
				runForEver(10);
			}
			catch(Exception exp) {
				exp.printStackTrace();
			}
		}

		private void runForEver(int value) throws InterruptedException {
			TimeUnit.SECONDS.sleep(1);
			System.out.println("The thread "+ getId() +" is alive and fine.." + value);
			runForEver(value-1);
		}
	}
}
