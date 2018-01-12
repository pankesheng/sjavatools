package com.ks.test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;


/**
 * @author pks
 * @version 2018年1月8日
 */
public class Test3 {
	public static void main(String[] args) {
		try {
			List<U> list = new ArrayList<>();
			for (int i = 1; i <= 9999; i++) {
				U u = new U("name"+i, 0);
				list.add(u);
			}
			System.out.println(System.currentTimeMillis());
			list = new Test3().list2Str(list, 10);
			System.out.println(System.currentTimeMillis());
//			for (U u : list) {
//				System.out.println(u.toString());
//			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public List<U> list2Str(List<U> list, final int nThreads)
			throws Exception {
		if (list == null || list.isEmpty()) {
			return null;
		}
		StringBuffer ret = new StringBuffer();
		ExecutorService executorService = Executors.newFixedThreadPool(nThreads);
		List<Future<String>> futures = new ArrayList<Future<String>>();
		for (final U u : list) {
			Callable<String> task = new Callable<String>() {
				@Override
				public String call() throws Exception {
					Thread.sleep(10);
					u.setAge(new Random().nextInt(100));
					return "";
				}
			};
			futures.add(executorService.submit(task));
		}

		for (Future<String> future : futures) {
			ret.append(future.get());
		}
		executorService.shutdown();

		return list;
	}
	static class U{
		private String name;
		private Integer age;
		
		public U() {
			// TODO Auto-generated constructor stub
		}
		
		public U(String name, Integer age) {
			super();
			this.name = name;
			this.age = age;
		}

		@Override
		public String toString() {
			return name+":"+age;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public Integer getAge() {
			return age;
		}
		public void setAge(Integer age) {
			this.age = age;
		}
		
	}
}
