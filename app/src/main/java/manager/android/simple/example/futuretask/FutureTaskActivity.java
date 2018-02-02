package manager.android.simple.example.futuretask;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.Calendar;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import manager.android.simple.example.R;

public class FutureTaskActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_future_task);
    }

    public static void main(String[] args) {
        MyCallable myCallable1 = new MyCallable(1000);
        MyCallable myCallable2 = new MyCallable(5000);

        FutureTask<String> futureTask1 = new FutureTask<String>(myCallable1);
        FutureTask<String> futureTask2 = new FutureTask<String>(myCallable2);

        ExecutorService executorService = Executors.newFixedThreadPool(2);
        executorService.execute(futureTask1);
        executorService.execute(futureTask2);

        System.out.println(System.currentTimeMillis());

        while (true) {
            try {
                if (futureTask1.isDone() && futureTask2.isDone()) {
                    System.out.println("DONE");
                    executorService.shutdown();
                    return;
                }

                if (!futureTask1.isDone()) {
                    System.out.println("FutureTask1 output=" + futureTask1.get());
                }

                System.out.println("Waiting for FutureTask2 to complete");

                String s = futureTask2.get(1, TimeUnit.MILLISECONDS);
                if (s != null) {
                    System.out.println(System.currentTimeMillis());
                    System.out.println("FutureTask2 output=" + s);
                }
            }  catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }catch(TimeoutException e){
                //do nothing
            }
        }
    }

    private static class MyCallable implements Callable<String> {

        private long sleepTime;

        public MyCallable(long sleepTime) {
            this.sleepTime = sleepTime;
        }

        @Override
        public String call() throws Exception {
            Thread.sleep(sleepTime);
            return Thread.currentThread().getName();
        }
    }
}
