package com;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main{

    static List<Fish> fishList = new ArrayList<>();
    static Random random = new Random();
    static Fish fish;

    public static void main(String[] args) {

        int erkak = random.nextInt(5)+1; //boshida nechta erkak baliq tashlagani
        int urgochi = random.nextInt(5)+1;//boshida nechta urg'ochi baliq tashlagani

        for (int i =1; i<=erkak; i++){
            fish = new Fish(i, true);
                fishList.add(fish);
            System.out.println("jami erkak baliqlar:" + erkak + "ta , id si: "+ i);
        }
        for (int i =1+erkak; i<=urgochi + erkak; i++){

            fish = new Fish(i, false);
                fishList.add(fish);
            System.out.println("jami urg'ochi baliqlar: " + urgochi + "ta, id si: "+ i);
        }
        new Thread(fish).run();

        Runnable nasl = new Runnable() {
            @Override
            public void run() {
                if(getLive()){
                    nasl();
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }else
                    akvarinniTozalash();
            }
        };
        Runnable tozalash = new Runnable() {
            @Override
            public void run() {
                while (fishList.size()!=0){
                    akvarinniTozalash();
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        new Thread(nasl).start();
        new Thread(tozalash).start();
    }

    public int[] getFishInfo() {
        FishService fishService = new FishService();
        return fishService.getFishInfo(fishList);
    }

    public static void nasl(){
        FishService fishService = new FishService();
        int oldSize = fishList.size();
        List<Fish> fishList = fishService.naslQoldirish(Main.fishList);
        if (fishList.size()>oldSize){
            for (int i=oldSize+1;i<fishList.size();i++){
                new Thread(fishList.get(i)).run();
            }
        }
    }

    public static boolean getLive(){
        for (Fish fish: fishList){
            if (fish.isLive)
                return true;
        }
        return false;
    }

    //o'lgan baliqlardan tozalash
    public static void akvarinniTozalash(){
        FishService fishService = new FishService();
        fishList = fishService.akvarinniTozalash(Main.fishList);
    }
}
