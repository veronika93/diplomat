package sk.simonova.veronika.dp.ui;

import java.io.Serializable;

public class Settings implements Serializable {
    private String maplePath;

    public String getMaplePath() {
        return maplePath;
    }

    public void setMaplePath(String maplePath) {
        this.maplePath = maplePath;
    }
}
