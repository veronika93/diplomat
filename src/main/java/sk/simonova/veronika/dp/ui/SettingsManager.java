package sk.simonova.veronika.dp.ui;

import io.reactivex.Completable;
import io.reactivex.Scheduler;
import io.reactivex.schedulers.Schedulers;

import java.io.*;

public class SettingsManager {

    private static SettingsManager INSTANCE = new SettingsManager();
    private  String settingsFile = "./settings.ser";
    private  Settings settings;

    private SettingsManager(){

    }

    public String getMaplePath(){
        if(settings == null) {
            return null;
        }
        return settings.getMaplePath();
    }


    public boolean isPathSet(){
        return settings != null && settings.getMaplePath() != null;
    }

    public void setMaplePath(String maplePath) {
        settings.setMaplePath(maplePath);
    }

    public static SettingsManager getInstance() {
        return INSTANCE;
    }

    public Completable loadSettings() {
        return Completable.fromAction(() -> {
            File f = new File(settingsFile);
            if (f.exists()) {
                ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(settingsFile));
                settings = (Settings) inputStream.readObject();
            } else {
                f.createNewFile();
                settings = new Settings();
            }
        }).subscribeOn(Schedulers.io());
    }

    public Completable save()  {
        return Completable.fromAction(()-> {
        if(settings != null){
            ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(settingsFile));
            outputStream.writeObject(settings);
        }
        }).subscribeOn(Schedulers.io());

    }


}
