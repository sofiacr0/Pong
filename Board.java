import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Ellipse2D;

public class Board extends JComponent implements Runnable, KeyListener {
    private Dimension preferredSize = null;
    private Ellipse2D.Double ball;

    private Paddle paddle;
    private Paddle2 paddle2;
    BallRunner ballRunner;
    public Board() {
        setOpaque(true);
        setBorder(BorderFactory.createMatteBorder(5, 0, 5, 0, Color.WHITE));
        ball = new Ellipse2D.Double(20,320,20,20);

        paddle = new Paddle();
        paddle2 = new Paddle2();
        ballRunner = new BallRunner(ball,paddle, paddle2);
        Thread t1 = new Thread(ballRunner);
        t1.start();
        Thread t2 = new Thread( this);
        t2.start();
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (isOpaque()) {
            g.setColor( Color.BLACK );
            g.fillRect(0, 0, getWidth(), getHeight());
            g.setColor(getForeground());
        }

        Graphics2D g2 = (Graphics2D) g;

        g2.setStroke(new BasicStroke(5.0f));
        g2.setColor(Color.WHITE);
        g2.drawLine(320, 0, 320, 480);
        g2.setColor(Color.WHITE);
        g2.fill(ball);
        g2.setColor(Color.WHITE);
        g2.fill(paddle.getRectangle());
        g2.setColor(Color.WHITE);
        g2.fill(paddle2.getRectangle());
    }

    public Dimension getPreferredSize() {
        if (preferredSize == null) {
            return new Dimension(640, 480);
        } else {
            return super.getPreferredSize();
        }
    }

    public void setPreferredSize(Dimension newPrefSize) {
        preferredSize = newPrefSize;
        super.setPreferredSize(newPrefSize);
    }

    @Override
    public void run() {
        while ( true ) {
            repaint();
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch ( e.getKeyCode() ) {
            case KeyEvent.VK_W:
                paddle.moveUp();
                break;
            case KeyEvent.VK_S:
                paddle.moveDown();
                break;
            case KeyEvent.VK_UP:
                paddle2.moveUp();
                break;
            case KeyEvent.VK_DOWN:
                paddle2.moveDown();
                break;
            case KeyEvent.VK_SPACE:
                ballRunner.pause();
                break;
            case KeyEvent.VK_R:
                ballRunner.resume();
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
