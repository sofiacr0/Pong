import java.awt.geom.Rectangle2D;

public class Paddle {
    //public static final int WIDTH = 80;
    //public static final int HEIGHT = 30;
    public static final int WIDTH = 30;
    public static final int HEIGHT = 80;

    //private final int[] yPositions = { 0,80,160,240,320,400,480,560};
    private final int[] yPositions = { 0,80,160,240,320,400 };

    private Rectangle2D.Double rectangle;
    private int pos;

    public Paddle() {
        super();
        pos=3;
        rectangle = new Rectangle2D.Double(10,yPositions[pos],WIDTH,HEIGHT);
    }

    //moveUp
    public void moveDown() {
        if( pos < 7 ) {
            pos++;
            rectangle.y = yPositions[pos];
        }
    }

    //moveDown
    public void moveUp() {
        if( pos > 0 ) {
            pos--;
            rectangle.y = yPositions[pos];
        }
    }

    // regresa el rectangulo que se va a dibujar
    public Rectangle2D.Double getRectangle() {
        return rectangle;
    }

    // verifica si la pelota toco la raqueta
    public boolean check( int x, int y) {
        return rectangle.contains(x,y);
    }
}
