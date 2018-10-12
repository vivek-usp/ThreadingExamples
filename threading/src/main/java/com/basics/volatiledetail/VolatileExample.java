package com.basics.volatiledetail;

public class VolatileExample {

//	public static volatile boolean active = true;
	public static boolean active = true;

	public static void main(String[] args) throws Exception {
		VolatileThreadExample eg = new VolatileThreadExample();
		eg.start();
		Thread.currentThread().sleep (1000);
		System.out.println(" The program end about to be invoked..........." + eg.counter);
		active = false;
		System.out.println(" The program end invoked..........." + eg.counter);
		//Since the active variable is not declared volatile there is no gurranttee that the thread will exit immediately or even notice the change at all.
		//However on 1.8 and this machine when I ran the program consistently the thread is exitting whether I declare a variable as volatile or not.
	}
	
	private static class VolatileThreadExample extends Thread {
		
		public int counter = 0;
		
		@Override
		public void run() {
			while (active) {
				System.out.println(this.currentThread().getId() + " I am running " + counter ++ );
				try {
					sleep(500);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			System.out.println(this.currentThread().getId() + " Thread ended " + counter);
		}
		
		public void end() {
			System.out.println(this.currentThread().getId() + " Thread stop request received.... " + counter);
			active = false;
		}
	}
	
}
