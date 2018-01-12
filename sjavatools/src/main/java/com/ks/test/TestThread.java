package com.ks.test;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @author pks
 * @version 2017年11月29日
 */
public class TestThread {
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		int a = 0;
		for (int i = 1; i <= 50; i++) {
			if(i%10-1<=5){
				Thread1 myThread = new Thread1(i);
				FutureTask<Boolean> faeature = new FutureTask<Boolean>(myThread);
				new Thread(faeature).start();
				Boolean bool = faeature.get();
				if(bool) a = a + 1;
			}else{
				Thread2 myThread = new Thread2(i);
				FutureTask<Boolean> faeature = new FutureTask<Boolean>(myThread);
				new Thread(faeature).start();
				Boolean bool = faeature.get();
				if(bool) a = a + 1;
			}
		}
		while (a==50){
			System.out.println("结束。。。。");
			a = a + 1;
		}
	}
	
}

class Thread1 implements Callable<Boolean> {
	private Integer num ;
	public Integer getNum() {
		return num;
	}
	public void setNum(Integer num) {
		this.num = num;
	}
	public Thread1() {
		// TODO Auto-generated constructor stub
	}
	
	public Thread1(Integer num) {
		super();
		this.num = num;
	}
	@Override
	public Boolean call() throws Exception {
		System.out.println("Thread1-"+num);
		return true;
	}
}

class Thread2 implements Callable<Boolean>{
	private Integer num ;
	public Integer getNum() {
		return num;
	}
	public void setNum(Integer num) {
		this.num = num;
	}
	public Thread2() {
		// TODO Auto-generated constructor stub
	}
	
	public Thread2(Integer num) {
		super();
		this.num = num;
	}
	
	@Override
	public Boolean call() throws Exception {
		System.out.println("Thread2-"+num);
		return true;
	}
}
