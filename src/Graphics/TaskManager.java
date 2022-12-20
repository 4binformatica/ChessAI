package Graphics;

import java.awt.Graphics;

import javax.swing.JFrame;

public class TaskManager extends JFrame implements Runnable{
    
    //create a task manager that shows the ammount of memory used
    public TaskManager() {
        super("Task Manager");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
        new Thread(this).start();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Runtime r = Runtime.getRuntime();
        long total = r.totalMemory();
        long free = r.freeMemory();
        long used = total - free;
        //convert the bytes to megabytes
        total /= 1024*1024;
        free /= 1024*1024;
        used /= 1024*1024;
        g.drawString("Total Memory: " + total + "MB", 10, 40);
        g.drawString("Free Memory: " + free + "MB", 10, 60);
        g.drawString("Used Memory: " + used + "MB", 10, 80);

        // write the percentage of memory used
        g.drawString("Percentage Used: " + (int)(100*used/total) + "%", 230, 40);
        g.drawString("Percentage Free: " + (int)(100*free/total) + "%", 230, 60);


        g.drawRect(10, 100, 200, 20);
        g.fillRect(10, 100, (int)(200*used/total), 20);

        g.drawRect(10, 120, 200, 20);
        g.fillRect(10, 120, (int)(200*free/total), 20);

        



    }

    
    @Override
    public void run() {
        while(true){
            repaint();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
