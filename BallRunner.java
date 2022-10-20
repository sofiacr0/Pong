import java.awt.geom.Ellipse2D;

public class BallRunner implements Runnable {

    public static final int MAX_X = 640;
    public static final int MAX_Y = 480;
    public static final int SIGN = -1;

    public static final int DX = -10;
    public static final int DY = 10;
    private Ellipse2D.Double ball;

    private Paddle paddle;
    private Paddle2 paddle2;

    private int ballX;
    private int ballY;

    private boolean paused;

    public BallRunner(Ellipse2D.Double shape, Paddle p1, Paddle2 p2) {
        ball = shape;
        paddle = p1;
        paddle2 = p2;
        ballX = 320;
        ballY = 240;
        ball.x = ballX ;
        ball.y = ballY;
        paused=false;
    }

    public void pause() {
        paused=true;
    }
    public void resume() {
        paused=false;
    }

    @Override
    public void run() {
        int directionY = 1;
        int directionX = 1;
        int ballMinX, ballMaxX, ballMinY, ballMaxY;

        while( true ) {
            if(paused){
                continue;
            }
            ballMinX = (int)ball.getMinX();
            ballMaxX = (int)ball.getMaxX();
            ballMinY = (int)ball.getMinY();
            ballMaxY = (int)ball.getMaxY();

            int y = (int) ball.getMaxY();
            int x = (int)  ball.getX();

            if( paddle.check(ballMinX,ballMinY) || paddle.check(ballMaxX,ballMaxY)) {
                directionY = directionY * SIGN;
                ballX = ballX + (DX * directionX) ;
                ballY = ballY + (DY * directionY);
                ball.x = ballX;
                ball.y = ballY;
                continue;
            }

            if( paddle2.check(ballMinX,ballMinY) || paddle2.check(ballMaxX,ballMaxY) ) {
                directionY = directionY * SIGN;
                ballX = ballX + (DX * directionX) ;
                ballY = ballY + (DY * directionY);
                ball.x = ballX ;
                ball.y = ballY;
                continue;
            }

            if( ball.getMinY() < 0 ) {
                directionY = directionY * SIGN;
            }

            if( ball.getMinX() < 0 ) {
                directionX = directionX * SIGN;
            }
            if( ball.getMaxY() > MAX_Y ) {
                directionY = directionY * SIGN;
            }

            if( ball.getMaxX() > MAX_X ) {
                directionX = directionX * SIGN;
            }

            if(ball.x == MAX_X){
                ballX = 310;
                ball.x = ballX;
                ballY = 240;
                ball.y = ballY;
            }
            if(ball.x == 0){
                ballX = 310;
                ball.x = ballX;
                ballY = 240;
                ball.y = ballY;
            }
            if(ball.y == MAX_Y || ball.y == 0){
                directionY = directionY*SIGN;
            }

            ballX = ballX + (DX * directionX) ;
            ballY = ballY + (DY * directionY);
            ball.x = ballX ;
            ball.y = ballY;
            try {
                Thread.sleep(150L);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
