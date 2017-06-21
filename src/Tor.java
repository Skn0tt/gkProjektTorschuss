import GLOOP.GLQuader;
import GLOOP.GLVektor;

/**
 * Created by skn0tt on 21.06.17.
 */
public class Tor {
    private GLQuader up;
    private GLQuader left;
    private GLQuader right;
    private GLQuader back;

    private double dicke = 10;
    private double tiefe = 100;
    private double hoehe = 100;
    private double breite = 200;

    private GLVektor position;

    public Tor(GLVektor position){
        this.position = position;

        up = new GLQuader(position.x, position.y + hoehe, position.z, breite, dicke, tiefe,"Bilder/Netz.jpg");
        left = new GLQuader(position.x - breite/2, position.y + hoehe/2, position.z, dicke, hoehe, tiefe, "Bilder/Netz.jpg");
        right = new GLQuader(position.x + breite/2, position.y + hoehe/2, position.z, dicke, hoehe, tiefe, "Bilder/Netz.jpg");
        back = new GLQuader(position.x, position.y + hoehe/2, position.z + tiefe, breite, hoehe, dicke, "Bilder/Netz.jpg");
    }

    public GLVektor getPosition(){
        return position;
    }
}
