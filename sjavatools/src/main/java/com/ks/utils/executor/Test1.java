package com.ks.utils.executor;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import com.ks.dto.ServiceResult;

/**
 * @author Administrator
 * @version 2017年3月30日
 */
public class Test1 {
	//创建一个可缓存线程池，如果线程池长度超过处理需要，可灵活回收空闲线程，若无可回收，则新建线程。
	private static ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
	
	//创建一个定长线程池，可控制线程最大并发数，超出的线程会在队列中等待。
	private static ExecutorService fixedThreadPool = Executors.newFixedThreadPool(2); 
	private static ExecutorService fixedThreadPool2 = Executors.newFixedThreadPool(3); 
	
	//创建一个定长线程池，支持定时及周期性任务执行。
	private static ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(5);  
	
	//创建一个单线程化的线程池，它只会用唯一的工作线程来执行任务，保证所有任务按照指定顺序(FIFO, LIFO, 优先级)执行。
	private static ExecutorService singleThreadExecutor = Executors.newSingleThreadExecutor();
	
	public static void test_0(){
		for (int i = 0; i < 10; i++) {  
		    final int index = i;  
		    try {  
		        Thread.sleep(index * 1000);  
		    } catch (InterruptedException e) {  
		        e.printStackTrace();  
		    }  
		    cachedThreadPool.execute(new Runnable() {  
		        public void run() {  
		            System.out.println(index);  
		        }  
		    });  
		} 
	}
	
	public static void test_1(){
		for (int i = 0; i < 10; i++) {  
		    final int index = i;  
		    fixedThreadPool.execute(new Runnable() {
				public void run() {
					try {  
		                System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())+":"+index);  
		                Thread.sleep(2000);  
		            } catch (InterruptedException e) {  
		                // TODO Auto-generated catch block  
		                e.printStackTrace();  
		            }  
				}  
		    });  
		}  
	}
	
	//延迟执行 表示延迟3秒执行。
	public static void test_2(){
		scheduledThreadPool.schedule(new Runnable() {  
			  
		    public void run() {  
		        System.out.println("delay 3 seconds");  
		    }  
		}, 3, TimeUnit.SECONDS);  
	}
	
	//定期执行 表示延迟1秒后每3秒执行一次。
	public static void test_3(){
		scheduledThreadPool.scheduleAtFixedRate(new Runnable() {  
		    public void run() {  
		        System.out.println("delay 1 seconds, and excute every 3 seconds");  
		    }  
		}, 1, 3, TimeUnit.SECONDS); 
	}
	
	//结果依次输出，相当于顺序执行各个任务。
	public static void test_4(){
		for (int i = 0; i < 10; i++) {  
		    final int index = i;  
		    singleThreadExecutor.execute(new Runnable() {  
		  
		        public void run() {  
		            try {  
		                System.out.println(index);  
		                Thread.sleep(2000);  
		            } catch (InterruptedException e) {  
		                // TODO Auto-generated catch block  
		                e.printStackTrace();  
		            }  
		        }  
		    });  
		} 
	}
	
	public static void test_5(final int i){
		fixedThreadPool.execute(new Runnable() {
			public void run() {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}  
				System.out.println("test_5:"+i+"["+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())+"]");
			}
		});
	}
	
	public static void test_6(final int i){
		fixedThreadPool2.execute(new Runnable() {
			public void run() {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println("test_6:"+i+"["+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())+"]");
			}
		});
	}
	
	public static void test_7(final int i){
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("test_7:"+i+"["+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())+"]");
	}
	
	 static class Task implements Callable<String>{  
        private int i;  
        public Task(int i){  
            this.i = i;  
        }  
        public String call() throws Exception {  
        	Thread.sleep(1000);
        	if(i%2==0){
        		return ServiceResult.initErrorJson(Thread.currentThread().getName() + "执行完任务：" + i+"["+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())+"]");
        	}else{
        		return ServiceResult.initSuccessJson(Thread.currentThread().getName() + "执行完任务：" + i+"["+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())+"]");
        	}
        }     
    }  
	
	private static void testExecutorCompletionService() throws InterruptedException, ExecutionException{  
        CompletionService<String> completionService = new ExecutorCompletionService<String>(fixedThreadPool);  
        for(int i = 1;i<= 10;i++ ){  
            completionService.submit(new Test1.Task(i)); 
            System.out.println(completionService.take().get());
        }  
    } 
	
	public static void test_8(){
		Thread t1 = new Thread(new Runnable() {
			public void run() {
				System.out.println("t1....");
				for (int i = 1; i <= 10; i++) {  
					test_7(i);
				} 
			}
		});
		Thread t2 = new Thread(new Runnable() {
			public void run() {
				System.out.println("t2....");
				for (int i = 1; i <= 10; i++) {  
					test_5(i);
				} 
			}
		});
		Thread t3 = new Thread(new Runnable() {
			public void run() {
				System.out.println("t3....");
				for (int i = 1; i <= 10; i++) {  
					test_6(i);
				} 
			}
		});
		t1.start();
		t2.start();
		t3.start();
	}
	
	public static void test_9(){
		ExecutorService executor = Executors.newFixedThreadPool(5);  
        final CompletionService<String> completionService = new ExecutorCompletionService<String>(executor);  
        Thread t1 = new Thread(new Runnable() {
			public void run() {
				System.out.println("t1....");
				for (int i = 1; i <= 10; i++) {  
					completionService.submit(new Test1.Task(i)); 
		            try {
						System.out.println("t1::::"+completionService.take().get());
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (ExecutionException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				} 
			}
		});
        Thread t2 = new Thread(new Runnable() {
			public void run() {
				System.out.println("t2....");
				for (int i = 1; i <= 10; i++) {  
					completionService.submit(new Test1.Task(i)); 
		            try {
						System.out.println("t2::::"+completionService.take().get());
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (ExecutionException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				} 
			}
		});
        Thread t3 = new Thread(new Runnable() {
			public void run() {
				System.out.println("t3....");
				for (int i = 1; i <= 10; i++) {  
					completionService.submit(new Test1.Task(i)); 
		            try {
						System.out.println("t3::::"+completionService.take().get());
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (ExecutionException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				} 
			}
		});
        t1.start();
        t2.start();
        t3.start();
	}
	
	public static void test_10(){
		int numThread = 5;  
        ExecutorService executor = Executors.newFixedThreadPool(numThread);  
        List<Future<String>> futureList = new ArrayList<Future<String>>();  
        for(int i = 0;i<numThread;i++ ){  
            Future<String> future = executor.submit(new Test1.Task(i));  
            futureList.add(future);  
        }  
                  
        while(numThread > 0){  
            for(Future<String> future : futureList){  
                String result = null;  
                try {  
                    result = future.get(0, TimeUnit.SECONDS);  
                } catch (InterruptedException e) {  
                    e.printStackTrace();  
                } catch (ExecutionException e) {  
                    e.printStackTrace();  
                } catch (TimeoutException e) {  
                    //超时异常直接忽略  
                }  
                if(null != result){  
                    futureList.remove(future);  
                    numThread--;  
                    System.out.println(result);  
                    //此处必须break，否则会抛出并发修改异常。（也可以通过将futureList声明为CopyOnWriteArrayList类型解决）  
                    break;  
                }  
            }  
        }  
	}
	

	public static void main(String[] args) throws InterruptedException, ExecutionException {
		testExecutorCompletionService();
	}

}

