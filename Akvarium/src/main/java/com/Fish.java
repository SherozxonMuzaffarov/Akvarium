package com;

import java.util.List;
import java.util.Random;

public class Fish implements Runnable {

    int id;
    boolean jinsi; //true=erkak
    boolean isLive =true;
    int lifeTime;
    long birthTime;

    Fish(int id, boolean jinsi){
        this.id=id;
        this.jinsi=jinsi;
        setLifeTime();

    }

    public void setLifeTime(){
        Random random = new Random();
        lifeTime = random.nextInt(50); // max yashash vaqti
        birthTime = System.currentTimeMillis();//tugilgan vaqti
    }

    @Override
    public void run() {
        while ((System.currentTimeMillis()-birthTime)/1000<lifeTime){
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        Main main = new Main();
        isLive=false;
        int[] fishInfo = main.getFishInfo();
        System.out.println(id +" id li  baliq o'ldi :( =>" + fishInfo[0] + " erkak, " + fishInfo[1] + " urg'ochi qoldi");

    }
}
