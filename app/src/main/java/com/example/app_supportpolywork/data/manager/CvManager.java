package com.example.app_supportpolywork.data.manager;

public class CvManager{
    public static CvManager instance;

    private CvManager() {}
    public static CvManager getInstance() {
        synchronized (CvManager.class) {
            if(instance == null) {
                instance = new CvManager();
                return instance;
            }
        }
        return instance;
    }







}
