package com.basics.executorservice;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class ThreadWaitShutdownNResult {
	public static void main(String[] args) throws InterruptedException, ExecutionException {
//		studyCallableBehavior();
		studyRunnableBehavior();
	}

	private static void studyRunnableBehavior() throws InterruptedException, ExecutionException {
		ExecutorService service = Executors.newSingleThreadExecutor();
		Future future = service.submit(new Runnable() {
			@Override
			public void run() {
				System.out.println("THis is Runnable and just wondering if this changes anything....");
				try {
					TimeUnit.SECONDS.sleep(2);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println("The sleep is over....");
			}
		});
		
		
		System.out.println("The submission is done.... "+future.get());
		service.shutdown();
		System.out.println("The Shutdown is invoked...... ");
		
	}

	private static void studyCallableBehavior() throws InterruptedException, ExecutionException {
		ExecutorService service = Executors.newSingleThreadExecutor();
		Callable<String> task = () -> {
				System.out.println("I am inside Callable returning String");
				TimeUnit.SECONDS.sleep(14);
				return "Swarali";
		};
		Future<String> resultAccess = service.submit(task);
		System.out.println(" Has the thread executed " + resultAccess.isDone());

		System.out.println("The termination request timed out.......");
//		service.shutdownNow();
		System.out.println(" The result String is " + resultAccess.get());
		service.shutdown();
	}

}
