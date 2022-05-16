package com;

import java.util.List;
import java.util.Random;

public class FishService {

    public int[] getFishInfo(List<Fish> fishList) {
        int[] res = new int[2];
        for (Fish fish:fishList){
            if (fish.isLive && fish.jinsi){
                res[0]++; //erkak
            }else if(fish.isLive){
                res[1]++;
            }
        }
        return res;
    }

    public List<Fish>  naslQoldirish(List<Fish> fishList) {
        int counter=fishList.size();
        Random random = new Random();
        int first = random.nextInt(fishList.size());
        int second=first;
        while (first==second){
            second =random.nextInt(fishList.size());
        }
        if (fishList.get(first).isLive && fishList.get(second).isLive &&
                (fishList.get(first).jinsi != fishList.get(second).jinsi)){
            int kopayishlarSoni = random.nextInt(10); //uchrashganda kopayishlar Soni
            for(int i=1; i<kopayishlarSoni; i++){
                int jinsi = random.nextInt(2);
                System.out.println(first + " va " + second + " nasl qoldirdi :)  => id si:" + (++counter) + (jinsi==0?"erkak":"urg'ochi"));
                fishList.add(new Fish(counter, jinsi==0));
            }
        }
        return fishList;
    }

    public List<Fish> akvarinniTozalash(List<Fish> fishList) {
        for (int i=1; i< fishList.size(); i++){
            if (!fishList.get(i).isLive)
                fishList.remove(i);
        }
        return fishList;
    }
}
