package Nave;

public class Enemy1 extends BaseEnemy{
    private long next_shoot;
    private double bullet_radius;
    private BulletsManager bulletsManager;

    public Enemy1(int state, double x, double y, double v, long currentTime, BulletsManager bulletsManager) {
        super(state, x, y, v);
        radius = 9.0;
        bullet_radius = 2.0;
        next_shoot =  0;
        next_enemy = currentTime + 2000;
        bullet_vx = Math.cos(getAngle()) * 0.45;
        bullet_vy = Math.sin(getAngle()) * 0.45 * (-1);
        this.bulletsManager = bulletsManager;
    }

    @Override
    public void updatePosition(long delta) {
        x += v * Math.cos(angle) * delta ;
        y += v * Math.sin(angle) * delta * (-1.0) ;
    }

    public void shoot(double angle){
        bulletsManager.addBullet(new Bullet(x, y, bullet_vx, bullet_vy, bullet_radius));
    }


    public long getNext_shoot() {
        return next_shoot;
    }

    public void setNext_shoot(long next_shoot) {
        this.next_shoot = next_shoot;
    }

    public double getBullet_radius() {
        return bullet_radius;
    }

    public void setBullet_radius(double bullet_radius) {
        this.bullet_radius = bullet_radius;
    }

    @Override
    public BulletsManager getBulletsManager() {
        return bulletsManager;
    }

    @Override
    public void setBulletsManager(BulletsManager bulletsManager) {
        this.bulletsManager = bulletsManager;
    }
}
