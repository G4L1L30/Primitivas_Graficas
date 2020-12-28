package primitivasgraficas;

import java.awt.Point;
import java.util.Stack;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.canvas.GraphicsContext;
import java.awt.Color;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.WritableImage;

/**
 *
 * @author g4l1l3u
 */
public class Poligno {
    
    private List<Point> pontosOriginais;
    private List<Point> pontosAtuais;
    private double[][] MA;
    private boolean fechou;
    private int qual;
    private int[][] r, g, b;

    public Poligno() {
        pontosOriginais = new ArrayList<>();
        pontosAtuais = new ArrayList<>();
        MA = matrizIdentidade();
        fechou = false;
    }
    
    private double[][] matrizIdentidade()
    {
        double[][] mat = new double[3][3];
        mat[0][0] = 1;
        mat[1][1] = 1;
        mat[2][2] = 1;
        return mat;
    }
    
    public void addPonto(int x, int y)
    {
        if(pontosAtuais.size() > 0)
        {
            if(pontosAtuais.get(pontosAtuais.size()-1).getX() != x && pontosAtuais.get(pontosAtuais.size()-1).getY() != y)
            {
                pontosOriginais.add(new Point(x, y));
                pontosAtuais.add(new Point(x, y));
            }
        }
        else{
            pontosOriginais.add(new Point(x, y));
            pontosAtuais.add(new Point(x, y));
        }
        
    }
    
    private void atualizaPontos()
    {
        for(int i = 0; i < pontosOriginais.size(); i++)
        {
            double[][] ponto = new double[3][1];
            ponto[0][0] = pontosOriginais.get(i).getX();
            ponto[1][0] = pontosOriginais.get(i).getY();
            ponto[2][0] = 1;
            ponto = multiplicaPontos(ponto);
            pontosAtuais.set(i, new Point((int)(ponto[0][0]), (int)(ponto[1][0])));
        }
    }
    
    public void setPontos(List<Point> pontos)
    {
        pontosOriginais = pontos;
        pontosAtuais = pontos;
    }

    public List<Point> getPontosOriginais() {
        return pontosOriginais;
    }
    
    
    
    /*public void exibe()
    {
        for (int i = 0; i < pontosAtuais.size(); i++) {
            System.out.println("X:" + pontosAtuais.get(i).getX() + " Y:" + pontosAtuais.get(i).getY());
        }
    }*/
    
    public int size()
    {
        return pontosAtuais.size();
    }
    
    public double getCx(int pos)
    {
        return pontosAtuais.get(pos).getX();
    }
    
    public double getCY(int pos)
    {
        return pontosAtuais.get(pos).getY();
    }
    
    public void setarqual(int conta)
    {
        qual = conta;
    }
    
    public Point fechaPoligno(double x, double y)
    {
        Point aux;
        if (Math.abs(x - pontosOriginais.get(0).getX()) <= 5 && Math.abs(y - pontosOriginais.get(0).getY()) <= 5) 
        {
            aux = new Point((int)pontosOriginais.get(0).getX(), (int)pontosOriginais.get(0).getY()); //encontrou P0
            //Atualiza a lista de pontos
            pontosAtuais.set(pontosAtuais.size()-1, aux);
            pontosOriginais.set(pontosOriginais.size()-1, aux);
            fechou = true;
        }
        else
            aux = new Point((int)x, (int)y);
        return aux;
    }
    
    public boolean polignoOk()
    {
        return fechou;
    }
    
    private double[][] copiaMatriz(double[][] ma, int l, int c)
    {
        double[][] aux = new double[l][c];
        for(int i = 0; i < l; i++)
            for(int j = 0; j < c; j++)
                aux[i][j] = ma[i][j];
        return aux;
    }
    
    public Point centroPoligno()
    {
        double x = 0.0, y = 0.0;
        
        for(int i = 0; i < pontosAtuais.size(); i++)
        {
            x += pontosAtuais.get(i).getX();
            y += pontosAtuais.get(i).getY();
        }
        x /= pontosAtuais.size();
        y /= pontosAtuais.size();
        return new Point((int)x, (int)y);
    }
    
    private void multiplicaMatriz(double[][] mat)
    {
        double soma;
        double[][] mataux = copiaMatriz(MA, 3, 3);
        for(int l = 0; l < 3; l++)
        {
            for(int c = 0; c < 3; c++)
            {
                soma = 0;
                for(int i = 0; i < 3; i++)
                    soma +=  mat[l][i] * MA[i][c];
                mataux[l][c] = soma;
            }
        }
        MA = mataux;    
    }
    
    private double[][] multiplicaPontos(double[][] mat)
    {
        double soma;
        double[][] mataux = copiaMatriz(mat, 3, 1);
        for(int l = 0; l < 3; l++)
        {
            for(int c = 0; c < 1; c++)
            {
                soma = 0;
                for(int i = 0; i < 3; i++)
                    soma += MA[l][i] * mat[i][c];
                mataux[l][c] = soma;
            }
        }
        return mataux;
    }
    
    private void atualizaPontos(double [][]mat)
    {
        for(int i = 0; i < pontosOriginais.size(); i++)
        {
            double[][] ponto = new double[3][1];
            ponto[0][0] = pontosOriginais.get(i).getX();
            ponto[1][0] = pontosOriginais.get(i).getY();
            ponto[2][0] = 1;
            ponto = multiplicaPontos(ponto);
            pontosAtuais.set(i, new Point((int)(ponto[0][0]), (int)(ponto[1][0])));
        }
    }
    
    //OK
    public void translacao(double x, double y)
    {
        double[][] matTrans = matrizIdentidade();
        matTrans[0][2] = x;
        matTrans[1][2] = y;
        multiplicaMatriz(matTrans);
        atualizaPontos();
    }
    
    //OK
    public void escala(double x, double y, int flag)
    {
        Point centro = null;
        if(flag == 1)
            centro = centroPoligno();
        double[][] matEscala = matrizIdentidade();
        matEscala[0][0] = x;
        matEscala[1][1] = y;
        if(flag == 1)
            translacao(-centro.getX(), -centro.getY());
        multiplicaMatriz(matEscala);
        if(flag == 1)
            translacao(centro.getX(), centro.getY());
        atualizaPontos();
    }
    
    
    
    //OK
    public void rotacao(double angulo, double x, double y, int flag)
    {
        double rad = angulo * Math.PI / 180.0;
        double[][] matRot = matrizIdentidade();
        matRot[0][0] = Math.cos(rad);
        matRot[0][1] = -Math.sin(rad);
        matRot[1][0] = Math.sin(rad);
        matRot[1][1] = Math.cos(rad);
        if(flag == 1)
            translacao(-x, -y);
        multiplicaMatriz(matRot);
        if(flag == 1)
            translacao(x, y);
        atualizaPontos();
    }
    
    //OK
    public void espelhoX(Point centro)
    {
        double[][] matEspX = matrizIdentidade();
        matEspX[1][1] = -1;
        translacao(-centro.getX(), -centro.getY());
        multiplicaMatriz(matEspX);
        translacao(centro.getX(), centro.getY());
        atualizaPontos();
    }
    
    //OK
    public void espelhoY(Point centro)
    {
        double[][] matEspY = matrizIdentidade();
        matEspY[0][0] = -1;
        translacao(-centro.getX(), -centro.getY());
        multiplicaMatriz(matEspY);
        translacao(centro.getX(), centro.getY());
        atualizaPontos();
    }
    
    //OK
    public void cisalhamentoX(double x, Point centro)
    {
        double[][] matCisX = matrizIdentidade();
        matCisX[0][1] = x;
        translacao(-centro.getX(), -centro.getY());
        multiplicaMatriz(matCisX);
        translacao(centro.getX(), centro.getY());
        atualizaPontos();
    }
    
    //OK
    public void cisalhamentoY(double x, Point centro)
    {
        double[][] matCisY = matrizIdentidade();
        matCisY[1][0] = x;
        translacao(-centro.getX(), -centro.getY());
        multiplicaMatriz(matCisY);
        translacao(centro.getX(), centro.getY());
        atualizaPontos();
    }
    
    private void writePixel(int x, int y, GraphicsContext gc, Color cor)
    {
        gc.fillRect(x, y, 1, 1);
        gc.setFill(awtColorToJavaFX(cor));
    }
   
    
    private Color cc(int x, int y)
    {
        
        return  new Color(r[x][y], g[x][y], b[x][y]);
    }
    
    private javafx.scene.paint.Color awtColorToJavaFX(Color c) {
        return javafx.scene.paint.Color.rgb(c.getRed(), c.getGreen(), c.getBlue(), c.getAlpha() / 255.0);
    }
    
    //2 formas de se fazer
    public void floodFill(GraphicsContext gc, int x, int y)
    {
        WritableImage wi = new WritableImage((int)gc.getCanvas().getWidth(), (int)gc.getCanvas().getHeight());
        BufferedImage bi = SwingFXUtils.fromFXImage(gc.getCanvas().snapshot(null, wi), null); 
        
        int width = (int)gc.getCanvas().getWidth();
        int heigth = (int)gc.getCanvas().getHeight();
        
        Color rgb = null;
        
        
        r = new int[width][heigth];
        g = new int[width][heigth];
        b = new int[width][heigth];
        
        int i, j;
        for(i = 0; i < width; i++)
        {
            for(j = 0; j < heigth; j++)
            {
                rgb = new Color(bi.getRGB(i, j));
                r[i][j] = rgb.getRed();
                g[i][j] = rgb.getGreen();
                b[i][j] = rgb.getBlue();
            }
        }
        
        Color cor  = new Color(r[x][y], g[x][y], b[x][y]) , atual;
        
        Stack<Point> pilha = new Stack<Point>();
        Point p = new Point(x ,y);
        pilha.push(p);
        
        while(pilha.size() > 0)
        {
            p = pilha.pop();
            x = (int)p.getX();
            y = (int)p.getY();
            
            if(x < width && x > 0 && y < heigth && y>0)
            {
                writePixel(x, y, gc, Color.YELLOW);
                
                
                r[x][y] = Color.YELLOW.getRed();
                g[x][y] =  Color.YELLOW.getGreen();
                b[x][y] =  Color.YELLOW.getBlue();
                
                atual = cc(x-1, y);
                if(atual.equals(cor))
                    pilha.push(new Point(x -1, y));
                
                atual = cc(x, y-1);
                if(atual.equals(cor))
                    pilha.push(new Point(x, y - 1));
                
                atual = cc(x+1, y);
                if(atual.equals(cor))
                    pilha.push(new Point(x + 1, y));
                
                atual = cc(x, y+1);
                if(atual.equals(cor))
                    pilha.push(new Point(x, y + 1));
                
            }
        }   
        
        
        
        //Prova que funciona para testar basta comentar a parte a baixo deste codigo e rodar somente este trecho
        //o que este codigo prova, que eu consigo pegar cada pixel, entao ele substitui todos os pixel branco por verde
        //e o poligno que esta em vermelho, sera redesenhado para azul!
        /*for(int i = 0; i < width; i++)
        {
            for(int j = 0; j < heigth; j++)
            {
                rgb = new Color(bi.getRGB(i, j));
                if(rgb.equals(Color.WHITE))
                    rgb = Color.GREEN;
                if(rgb.equals(Color.RED))
                    rgb = Color.BLUE;
                writePixel(i, j, gc, rgb);
            }
        }*/
        
        
        //Segunda forma porem muita lenta, mas funcional;
        
        /*Color cor = new Color(bi.getRGB(x, y));
        Color atual;
        
        
        Stack<Point> pilha = new Stack<Point>();
        Point p = new Point(x ,y);
        pilha.push(p);
        
        while(pilha.size() > 0)
        {
            p = pilha.pop();
            x = (int)p.getX();
            y = (int)p.getY();
            
            if(x < width && x > 0 && y < heigth && y>0)
            {
                writePixel(x, y, gc, Color.YELLOW);
                
                //!atual.equals(Color.RED) && !atual.equals(Color.YELLOW)
                
                atual = new Color(bi.getRGB(x-1, y));
                if(atual.equals(cor))
                    pilha.push(new Point(x -1, y));
                atual = new Color(bi.getRGB(x, y-1));
               if(atual.equals(cor))
                    pilha.push(new Point(x, y - 1));
                atual = new Color(bi.getRGB(x+1, y));
                if(atual.equals(cor))
                    pilha.push(new Point(x + 1, y));
                atual = new Color(bi.getRGB(x, y+1));
                if(atual.equals(cor))
                    pilha.push(new Point(x, y + 1));
                wi = new WritableImage((int)gc.getCanvas().getWidth(), (int)gc.getCanvas().getHeight());
                bi = SwingFXUtils.fromFXImage(gc.getCanvas().snapshot(null, wi), null); 
            }
            
        }*/
    }
    
    public int Ymin()
    {
        List<Point> lista = pontosOriginais;
        int maior = (int)lista.get(0).getY();
        for(int i = 0; i< lista.size(); i++)
        {
            if(maior < (int)lista.get(i).getY())
                maior = (int)lista.get(i).getY();
        }
        return maior;
    }
    
    public void ET(GraphicsContext gc)
    {
        ET et = new ET(Ymin());
        
        List<Point> lista = pontosOriginais;
        
        double Xmax, Ymax, Xmin, Ymin;
        double IncX;
        
        //Constroi ET
        for(int i = 0; i < lista.size()-1; i++)
        {
            if(lista.get(i).getY() > lista.get(i+1).getY())
            {
                Xmax = lista.get(i).getX();
                Ymax = lista.get(i).getY();
                Xmin = lista.get(i+1).getX();
                Ymin = lista.get(i+1).getY();
            }
            else
            {
                Xmax = lista.get(i+1).getX();
                Ymax = lista.get(i+1).getY();
                Xmin = lista.get(i).getX();
                Ymin = lista.get(i).getY();
            }
            IncX = (Xmax - Xmin) / (Ymax - Ymin);
            NoAET novo = new NoAET(Ymax, Xmin, IncX);
            et.add((int)Ymin, novo);
        }
        
        AET aet = new AET();
        for(int i = 0; i < et.size(); i++)
        {
            if(!et.isEmpty(i))
            {
                aet.setLista(et.get(i));
                while(aet != null && aet.getLista().size() > 0)
                {
                    aet.remove(i);
                    aet.ordena();
                    for(int a = 0; a < aet.getLista().size()-1; a+=2)
                    {
                        if(aet.getLista().size() >=4)
                            System.out.println("");
                        
                        NoAET cx1 = aet.getLista().get(a);
                        NoAET cx2 = aet.getLista().get(a+1);
                        for(int b = (int)cx1.getXmin(); b < cx2.getXmin(); b++)
                            writePixel(b, i, gc, Color.GREEN);
                        cx1.setXmin(cx1.getXmin() + cx1.getIncrX());
                        cx2.setXmin(cx2.getXmin() + cx2.getIncrX());
                    }
                    i++;
                    if(!et.isEmpty(i))
                    {
                        if(et.get(i) != null)
                            aet.setLista(et.get(i));
                        else
                            aet = null;
                    }
                        
                }
            }
        }
    }
    
    public int[] getMaxMin()
    {
        double Xmax = pontosOriginais.get(0).getX(), Ymax = pontosOriginais.get(0).getY();
        double Xmin = Xmax, Ymin = Ymax;
        int[] vet = new int[4];
        for(int i = 0; i < pontosOriginais.size(); i++)
        {
            if(pontosOriginais.get(i).getX() > Xmax)
                Xmax = pontosOriginais.get(i).getX();
            else
                if(pontosOriginais.get(i).getX() < Xmin)
                    Xmin = pontosOriginais.get(i).getX();
            if(pontosOriginais.get(i).getY() > Ymax)
                Ymax = pontosOriginais.get(i).getY();
            else
                if(pontosOriginais.get(i).getY() < Ymin)
                    Ymin = pontosOriginais.get(i).getY();
        }
        vet[0] = (int)Xmax;
        vet[1] = (int)Ymax;
        vet[2] = (int)Xmin;
        vet[3] = (int)Ymin;
        return vet;
    }
    
    /*private double[][] multiplicaMatrizView(double[][] mat)
    {
        double soma;
        double[][] mataux = copiaMatriz(MA, 3, 3);
        for(int l = 0; l < 3; l++)
        {
            for(int c = 0; c < 3; c++)
            {
                soma = 0;
                for(int i = 0; i < 3; i++)
                    soma +=  mat[l][i] * MA[i][c];
                mataux[l][c] = soma;
            }
        }
        return mataux;
    }
    
    private double[][] multiplicaPontosView(double[][] mat, double[][] MA)
    {
        double soma;
        double[][] mataux = copiaMatriz(mat, 3, 1);
        for(int l = 0; l < 3; l++)
        {
            for(int c = 0; c < 1; c++)
            {
                soma = 0;
                for(int i = 0; i < 3; i++)
                    soma += MA[l][i] * mat[i][c];
                mataux[l][c] = soma;
            }
        }
        return mataux;
    }
    
    private void atualizaPontosView(double [][]mat)
    {
        for(int i = 0; i < pontosOriginais.size(); i++)
        {
            double[][] ponto = new double[3][1];
            ponto[0][0] = pontosOriginais.get(i).getX();
            ponto[1][0] = pontosOriginais.get(i).getY();
            ponto[2][0] = 1;
            ponto = multiplicaPontosView(ponto, mat);
            pontosAtuais.set(i, new Point((int)(ponto[0][0]), (int)(ponto[1][0])));
        }
    }*/
    
    public void viewPort(int width, int height, int _width, int _height)
    {
        double porcx =  (double)((double)_width / width);
        double porcy = (double)((double)_height / height);
        Point centro = centroPoligno();
        double x = ((double)centro.getX() / width);
        double y = ((double)centro.getY() / height);
        int cx = (int)x * _width;
        int cy = (int)y * _height;
        translacao(-cx, -cy);
        escala(porcx, porcy, 0);
        translacao(cx, cy);
        
    }
    
    @Override
    public String toString() {
        return "Poligno "+qual;
    }
    
}
