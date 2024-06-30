package Nave;

public class Enemy2 extends BaseEnemy {
    private boolean shootNow;
    private double RV;
    private static int count = 0;
    private double spawnX;

    public Enemy2(int state, double x, double y, double v, double RV, long currentTime) {
        super(state, x, y, v);
        radius = 12.0;
        this.RV = RV;
        next_enemy = currentTime + 7000;
        count++;
        shootNow = false;
    }

    @Override
    public void updatePosition(long delta) {
        x += v * Math.cos(angle) * delta;
        y += v * Math.sin(angle) * delta * (-1.0);
        angle += RV * delta;
    }

    public static int getCount() {
        return count;
    }

    public static void setCount(int count) {
        Enemy2.count = count;
    }

    public double getRV() {
        return RV;
    }

    public void setRV(double RV) {
        this.RV = RV;
    }

    public boolean isShootNow() {
        return shootNow;
    }

    public void setShootNow(boolean shootNow) {
        this.shootNow = shootNow;
    }

    public double getSpawnX() {
        return spawnX;
    }

    public void setSpawnX(double spawnX) {
        this.spawnX = spawnX;
    }
}
