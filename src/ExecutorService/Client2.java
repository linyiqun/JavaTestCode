package ExecutorService;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * future模式线程池测试
 * 
 * @author Administrator
 * 
 */
public class Client2 {
	public static void main(String[] args) {
		ExecutorService executorService = Executors.newCachedThreadPool();
		List<Future<String>> resultList = new ArrayList<Future<String>>();
		Future<String> future;
		
		//创建10个任务并执行
		for(int i=0; i<10; i++){
			future = executorService.submit(new TaskWithResult(i));
			//将任务集存储到List中
			resultList.add(future);
		}
		
		executorService.shutdown();
		System.out.println("all thread end");
		
		for (Future<String> fs : resultList) {
			try {
				System.out.println(fs.get());
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ExecutionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	static class TaskWithResult implements Callable<String> {
		/**
		 * 表示ID
		 */
		private int id;

		public TaskWithResult(int id) {
			this.id = id;
		}

		/**
		 * 任务的具体过程，一旦任务传给ExecutorService的submit方法，则该方法自动在一个线程上执行。
		 * 
		 * @return
		 * @throws Exception
		 */
		public String call() throws Exception {
			System.out.println("call()方法被自动调用,干活！！！             "
					+ Thread.currentThread().getName());
			// 一个模拟耗时的操作
			for (int i = 999999999; i > 0; i--);
			
			return "call()方法被自动调用，任务的结果是：" + id + "    "
					+ Thread.currentThread().getName();
		}
	}
}
