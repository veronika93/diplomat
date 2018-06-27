package sk.simonova.veronika.dp;

import org.math.plot.Plot2DPanel;

import javax.swing.*;
import java.awt.*;

public class Maple {

    String alfa;
    String delta;
    String r;
    String uzitok;
    String znecistenie;
    String vztah;
    String znamienko;

    public void setAlfa(String alfa) {
        this.alfa = alfa;
    }

    public void setDelta(String delta) {
        this.delta = delta;
    }

    public void setR(String r) {
        this.r = r;
    }

    public void setUzitok(String uzitok) {
        this.uzitok = uzitok;
    }

    public void setZnecistenie(String znecistenie) {
        this.znecistenie = znecistenie;
    }

    public void setVztah(String vztah) {
        this.vztah = vztah;
    }

    public void setZnamienko(String znamienko) {
        this.znamienko = znamienko;
    }


    public Maple() {
    }

    public Maple(String alfa, String delta, String r, String uzitok, String znecistenie, String vztah, String znamienko) {
        this.alfa = alfa;
        this.delta = delta;
        this.r = r;
        this.uzitok = uzitok;
        this.znecistenie = znecistenie;
        this.vztah = vztah;
        this.znamienko = znamienko;
    }

    public void createProduction()  {

    }

    public static void main(String[] args) {
        Maple maple = new Maple();
        maple.setAlfa("0.5");
        maple.createProduction();
    }
}
