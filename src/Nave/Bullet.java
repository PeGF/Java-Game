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
        this.state = ACTIVE;
        this.x = x;
        this.y = y;
        this.vx = vx * 0.5; //Por algum motivo, precisamo resuzir a velocidade
        this.vy = vy * 0.5; //das balas para ficar igual a antes ¯\_(ツ)_/¯
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

    public void setAngle(double angle) {
        this.angle = angle;
    }

    public void setVy(double vy) {
        this.vy = vy;
    }

    public void setVx(double vx) {
        this.vx = vx;
    }

    public void setY(double y) {
        this.y = y;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setState(int state) {
        this.state = state;
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