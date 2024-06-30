package Nave;

public class Enemy2 extends BaseEnemy {
    private boolean shootNow;
    private double RV;
    private static int count = 0;
    private double spawnX;
    private double bullet_radius;

    public Enemy2(int state, double x, double y, double v, double RV, long currentTime, BulletsManager bulletsManager) {
        super(state, x, y, v);
        radius = 12.0;
        this.RV = RV;
        next_enemy = currentTime + 7000;
        count++;
        shootNow = false;
        this.bulletsManager = bulletsManager;
        bullet_radius = 2.0;
    }

    @Override
    public void updatePosition(long delta) {
        x += v * Math.cos(angle) * delta;
        y += v * Math.sin(angle) * delta * (-1.0);
        angle += RV * delta;
    }

    public void shoot(double shoot_angles){
        double a = shoot_angles + Math.random() * Math.PI/6 - Math.PI/12;
        double vx = Math.cos(a);
        double vy = Math.sin(a);
        bulletsManager.addBullet(new Bullet(x, y, vx, vy, bullet_radius));
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

    public double getBullet_radius() {
        return bullet_radius;
    }

    public void setBullet_radius(double bullet_radius) {
        this.bullet_radius = bullet_radius;
    }
}
