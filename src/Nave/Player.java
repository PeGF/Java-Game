package Nave;
import java.util.ArrayList;
import java.util.List;

public class Player {
    public static final int INACTIVE = 0;
    public static final int ACTIVE = 1;
    public static final int EXPLODING = 2;

    private int state;
    private double x;
    private double y;
    private double vx;
    private double vy;
    private double radius;
    private double comeco_explosao;   //instante do inicio da explosão
    private double final_explosao;      //instante do fim da explosão
    private long proximo_tiro;      //instante a partir do qual pode haver um próximo tiro
    private final ArrayList<Bullet> bullets;

    public Player(double x, double y){
        this.state = ACTIVE;

        this.x = x / 2;
        this.y = y * 0.90;
        vx = 0.25;
        vy = 0.25;
        radius = 12.0;

        this.comeco_explosao = 0;
        this.final_explosao = 0;
        this.proximo_tiro = System.currentTimeMillis();
        this.bullets = new ArrayList<>();

    }
    public int getState(){ return state; }
    public void setState (int state) { this.state = state;}

    public double getX () {return x;}
    public void setX(double x) {this.x = x;}

    public double getY () {return y;}
    public void setY(double y) {this.y = y;}

    public double getVx() { return vx; }
    public void setVx(double vx) { this.vx = vx; }

    public double getVy() { return vy; }
    public void setVy(double vy) { this.vy = vy; }

    public double getRadius() { return radius; }
    public void setRadius(double radius) { this.radius = radius; }

    public double getExplosionStart() { return comeco_explosao; }
    public void setExplosionStart(double explosionStart) { this.comeco_explosao = explosionStart; }

    public double getExplosionEnd() { return final_explosao; }
    public void setExplosionEnd(double explosionEnd) { this.final_explosao = explosionEnd; }

    public long getNextShot() { return proximo_tiro; }
    public void setNextShot(long nextShot) { this.proximo_tiro = nextShot; }

    public List<Bullet> getBullets() {return bullets;}

    //ações

    public void move(){
        this.x += vx;
        this.y += vy;
    }

    public void explode(){
        this.state = EXPLODING;
        this.comeco_explosao = System.currentTimeMillis();
        this.final_explosao = this.final_explosao + 1000; //1 segundo de recuperação
    }

    public boolean canShoot(){
        return System.currentTimeMillis() >= proximo_tiro;
    }

    public void shoot(){
        if(canShoot()){
            bullets.add(new Bullet(x, y, 0, 1, 5)); //projetil nao se move no eixo x
            proximo_tiro = System.currentTimeMillis() + 500; //0.5s de intervalo
        }
    }

    public void update(int height, int width){
        move();

        bullets.forEach(bullet -> bullet.update(height, width));
        bullets.removeIf(bullet -> bullet.getState() == INACTIVE);
    }
}