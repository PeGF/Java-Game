package Nave;

public class Enemy2 extends BaseEnemy {
    private boolean shootNow;
    private double RV;
    private static int count = 0;
    private double spawnX;
    private BulletsManager bulletsManager;

    public Enemy2(int state, double x, double y, double v, double RV, long currentTime, BulletsManager bulletsManager) {
        super(state, x, y, v);
        radius = 12.0;
        this.RV = RV;
        next_enemy = currentTime + 7000;
        count++;
        shootNow = false;
        this.bulletsManager = bulletsManager;
    }

    @Override
    public void updatePosition(long delta) {
        x += v * Math.cos(angle) * delta;
        y += v * Math.sin(angle) * delta * (-1.0);
        angle += RV * delta;
        if (shootNow && bulletsManager.canShoot()) {
            shoot();
            shootNow = false; // Reseta o estado de disparo
        }
    }

    public void shoot(){
        double bulletV = 300; // Velocidade do projétil
        Bullet bullet = new Bullet(x, y, bulletV, angle, 2.0);
        bulletsManager.addBullet(bullet);
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
