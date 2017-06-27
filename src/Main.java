/**
 * Created by skn0tt on 21.06.17.
 */
import GLOOP.*;

public class Main {
    private final static double speed = 0.0001;

    private static GLKugel ball;
    private static GLKamera kamera;
    private static Tor tor;
    private static GLQuader wand;

    private static double tilt;
    private static double torwartPosX = 0;

    public static void main(String[] args){
        kamera = new GLKamera(500, 500);
        kamera.setzePosition(0, 500, -1000);

        GLBoden boden = new GLBoden("Bilder/Strasse.png");
        GLHimmel himmel = new GLHimmel("Bilder/Himmel.jpg");
        GLLicht licht = new GLLicht(0,1000,0);

        ball = new GLKugel(0,10,0,20);

        tor = new Tor(new GLVektor(0,0,2000));

        GLQuader torwart = new GLQuader(0,100,2000, 80, 200, 80);
        torwart.setzeFarbe(0,0,0);

        wand = new GLQuader(0,200,2100,7700,5000,20, "Bilder/Wand.png");

        kamera.setzeBlickpunkt(ball.gibX(), ball.gibY(), ball.gibZ() + 1000);

        GLTastatur tastatur = new GLTastatur();

        while(true){
            if (tastatur.istGedrueckt('d')) move(speed * -1, 0);
            else if(tastatur.istGedrueckt('a')) move(speed, 0);
            else if(tastatur.istGedrueckt('s')) move(0, speed*-1);
            else if(tastatur.istGedrueckt('w')) move(0, speed);
            else if (tastatur.links()) tilt(speed);
            else if (tastatur.rechts()) tilt(speed * -1);
            else if(tastatur.enter()) shoot();
        }
    }

    private static void move (double x, double z){
        x *= 2;
        z *= 2;
        if (ball.gibX() + x > -2000 && ball.gibX() + x < 2000) {
            ball.setzePosition(ball.gibX() + x, ball.gibY(), ball.gibZ());
            kamera.setzePosition(kamera.gibX() + x, kamera.gibY(), kamera.gibZ());
        }

        if (ball.gibZ() + z > -2000 && ball.gibZ() + z < 2000) {
            ball.setzePosition(ball.gibX(), ball.gibY(), ball.gibZ() + z);
            kamera.setzePosition(kamera.gibX(), kamera.gibY(), kamera.gibZ() + z);
        }
    }

    private static void tilt(double x){
        x /= 2;
        if (Math.abs(tilt + x) < 20) {
            tilt += x;
            kamera.rotiere(x, new GLVektor(0, 1, 0), ball.gibPosition());
        }
    }

    private static void shoot(){
        while (ball.gibZ() < tor.getPosition().z) {
            ball.setzePosition(ball.gibX(), ball.gibY(), ball.gibZ() + speed * 50000);
            Sys.warte(1);
        }

        if (tor.beinhaltet(ball.gibPosition())) System.out.println("Getroffen!");
        else System.out.println("Daneben!");

        ball.setzePosition(0,10,0);
        kamera.setzePosition(ball.gibX(), ball.gibY() + 500, ball.gibZ() - 1000);
        kamera.setzeBlickpunkt(ball.gibX(), ball.gibY(), tor.getPosition().z);
    }
}
