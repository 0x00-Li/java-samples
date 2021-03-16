package fit.ome;

import java.util.concurrent.*;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        int probe=1;
        System.out.println(1^2);
        probe ^= probe << 13;   // xorshift
        probe ^= probe >>> 17;
        probe ^= probe << 5;
        System.out.println(probe);
    }
    private static void fjpool(){
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        // 提交任务的两种方式
        // execute
        // - 提交任务后不接收返回值
        // submit
        // - 提交任务后接收返回对象 ForkJoinTask<T>
        // =========================
        // execute 示例
        forkJoinPool.execute(()->{
            System.out.println("this is execute Runnable");
        });
        forkJoinPool.execute(new ForkJoinTask<String>() {
            @Override
            public String getRawResult() {
                System.out.printf("ForkJoinTask getRawResult");
                return null;
            }

            @Override
            protected void setRawResult(String value) {
                System.out.printf("ForkJoinTask setRawResult");
            }

            @Override
            protected boolean exec() {
                return false;
            }
        });
        // submit 示例
        forkJoinPool.submit(()->{});

        forkJoinPool.submit(new Callable<Object>() {
            @Override
            public Object call() throws Exception {
                return null;
            }
        });

        forkJoinPool.submit(new ForkJoinTask<Object>() {
            @Override
            public Object getRawResult() {
                return null;
            }

            @Override
            protected void setRawResult(Object value) {

            }

            @Override
            protected boolean exec() {
                return false;
            }
        });

        forkJoinPool.submit(()->{},new Object());
    }
    private static void pool(){
        ThreadPoolExecutor executorService = new ThreadPoolExecutor(4,4,10,TimeUnit.SECONDS,new LinkedBlockingQueue<>());
        executorService.execute(()->{
            System.out.printf("this is runnable");
        });
    }
}
