package Nave;
import java.awt.Color;

public class Enemy3 extends BaseEnemy{

    private Color color;
    private double waveAmplitude;
    private double waveFrequency;

    public Enemy3(int state, double x, double y, double v, double waveAmplitude, double waveFrequency) {
        super(state, x, y, v);
        this.waveAmplitude = waveAmplitude;
        this.waveFrequency = waveFrequency;
        this.color = Color.GREEN;
    }

    public Color getColor() {
        return color;
    }

    public double getWaveAmplitude() {
        return waveAmplitude;
    }

    public double getWaveFrequency() {
        return waveFrequency;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public void setWaveAmplitude(double waveAmplitude) {
        this.waveAmplitude = waveAmplitude;
    }

    public void setWaveFrequency(double waveFrequency) {
        this.waveFrequency = waveFrequency;
    }

    @Override
    public void updatePosition(long delta) {
        if (state == ACTIVE) {
            double time = delta / 1000.0; // convert delta to seconds
            y += v * time;
            x += waveAmplitude * Math.sin(waveFrequency * time);
        }
    }
}
