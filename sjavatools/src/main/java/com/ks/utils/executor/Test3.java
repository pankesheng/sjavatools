package com.ks.utils.executor;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.ks.utils.random.RandomValue;

/**
 * @author Administrator
 * @version 2017年4月6日
 */
public class Test3 {
	
	private static ExecutorService executorService = Executors.newFixedThreadPool(2);
	
	
	public static void test_1(final Integer type,final int i){
		executorService.execute(new Runnable() {
			
			@Override
			public void run() {
				synchronized (type) {
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())+"||"+type+"-----"+i+"-----"+Thread.currentThread().getName());
				}
			}
		});
	}
	
	
	public static void main(String[] args) {
		for (int i = 0; i < 16; i++) {
			test_1(RandomValue.getNum(1, 2), i);
		}
		executorService.shutdown();
	}

}

