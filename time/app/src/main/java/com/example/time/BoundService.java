package com.example.time;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

import java.util.Date;

public class BoundService extends Service {
    private  mybinder binder;
    public BoundService() {
        binder = new mybinder();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }
    public String getDate(){
        return new Date().toString();
    }


    class mybinder extends Binder{
        BoundService getService(){
            return BoundService.this;
        }

    }
}