package com.threadpool;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class ThreadPoolExample {

	static class Thread1<V> implements Callable<V> {

		private V classVar;
		private long sleepTime;

		public Thread1(V input, long sleepTime) {
			this.classVar = input;
			this.sleepTime = sleepTime;
		}

		public V call() throws Exception {
			System.out.println("The thread " + Thread.currentThread().getId() + " identifier " + classVar);
			TimeUnit.SECONDS.sleep(sleepTime);
			System.out.println("The thread " + Thread.currentThread().getId() + " identifier " + classVar);
			return classVar;
		}
	}

	public static void main(String[] args) {
		ExecutorService service = Executors.newFixedThreadPool(5);

		List<Future<String>> futureList1 = new ArrayList<Future<String>>(5);
		List<Future<Integer>> futureList2 = new ArrayList<Future<Integer>>(5);

		for (int i = 0; i < 10; ++i) {
			if (i % 2 == 0)
				futureList1.add(service.submit(new Thread1<String>("i " + i, 1)));
			else
				futureList2.add(service.submit(new Thread1<Integer>(i, 1)));
		}

		service.shutdown();
		if (service.isTerminated())
			System.out.println("This is terminated...... ");
		else
			System.out.println("This is not terminated yet...... ");

	}
}
