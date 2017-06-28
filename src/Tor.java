import GLOOP.GLQuader;
import GLOOP.GLVektor;

public class Tor {
    private GLQuader up;
    private GLQuader left;
    private GLQuader right;
    private GLQuader back;

    static double dicke = 10;
    static double tiefe = 100;
    static double hoehe = 400;
    static double breite = 1000;

    private GLVektor position;

    public Tor(GLVektor position){
        this.position = position;

        up = new GLQuader(position.x, position.y + hoehe, position.z, breite, dicke, tiefe,"Bilder/Netz.jpg");
        left = new GLQuader(position.x - breite/2, position.y + hoehe/2, position.z, dicke, hoehe, tiefe, "Bilder/Netz.jpg");
        right = new GLQuader(position.x + breite/2, position.y + hoehe/2, position.z, dicke, hoehe, tiefe, "Bilder/Netz.jpg");
        back = new GLQuader(position.x, position.y + hoehe/2, position.z + tiefe / 2, breite, hoehe, dicke, "Bilder/Netz.jpg");
    }

    public GLVektor getPosition(){
        return position;
    }

    public boolean beinhaltet(GLVektor pos){
        if (pos.x > position.x - (breite / 2) && pos.x < position.x + (breite / 2)) return true;
        else return false;
    }
}
