package com.maximus.youngws;

import javax.servlet.ServletConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import java.util.Random;

@WebServlet(name="DataCenter",urlPatterns = "/DataCenter",loadOnStartup = 1)
public class DataCenter extends HttpServlet implements Runnable {
    public void init(ServletConfig config) {
        startup();
    }
    public  void startup(){
        new Thread(this).start();
    }
    @Override
    public void run() {
        int price = 100000;
        while(true) {
            int duration = 1000 + new Random().nextInt(2000);
            try {
                Thread.sleep(duration);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            //新价格围绕100000左右50%波动
            float random = 1 + (float) (Math.random() - 0.5);
            int newPrice = (int) (price * random);

            //查看的人越多，价格越高
            int total = ServerManager.getTotal();
            newPrice = newPrice*total;

            String messageFormat = "{\"price\":\"%d\",\"total\":%d}";
            String message = String.format(messageFormat, newPrice, total);
            //广播出去
            ServerManager.broadCast(message);
        }
    }
}
