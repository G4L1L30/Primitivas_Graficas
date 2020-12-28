package primitivasgraficas;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 *
 * @author g4l1l3u
 */

public class Circunferencia {
    
    private final List<Point> coordenadas = new ArrayList<>();

    public List<Point> getCoordenadas() {
        return coordenadas;
    }
    
    public Color getColor()
    {
        return Color.DARKORCHID;
    }
    
    private void setCoord(int x, int y)
    {
        coordenadas.add(new Point(x, y));
    }
    
    public void eqGeralCircunferencia(Point p, double raio, GraphicsContext gc)
    {
        int cx = p.x, cy = p.y;
        double y;
        for(double x = 0; x <= raio/Math.sqrt(2); x++)
        {
            y = Math.sqrt(Math.pow(raio, 2)-Math.pow(x, 2));
            pontosCircunferencia(cx, cy, (int)x, (int)y, gc);
        }
    }
    
    public void eqTrigonometrica(Point p, double raio, GraphicsContext gc)
    {
        int cx = p.x, cy = p.y;
        double y, x;
        double perimetro = 2.0 * Math.PI * raio;
        double inc = 360.0 / perimetro;
        for(double ang = 45; ang <= 90; ang+=inc)
        {
            x = raio*Math.cos(ang * Math.PI / 180.0);
            y = raio*Math.sin(ang * Math.PI / 180.0);
            pontosCircunferencia(cx, cy, (int)x, (int)y, gc);
        }
    }
    
    public void pontoMedio(Point p, double raio, GraphicsContext gc)
    {
        int cx = p.x, cy = p.y;
        int x = 0, y = (int)raio;
        double d = 1 - raio;
        pontosCircunferencia(cx, cy, x, y, gc);
        while(y > x)
        {
            if(d < 0)
                d += 2 * x + 3;
            else
            {
                d +=2 * (x - y) + 5;
                y--;
            }
            x++;
            pontosCircunferencia(cx, cy, x, y, gc);
        }
    }
    
    private void pontosCircunferencia(int cx, int cy, int x, int y, GraphicsContext gc)
    {
        if(tamanho(cx + y, cy - x, gc))
            setCoord(cx + y, cy - x);
        if(tamanho(cx + x, cy - y, gc))
            setCoord(cx + x, cy - y);
        if(tamanho(cx - x, cy - y, gc))
            setCoord(cx - x, cy - y);
        if(tamanho(cx - y, cy - x, gc))
            setCoord(cx - y, cy - x);
        if(tamanho(cx - y, cy + x, gc))
            setCoord(cx - y, cy + x);
        if(tamanho(cx - x, cy + y, gc))
            setCoord(cx - x, cy + y);
        if(tamanho(cx + x, cy + y, gc))
            setCoord(cx + x, cy + y);
        if(tamanho(cx + y, cy + x, gc))
            setCoord(cx + y, cy + x);
    }
    
    private boolean tamanho(int x, int y, GraphicsContext gc)
    {
        return x >=0 && x < gc.getCanvas().getWidth() && y>=0 && y < gc.getCanvas().getHeight();
    }
    
}
