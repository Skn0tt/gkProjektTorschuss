/**
 * Created by skn0tt on 21.06.17.
 */
import GLOOP.*;

public class Main {
    private final static double speed = 0.01;

    private static GLKugel ball;
    private static GLKamera kamera;

    public static void main(String[] args){
        kamera = new GLKamera(500, 500);
        kamera.setzePosition(0, 500, 500);
        kamera.setzeBlickpunkt(0,0,0);

        GLBoden boden = new GLBoden("Bilder/Strasse.jpg");
        GLHimmel himmel = new GLHimmel("Bilder/Himmel.jpg");
        GLLicht licht = new GLLicht(0,1000,0);

        ball = new GLKugel(0,10,0,20);

        Tor tor = new Tor(new GLVektor(0,0,0));

        GLTastatur tastatur = new GLTastatur();

        while(true){
            if (tastatur.istGedrueckt('a')) move(speed * -1);
            else if(tastatur.istGedrueckt('d')) move(speed);
            else if(tastatur.enter()) shoot();
        }
    }

    private static void move (double x){
        ball.setzePosition(ball.gibX() + x, ball.gibY(), ball.gibZ());
        kamera.setzePosition(kamera.gibX() + x, kamera.gibY(), kamera.gibZ());
        kamera.setzeBlickpunkt(ball.gibPosition());
        System.out.println(ball.gibX());
    }

    private static void shoot(){
        while (ball.gibZ() < 500) {
            ball.setzePosition(ball.gibX(), ball.gibY(), ball.gibZ() + speed);
        }
    }
}
