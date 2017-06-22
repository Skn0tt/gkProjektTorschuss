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

    public static void main(String[] args){
        kamera = new GLKamera(500, 500);
        kamera.setzePosition(0, 500, -1000);

        GLBoden boden = new GLBoden("Bilder/Strasse.png");
        GLHimmel himmel = new GLHimmel("Bilder/Himmel.jpg");
        GLLicht licht = new GLLicht(0,1000,0);

        ball = new GLKugel(0,10,0,20);

        tor = new Tor(new GLVektor(0,0,2000));

        wand = new GLQuader(0,200,2100,7700,5000,20, "Bilder/Wand.png");
        GLTextur textur = new GLTextur("Bilder/Wand.png");

        kamera.setzeBlickpunkt(0,0,tor.getPosition().z);

        GLTastatur tastatur = new GLTastatur();

        while(true){
            if (tastatur.istGedrueckt('d')) move(speed * -1);
            else if(tastatur.istGedrueckt('a')) move(speed);
            else if(tastatur.enter()) shoot();
        }
    }

    private static void move (double x){
        x *= 2;
        if (ball.gibX() + x > -2000 && ball.gibX() + x < 2000) {
            ball.setzePosition(ball.gibX() + x, ball.gibY(), ball.gibZ());
            kamera.setzePosition(kamera.gibX() + x, kamera.gibY(), kamera.gibZ());
            kamera.setzeBlickpunkt(ball.gibX(), ball.gibY(), tor.getPosition().z);
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
        kamera.setzePosition(ball.gibX(), kamera.gibY(), kamera.gibZ());
        kamera.setzeBlickpunkt(ball.gibX(), ball.gibY(), tor.getPosition().z);
    }
}
