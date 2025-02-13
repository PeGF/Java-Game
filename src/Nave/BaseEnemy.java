package Nave;

public abstract class BaseEnemy {
    public static final int INACTIVE = 0;
    public static final int ACTIVE = 1;
    public static final int EXPLODING = 2;

    int state;
    protected double x;
    protected double y;
    protected double v;
    protected double bullet_vx;
    protected double bullet_vy;
    protected double angle;
    protected double explosion_start;
    protected double explosion_end;
    protected static long next_enemy;
    protected double radius;
    protected BulletsManager bulletsManager;

    public BaseEnemy(int state, double x, double y, double v) {
        this.state = state;
        this.x = x;
        this.y = y;
        this.v = v;
        angle = (3 * Math.PI) / 2;
    }

    public double getRadius() {
        return radius;
    }

    public long getNext_enemy() {
        return next_enemy;
    }

    public double getExplosion_end() {
        return explosion_end;
    }

    public double getExplosion_start() {
        return explosion_start;
    }

    public double getAngle() {
        return angle;
    }

    public double getV() {
        return v;
    }

    public double getY() {
        return y;
    }

    public double getX() {
        return x;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }

    public void setNext_enemy(long next_enemy) {
        this.next_enemy = next_enemy;
    }

    public void setExplosion_end(double explosion_end) {
        this.explosion_end = explosion_end;
    }

    public void setExplosion_start(double explosion_start) {
        this.explosion_start = explosion_start;
    }

    public void setAngle(double angle) {
        this.angle = angle;
    }

    public void setV(double v) {
        this.v = v;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getBullet_vx() {
        return bullet_vx;
    }

    public void setBullet_vx(double bullet_vx) {
        this.bullet_vx = bullet_vx;
    }

    public double getBullet_vy() {
        return bullet_vy;
    }

    public void setBullet_vy(double bullet_vy) {
        this.bullet_vy = bullet_vy;
    }

    public BulletsManager getBulletsManager() {
        return bulletsManager;
    }

    public void setBulletsManager(BulletsManager bulletsManager) {
        this.bulletsManager = bulletsManager;
    }

    public void kill(double otherX, double otherY, double otherRadius) {
        double dx = otherX - x;
        double dy = otherY - y;
        double dist = Math.sqrt(dx * dx + dy * dy);
        if(dist < (radius + otherRadius) * 0.8){
           state = EXPLODING;
            explosion_start = System.currentTimeMillis();
            explosion_end = explosion_start + 500;
        }
    }

    public void activate(double startX, double startY, double startV, double startAngle, long currentTime) {
        this.state = ACTIVE;
        this.x = startX;
        this.y = startY;
        this.angle = startAngle;
    }

    public abstract void updatePosition(long delta);

}
