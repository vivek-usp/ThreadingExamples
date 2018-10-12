package com.basics.volatiledetail;

public class VolatileInListenerTriggerExample {

	public static int myRun = 4;

	public static void main(String[] args) throws InterruptedException {
		new Thread(new ChangeMaker()).start();
		new Thread(new ChangeListener()).start();
		Thread.sleep(1000);
		System.out.println("Updating MyRun to stop...... ");
		myRun = 6;
		System.out.println("Updated MyRun to stop...... ");
	}

	static class ChangeMaker implements Runnable {

		public void run() {
			System.out.println("Started ChangeMaker......................");
			long counter = 0;
			while (myRun < 5) {
				System.out.println(Thread.currentThread().getId() + " " + counter++);
				try {
					Thread.currentThread().sleep(500);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			System.out.println("Stoppedd ChangeMaker......................");
		}

	}

	static class ChangeListener implements Runnable {

		int local_value = myRun;

		public void run() {
			System.out.println("Started Change Listener......................");
			while (myRun < 5) {
				System.out.println(Thread.currentThread().getId() + " Change Listener ");
				if (local_value != myRun) {
					System.out.println("Got Change for MY_INT " + local_value + " myRun + " + myRun);
					local_value = myRun;
				}
				try {
					Thread.currentThread().sleep(500);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			System.out.println("Started ChangeListener......................");
		}

	}
}
