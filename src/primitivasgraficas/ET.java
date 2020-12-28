/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package primitivasgraficas;

import java.util.List;

/**
 *
 * @author g4l1l3u
 */
public class ET {
    
    private AET[] et;
    private int tam;

    //Cria ET
    public ET(int tam)
    {
        et = new AET[tam];
        this.tam = tam;
    }
    
    //Inicializa bloco especifico da ET, para adicionar NoAET
    private void init(int pos)
    {
        et[pos] = new AET();
    }
    
    //Insere no vetor de ET e na Lista AET determinado no
    public void add(int pos, NoAET no)
    {
        if(et[pos] == null)
            init(pos);
        et[pos].add(no);
    }
    
    public int size()
    {
        return tam;
    }
    
    public List<NoAET> get(int pos)
    {
        if(pos < tam)
            return et[pos].getLista();
        return null;
    }
    
    public boolean isEmpty(int pos)
    {
        return pos < tam && et[pos] == null;
    }

  
    
    
}
