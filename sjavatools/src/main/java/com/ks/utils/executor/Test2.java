package com.ks.utils.executor;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import com.ks.test.Random;
import com.ks.test.RoundRobin;

/**
 * @author Administrator
 * @version 2017年3月31日
 */
public class Test2 {
	
	public static final BlockingQueue<Runnable> queue = new ArrayBlockingQueue<>(100);
	public static ExecutorService executorService = new ThreadPoolExecutor(3, 5, 0L, TimeUnit.MILLISECONDS,queue);

	public static void test_1(final int i){
		executorService.execute(new Runnable() {
			@Override
			public void run() {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				
				System.out.println(Random.getServer());
			}
		});
	}
	
	public static void main(String[] args) {
		for (int i = 1; i < 10; i++) {
			test_1(i);
		}
		executorService.shutdown();
	}
}

