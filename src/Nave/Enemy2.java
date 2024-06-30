package Nave;

public class Enemy2 extends BaseEnemy {
    private double spawnX;
    private boolean shootNow;
    public double RV;
    private static int count = 0;

    public Enemy2(int state, double x, double y, double v, double angle, int width, double RV, long currentTime) {
        super(state, x, y, v);
        spawnX = width * 0.20;
        radius = 12.0;
        this.RV = RV;
        next_enemy = currentTime + 7000;
        count++;
    }

    @Override
    public void updatePosition(long delta) {
        x += v * Math.cos(angle) * delta;
        y += v * Math.sin(angle) * delta * (-1.0);
        angle += RV * delta;
    }
}
