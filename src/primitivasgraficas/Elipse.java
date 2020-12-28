/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
public class Elipse {
    
    private final List<Point> coordenadas = new ArrayList<>();

    public List<Point> getCoordenadas() {
        return coordenadas;
    }
    
    public Color getColor()
    {
        return Color.BLUE;
    }
    
    private void setCoord(int x, int y)
    {
        coordenadas.add(new Point(x, y));
    }
    
    public void elipsePontoMedio(Point p, int dx, int dy, GraphicsContext gc)
    {
        int cx = p.x, cy = p.y;
        int x = 0, y = dy;
        double d1 = dy * dx - dx * dx * dy + dx * dx / 4;
        double d2;
        pontosElipse(cx, cy, x, y, gc);
        while (dx * dx * (y - 0.5) > dy * dy * (x + 1))
        {
            
            if (d1 < 0)
            {
                d1 += dy * dy * (2 * x + 3);
                ++x;
            }
            else
            {
                d1 = d1 + dy * dy * (2 * x + 3) + dx * dx * (-2 * y + 2);
                x++;
                y--;
            }
            pontosElipse(cx, cy, x, y, gc);
        }

        d2 = dy * dy * (x + 0.5) * (x + 0.5) + dx * dx * (y - 1) * (y - 1) - dx * dx * dy * dy;
        while (y > 0)
        {
            
            if (d2 < 0)
            {
                d2 = d2 + dy * dy * (2 * x + 2) + dx * dx * (-2 * y + 3);
                x++;
                y--;
            }
            else
            {
                d2 = d2 + dx * dx * (-2 * y + 3);
                y--;
            }
            pontosElipse(cx, cy, x, y, gc);
        }
    }
    
    
    private void pontosElipse(int cx, int cy, int x, int y, GraphicsContext gc)
    {
        if(tamanho(cx + x, cy - y, gc))
            setCoord(cx + x, cy - y);
        if(tamanho(cx - x, cy - y, gc))
            setCoord(cx - x, cy - y);
        if(tamanho(cx - x, cy + y, gc))
            setCoord(cx - x, cy + y);
        if(tamanho(cx + x, cy + y, gc))
            setCoord(cx + x, cy + y);
    }
    
    private boolean tamanho(int x, int y, GraphicsContext gc)
    {
        return x >=0 && x < gc.getCanvas().getWidth() && y>=0 && y < gc.getCanvas().getHeight();
    }
}
