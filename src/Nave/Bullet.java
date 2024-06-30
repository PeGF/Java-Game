package Nave;

public class Bullet {
    public static final int INACTIVE = 0;
    public static final int ACTIVE = 1;

    protected int state;
    private double x;
    private double y;
    private double vx;
    private double vy;
    private double angle;
    private final double radius;


    public Bullet(double x, double y, double vx, double vy, double radius) {
        this.state = INACTIVE;
        this.x = x;
        this.y = y;
        this.vx = vx;
        this.vy = vy;
        this.radius = radius;
    }

    public double getX() {
        return x;
    }
    public double getY() {
        return y;
    }
    public double getRadius() {
        return radius;
    }
    public int getState() {
        return state;
    }

    public void updatePosition(long delta) {
        // Atualiza a posição da bala com base na velocidade e no tempo passado (delta)
        x += vx * delta;
        y += vy * delta;
    }

    /*
    public void update(int height, int width) {
        if (state == ACTIVE) {
            x += vx;
            y += vy;
            if (y < 0 || y > height || x < 0 || x > width) {
                state = INACTIVE; // Desativa o projétil se ele sair da tela
            }
        }
    }
     */

}