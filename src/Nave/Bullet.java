package Nave;

public class Bullet {
    public static final int INACTIVE = 0;
    public static final int ACTIVE = 1;

    protected int state;
    public static int max_bullets;
    protected double x;
    protected double y;
    private final double vx;
    private final double vy;
    private final double radius;


    public Bullet(double x, double y, double vx, double vy, double radius) {
        this.state = ACTIVE;
        this.x = x;
        this.y = y;
        this.vx = vx;
        this.vy = vy;
        this.radius = radius;
    }

    public double getX() { return x; }
    public double getY() { return y; }
    public double getRadius() { return radius; }
    public int getState() { return state; }

    public void update(int height, int width) {
        if (state == ACTIVE) {
            x += vx;
            y += vy;
            if (y < 0 || y > height || x < 0 || x > width) {
                state = INACTIVE; // Desativa o projétil se ele sair da tela
            }
        }
    }
}