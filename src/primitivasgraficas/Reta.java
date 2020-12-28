package primitivasgraficas;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.paint.Color;

/**
 *
 * @author g4l1l3u
 */
public class Reta {
    
    private final List<Point> coordenadas = new ArrayList<>();

    public List<Point> getCoordenadas() {
        return coordenadas;
    }
    
    public Color getColor()
    {
        return Color.RED;
    }
    
    private void writePixel(int x, int y)
    {
        coordenadas.add(new Point(x, y));
    }
    
    public void eqRealReta(Point p1, Point p2)
    {
        int x1 = p1.x, y1 = p1.y;
        int x2 = p2.x, y2 = p2.y;
        double dx = x2 - x1;
        double dy = y2 - y1;
        double m = dy/dx;
        
        //1ªManeira apenas 2 condições! 
        if(Math.abs(dx) >= Math.abs(dy))
        {
            double y;
            int inc = dx > 0 ? 1 : -1;
            for(int x = x1; x != x2; x+=inc)
            {
                y =(double)(y1 + m*(x - x1));
                writePixel(x, (int)Math.round(y));
            }
            
        }
        else
        {
            double x;
            int inc = dy > 0 ? 1 : -1;
            for(int y = y1; y != y2; y+=inc)
            {
                x = x1 + (y-y1)/m;
                writePixel((int)Math.round(x), y);
            }
        }
        
        //2ª Maneira diversas condições. Funciona tambem :)
        //1ºQuadrante
        /*if(x2 > x1 && y2 > y1)
        {
            if(dx > dy)//1ºOctante
            {
                double y;
                for(int x = x1; x <= x2; x++)
                {
                    y =(double)(y1 + m*(x - x1));
                    writePixel(x, (int)Math.round(y));
                }
            }
            else//2ºOctante
            {
                double x;
                for(int y = y1; y <= y2; y++)
                {
                    x = x1 + (y-y1)/m;
                    writePixel((int)Math.round(x), y);
                }
            }
        }
        else
        {
            if(x1 > x2 && y2 > y1) //2ºQuadrante
            {
                if(dx < dy)//3ºOctante
                {
                    double x;
                    for(int y = y1; y <= y2; y++)
                    {
                        x = x1 - (y-y1)/m;
                        writePixel((int)Math.round(x), y);
                    }
                }
                else //4ºOctante
                {
                    double y;
                    for(int x = x1; x >= x2; x--)
                    {
                        y = (double)(y1 - m*(x - x1));
                        writePixel(x, (int)Math.round(y));
                    }
                }
            }
            else
            {
                if(x1 > x2 && y1 > y2)//3ºQuadrante
                {
                    if(dx > dy)//5ºOctante
                    {
                        double y;
                        for(int x = x1; x >= x2; x--)
                        {
                            y = (double)(y1 + m*(x - x1));
                            writePixel(x, (int)Math.round(y));
                        }
                    }
                    else //6ºOctante
                    {
                        double x;
                        for(int y = y1; y >= y2; y--)
                        {
                            x = x1 + (y-y1)/m;
                            writePixel((int)Math.round(x), y);
                        }
                    }
                }
                else
                {
                    if(x2 > x1 && y1 > y2)//4ºQuadrante
                    {
                        if(dx < dy)//7ºOctante
                        {
                            double x;
                            for(int y = y1; y >= y2; y--)
                            {
                                x = x1 - (y-y1)/m;
                                writePixel((int)Math.round(x), y);
                            }
                        }
                        else//8ºOctante
                        {
                            double y;
                            for(int x = x1; x <= x2; x++)
                            {
                                y = (double)(y1 - m*(x - x1));
                                writePixel(x, (int)Math.round(y));
                            }
                        }
                    }
                    else
                    {
                        if(x1 == x2)
                        {
                            for(int y = y1; y >= y2; y--)
                                writePixel(x1, y);
                            for(int y = y1; y <= y2; y++)
                                writePixel(x1, y);
                        }
                        else
                        {
                            if(y1 == y2)
                            {
                                for(int x = x1; x >= x2; x--)
                                    writePixel(x, y1);
                                for(int x = x1; x <= x2; x++)
                                    writePixel(x, y1);
                            }
                        }
                    }
                }
            }
        }*/
    
    }
    
    public void DDA(Point p1, Point p2)
    {
        int x1 = p1.x, y1 = p1.y;
        int x2 = p2.x, y2 = p2.y;
        int length, cont;
        double x, y, xinc, yinc;
        int dx = x2 - x1;
        int dy = y2 - y1;
        
        length = Math.max(Math.abs(dx), Math.abs(dy));
        
        if(x1 == x2)
        {
            for(y = y1; y >= y2; y--)
                writePixel(x1, (int)y);
            for(y = y1; y <= y2; y++)
                writePixel(x1, (int)y);
        }
        else
        {
            xinc = (double)dx/length;
            yinc = (double)dy/length;
            x = x1;
            y = y1;
            cont = 0;
            while(cont < length)
            {
                writePixel((int)Math.round(x), (int)Math.round(y));
                x += xinc;
                y += yinc;
                cont++;
            }
            
        }
    }
    
    public void bresenham(Point p1, Point p2)
    {
        int x1 = p1.x, y1 = p1.y;
        int x2 = p2.x, y2 = p2.y;
        
        int d, x, y, incE, incNE, declive;
        int dx = x2 - x1;
        int dy = y2 - y1;
        
        if (Math.abs(dx) >= Math.abs(dy)) 
        {
            if (x2 < x1) 
            {
                bresenham(p2, p1);
            } 
            else 
            {
                if (dy <= 0) 
                {
                    declive = -1;
                    dy = -dy;
                } 
                else 
                {
                    declive = 1;
                }
                // Constante de Bresenham
                incE = 2 * dy;
                incNE = 2 * dy - 2 * dx;
                d = 2 * dy - dx;
                y = y1;
                for (x = x1; x <= x2; x++) 
                {
                    writePixel(x, y);
                    if (d <= 0) 
                    {
                        d += incE;
                    } 
                    else 
                    {
                        d += incNE;
                        y += declive;
                    }
                }
            }
        }
        else
        {
            if (y2 < y1) 
            {
                bresenham(p2, p1);
            } 
            else 
            {
                if (dx < 0) 
                {
                    declive = -1;
                    dx = -dx;
                } 
                else 
                {
                    declive = 1;
                }
                // Constante de Bresenham
                incE = 2 * dx;
                incNE = 2 * dx - 2 * dy;
                d = 2 * dx - dy;
                x = x1;
                for (y = y1; y <= y2; y++) 
                {
                    writePixel(x, y);
                    if (d <= 0) 
                    {
                        d += incE;
                    } 
                    else 
                    {
                        d += incNE;
                        x += declive;
                    }
                }
            }
        }
        
    }

    
}
