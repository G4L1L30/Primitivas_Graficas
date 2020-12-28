/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package primitivasgraficas;

import java.awt.Point;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

/**
 *
 * @author g4l1l3u
 */
public class FXMLTelaPrimitivasController implements Initializable {
    
    @FXML
    private TabPane tabPrincipal;
    @FXML
    private Tab tabPrimitivas;
    @FXML
    private Canvas canvas;
    @FXML
    private AnchorPane acPCanvas;
    @FXML
    private AnchorPane acPBotoes;
    @FXML
    private VBox vboxReta;
    @FXML
    private Label lbReta;
    @FXML
    private RadioButton rbEqReal;
    @FXML
    private RadioButton rbDDA;
    @FXML
    private RadioButton rbBresenham;
    @FXML
    private Button btDesfazer;
    @FXML
    private VBox vboxcircunferencia;
    @FXML
    private Label lbCircunferencia;
    @FXML
    private RadioButton rbEqGeralCirc;
    @FXML
    private RadioButton rbTrigonometrica;
    @FXML
    private RadioButton rbPontoMedio;
    @FXML
    private ToggleGroup primitivas;
    @FXML
    private VBox vboxElipse;
    @FXML
    private Label lbElipse;
    @FXML
    private RadioButton rbElipsePP;
    
    //FXML Transformações 2D
    @FXML
    private Canvas canvas2D;
    @FXML
    private AnchorPane acPTransformacoes;
    @FXML
    private Button clkNovo;
    @FXML
    private Button btReset;
    @FXML
    private ComboBox<Poligno> cbListaPolignos;
    @FXML
    private Label lbPolignos;
    @FXML
    private TableView<LinhaTabela> tbvXY;
    @FXML
    private TableColumn<?, ?> tbvCP;
    @FXML
    private TableColumn<?, ?> tbvCX;
    @FXML
    private TableColumn<?, ?> tbvCY;
    @FXML
    private Pane pnEscala;
    @FXML
    private Label lbXEscala;
    @FXML
    private TextField tfXEscala;
    @FXML
    private Label lbYEscala;
    @FXML
    private TextField tfYEscala;
    @FXML
    private Label lbEscala;
    @FXML
    private Button btOkEscala;
    @FXML
    private Pane pnTranslacao;
    @FXML
    private Label lbXTrans;
    @FXML
    private TextField tfXTrans;
    @FXML
    private Label lbYTrans;
    @FXML
    private TextField tfYTrans;
    @FXML
    private Label lbTrans;
    @FXML
    private Button btOkTrans;
    @FXML
    private Button btExcluir;
    @FXML
    private Pane pnRotacao;
    @FXML
    private RadioButton rbCentro;
    @FXML
    private RadioButton rbOrigem;
    @FXML
    private Label lbRotacao;
    @FXML
    private TextField tfRotacao;
    @FXML
    private Button btOkRotacao;
    @FXML
    private ToggleGroup rotacao;
    @FXML
    private Pane pnSelecao;
    @FXML
    private Pane pnCisX;
    @FXML
    private Label lbCisalhamentoX;
    @FXML
    private TextField tfCisX;
    @FXML
    private Button btOkCisX;
    @FXML
    private Pane pnEspelho;
    @FXML
    private Label lbEspelhamento;
    @FXML
    private RadioButton rbEspX;
    @FXML
    private ToggleGroup espelhamento;
    @FXML
    private RadioButton rbEspY;
    @FXML
    private Button btOkEsp;
    @FXML
    private Pane pnCisY;
    @FXML
    private Label lbCisalhamentoY;
    @FXML
    private TextField tfCisY;
    @FXML
    private Button btOkCisY;
    @FXML
    private MenuButton mbCor;
    @FXML
    private MenuItem mbCorFlood;
    @FXML
    private MenuItem mbCorScan;
    //FXML View Port
    @FXML
    private AnchorPane pnView;
    @FXML
    private Label lbAltura;
    @FXML
    private TextField tfAltura;
    @FXML
    private Label lbLargura;
    @FXML
    private TextField tfLargura;
    @FXML
    private Button btAplicar;
    @FXML
    private Button btResetarView;
    @FXML
    private AnchorPane pnCanvasView;
    @FXML
    private Canvas canvasViewPort;
    @FXML
    private Label lbAvisoAltura;
    @FXML
    private Label lbAvisoLargura;
    @FXML
    private Label lbAvisosView;
    
    
    //Variaveis das primitivas graficas
    private List<Point> pontos, coordenadas;
    private List<Object> objetos;
    private Reta r;
    private Circunferencia c;
    private Elipse e;
    private int conta;
    private double raio;
    private Color cor;
    //fim das variaveis primitivas
    
    private Poligno p, auxP;
    private List<Poligno> listaP;
    private LinhaTabela lp[];
    private int contaPoli, select;
    private Point auxPonto;
    
    private boolean flood;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        pontos = new ArrayList<>();
        objetos = new ArrayList<>();
        conta = 0;
        flood = false;
        
        //Configurar o tableView da Transformação2D
        tbvCP.setCellValueFactory(new PropertyValueFactory<>("P"));
        tbvCX.setCellValueFactory(new PropertyValueFactory<>("CX"));
        tbvCY.setCellValueFactory(new PropertyValueFactory<>("CY"));
        
        lp = new LinhaTabela[100];
        listaP = new ArrayList<>();
        contaPoli = 0;
        auxP = null;
    }
    
    //Area das Primitivas Graficas
    
    @FXML
    private void clkUp(MouseEvent event) {
        pontos.add(new Point((int) event.getX(), (int) event.getY()));//adiciona x,y do mouse
        clkMouse();
    }

    @FXML
    private void clkDown(MouseEvent event) {
        pontos.add(new Point((int) event.getX(), (int) event.getY()));//adiciona x,y do mouse
    }
    
    private void clkMouse() {
        
        if (pontos.size() == 2) 
        {
            coordenadas = new ArrayList<>();//para cada reta/circuferencia/elipse um novo list de x,y é criado 
            
            r = new Reta();//cria uma nova reta
            c = new Circunferencia();//cria uma nova circunferencia
            e = new Elipse();//cria uma nova elipse
            raio = Math.sqrt(Math.pow(pontos.get(1).x-pontos.get(0).x,2)+Math.pow(pontos.get(1).y-pontos.get(0).y,2));//calculo do raio para circunferencia
            
            if (rbEqReal.isSelected()) {
                r.eqRealReta(pontos.get(0), pontos.get(1));
            } else if (rbDDA.isSelected()) {
                r.DDA(pontos.get(0), pontos.get(1));
            } else if (rbBresenham.isSelected()) {
                r.bresenham(pontos.get(0), pontos.get(1));
            } else if (rbEqGeralCirc.isSelected()) {
                c.eqGeralCircunferencia(pontos.get(0), raio, canvas.getGraphicsContext2D());
            } else if (rbTrigonometrica.isSelected()) {
                c.eqTrigonometrica(pontos.get(0), raio, canvas.getGraphicsContext2D());
            } else if (rbPontoMedio.isSelected()){
                c.pontoMedio(pontos.get(0), raio, canvas.getGraphicsContext2D());
            } else if (rbElipsePP.isSelected()) {
                e.elipsePontoMedio(pontos.get(0), (int) Math.abs(pontos.get(0).getX() - pontos.get(1).getX()), (int) Math.abs(pontos.get(0).getY() - pontos.get(1).getY()), canvas.getGraphicsContext2D());
            }
            
            if (!r.getCoordenadas().isEmpty()) {
                coordenadas.addAll(r.getCoordenadas());//adicionas todos os pontos x,y da reta
                objetos.add(r.getColor());//adiciona a cor da reta
            } else if (!c.getCoordenadas().isEmpty()) {
                coordenadas.addAll(c.getCoordenadas());//adicionas todos os pontos x,y da circunferencia
                objetos.add(c.getColor());//adiciona a cor da circunferencia
            } else if (!e.getCoordenadas().isEmpty()) {
                coordenadas.addAll(e.getCoordenadas());//adicionas todos os pontos x,y da elipse
                objetos.add(e.getColor());//adiciona a cor da elipse
            }
            
            objetos.add(coordenadas);//adicona o list de coordenas a um objeto
            desenha();//desenha apenas apenas o ultimo elemento
            conta+=2;//vai guardando quantos elementos forem adicionados
            pontos.clear();//limpa os pontos x,y do mouse
            
        }
    }
  
    @FXML
    private void clkDesfazer(ActionEvent event) 
    {
        if (objetos.size() > 0) {
            canvas.getGraphicsContext2D().clearRect(0, 0, canvas.getWidth(), canvas.getHeight());//Limpa o canvas
            for (int j = 0; j < conta - 2; j+=2) {
                cor = (Color) objetos.get(j);
                List<Point> coord = (List<Point>) objetos.get(j+1); //pega todas as coordenadas de cada objeto, exceto o ultimo
                for (int i = 0; i < coord.size(); i++) {
                    writePixel((int) coord.get(i).getX(), (int) coord.get(i).getY(), canvas.getGraphicsContext2D(), cor); //desenha todos novamente, menos o ultimo
                }
            }
            conta--;
            objetos.remove(conta);//remove o ultimo objeto coordenadas
            conta--;
            objetos.remove(conta);//remove o ultimo objeto Color
        }
    }
    
    private void desenha()
    {
       cor = (Color) objetos.get(conta);
       List<Point> coord = (List<Point>)objetos.get(conta+1);
       for(int i = 0; i < coord.size(); i++)
           writePixel((int)coord.get(i).getX(), (int)coord.get(i).getY(), canvas.getGraphicsContext2D(), cor);
    }
    
    private void writePixel(int x, int y, GraphicsContext gc, Color cor)
    {
        gc.fillRect(x, y, 1, 1);
        gc.setFill(cor);
    }
    
    //Fim das Primitivas Graficas
    //Inicio das Transformações 2D
    
    
    @FXML
    private void clkDownP(MouseEvent event) {
        pontos.add(new Point((int) event.getX(), (int) event.getY()));//adiciona x,y do mouse
    }
    
    @FXML
    private void clkUpP(MouseEvent event) {
        pontos.add(new Point((int) event.getX(), (int) event.getY()));//adiciona x,y do mouse
        if(flood == false)
            Poli();
    }

    private void Poli()
    {
        Reta r = new Reta();
        Point aux;
        p.addPonto((int)pontos.get(0).getX(), (int)pontos.get(0).getY());
        p.addPonto((int)pontos.get(1).getX(), (int)pontos.get(1).getY());
        aux = p.fechaPoligno(pontos.get(1).getX(), pontos.get(1).getY());
        pontos.set(1, aux);
        r.bresenham(pontos.get(0), pontos.get(1));
        desenhaRetaPoli(r, canvas2D.getGraphicsContext2D());
        pontos.clear();
        AttTableView(p);
        if(p.polignoOk())
        {
            listaP.add(p);
            p.setarqual(contaPoli);
            cbListaPolignos.getItems().add(listaP.get(contaPoli++));
            cbListaPolignos.setDisable(false);
            canvas2D.setDisable(true);
        }
    }
    
    
    @FXML
    private void clkBtOkEscala(ActionEvent event) {
        if(!tfXEscala.getText().isEmpty() && !tfYEscala.getText().isEmpty())
        {
            emRelacao();
            double x = Double.parseDouble(tfXEscala.getText()), y = Double.parseDouble(tfYEscala.getText());
            auxP.escala(x, y, select);
            AttTableView(auxP);
            desenhaPolignos(canvas2D.getGraphicsContext2D());
        }
    }

    @FXML
    private void clkBtOkTranslacao(ActionEvent event) {
        if(!tfXTrans.getText().isEmpty() && !tfYTrans.getText().isEmpty())
        {
            double x = Double.parseDouble(tfXTrans.getText()), y = Double.parseDouble(tfYTrans.getText());
            auxP.translacao(x, y);
            AttTableView(auxP);
            desenhaPolignos(canvas2D.getGraphicsContext2D());
        }
    }
    
    @FXML
    private void clkBtOkRot(ActionEvent event) {
        if(!tfRotacao.getText().isEmpty())
        {
            emRelacao();
            double angulo = Double.parseDouble(tfRotacao.getText());
            auxP.rotacao(angulo, auxPonto.getX(), auxPonto.getY(), select);
            AttTableView(auxP);
            desenhaPolignos(canvas2D.getGraphicsContext2D());
        }
    }
    
    @FXML
    private void clkBtOkCisX(ActionEvent event) {
        if(!tfCisX.getText().isEmpty())
        {
            emRelacao();
            double valor = Double.parseDouble(tfCisX.getText());
            auxP.cisalhamentoX(valor, auxPonto);
            AttTableView(auxP);
            desenhaPolignos(canvas2D.getGraphicsContext2D());
        }
    }
    
    @FXML
    private void clkBtOkCisY(ActionEvent event) {
        if(!tfCisY.getText().isEmpty())
        {
            emRelacao();
            double valor = Double.parseDouble(tfCisY.getText());
            auxP.cisalhamentoY(valor, auxPonto);
            AttTableView(auxP);
            desenhaPolignos(canvas2D.getGraphicsContext2D());
        }
    }
    

    @FXML
    private void clkBtOkEsp(ActionEvent event) {
        emRelacao();
        
        if(rbEspX.isSelected())
            auxP.espelhoX(auxPonto);
        else
            if(rbEspY.isSelected())
                auxP.espelhoY(auxPonto);
        AttTableView(auxP);
        desenhaPolignos(canvas2D.getGraphicsContext2D());
    }

    
    private void emRelacao()
    {
        if(rbCentro.isSelected())
        {
            auxPonto = new Point(auxP.centroPoligno());
            select = 1;
        }
        else
        {
            auxPonto = new Point(0, 0);
            select = 0;
        }
            
    }

    
    @FXML
    private void clkBtNovo(ActionEvent event) {
        p = new Poligno();
        canvas2D.setDisable(false);
        limpa();
    }
    
    @FXML
    private void clkBtExcluir(ActionEvent event) {
        if(listaP.size() > 0)
        {
            listaP.remove(auxP);
            cbListaPolignos.getItems().remove(auxP);
            tbvXY.getItems().clear();
            contaPoli--;
            desenhaPolignos(canvas2D.getGraphicsContext2D());
            desabilita();
        }
    }
    
    @FXML
    private void clkBtReset(ActionEvent event) {
        desenhaPolignos(canvas2D.getGraphicsContext2D());
        tbvXY.getItems().clear();
        canvas2D.setDisable(true);
        pontos.clear();
    }

    private void limpa()
    {
        tfXEscala.clear();
        tfYEscala.clear();
        tfXTrans.clear();
        tfYTrans.clear();
        pontos.clear();
        desabilita();
    }
    
    private void desabilita()
    {
        pnSelecao.setDisable(true);
        pnEscala.setDisable(true);
        pnTranslacao.setDisable(true);
        pnCisX.setDisable(true);
        pnCisY.setDisable(true);
        pnEspelho.setDisable(true);
        pnRotacao.setDisable(true);
        btExcluir.setDisable(true);
        mbCor.setDisable(true);
    }
    
    private void habilita()
    {
        pnSelecao.setDisable(false);
        pnEscala.setDisable(false);
        pnTranslacao.setDisable(false);
        pnCisX.setDisable(false);
        pnCisY.setDisable(false);
        pnEspelho.setDisable(false);
        pnRotacao.setDisable(false);
        btExcluir.setDisable(false);
        mbCor.setDisable(false);
    }

    private void AttTableView(Poligno aux)
    {
        tbvXY.getItems().clear();
        for(int i = 0; i < aux.size(); i++)
        {
            lp[i] = new LinhaTabela("P"+i);
            lp[i].setCX((int)aux.getCx(i) + "");
            lp[i].setCY((int)aux.getCY(i) + "");
            tbvXY.getItems().add(lp[i]);
        }
    }
    
    @FXML
    private void clkSelectPoli(ActionEvent event) {
        if(listaP.size() >= 0 && cbListaPolignos.getSelectionModel().selectedItemProperty().get() != null)
        {
            auxP = cbListaPolignos.getSelectionModel().selectedItemProperty().get();
            AttTableView(auxP);
            habilita();
        }
    }

    @FXML
    private void clkMbCorFlod(ActionEvent event) {
        
        flood = true;
        canvas2D.setDisable(false);
    }

    @FXML
    private void clkMbCorScan(ActionEvent event) {
        if(auxP != null)
            auxP.ET(canvas2D.getGraphicsContext2D());
    }

    @FXML
    private void teste(MouseEvent event) {
        
        if(auxP != null && flood)
        {
            auxP.floodFill(canvas2D.getGraphicsContext2D(), (int)event.getX(), (int)event.getY());
            flood = false;
            canvas2D.setDisable(true);
        }
        
    }
    
    public Poligno getPoli(int pos)
    {
        Poligno novo = new Poligno();
        novo.setPontos(listaP.get(pos).getPontosOriginais());
        return novo;
    }

    @FXML
    private void clkBtAplicar(ActionEvent event) {
        if(!tfAltura.getText().isEmpty() && !tfLargura.getText().isEmpty())
        {
            lbAvisosView.setVisible(false);
            int height = Integer.parseInt(tfAltura.getText()), width = Integer.parseInt(tfLargura.getText());
            if(height > 0 && height < pnCanvasView.getHeight())
            {
                lbAvisosView.setVisible(false);
                if(width > 0 && width < pnCanvasView.getWidth())
                {
                    lbAvisosView.setVisible(false);
                    canvasViewPort.setWidth(width);
                    canvasViewPort.setHeight(height);
                    
                    canvasViewPort.getGraphicsContext2D().clearRect(0, 0, canvasViewPort.getWidth(), canvasViewPort.getHeight());//Limpa o canvas
                    
                    for(int i = 0; i < listaP.size(); i++)
                    {
                        Poligno aux  = getPoli(i);
                        aux.viewPort((int)canvas2D.getWidth(), (int)canvas2D.getHeight(), (int)canvasViewPort.getWidth(), (int)canvasViewPort.getHeight());
                        
                        redesenha(aux, canvasViewPort.getGraphicsContext2D());
                    }
                        
                }
                else
                {
                    lbAvisosView.setText("Largura deve ser maior que 0 ou menor que 768");
                    lbAvisosView.setVisible(true);
                }
            }
            else
            {
                
                lbAvisosView.setText("Altura deve ser maior que 0 ou menor que 412");
                lbAvisosView.setVisible(true);
            }
        }
        else
        {
            
            lbAvisosView.setText("Defina o valor da Altura e Largura");
            lbAvisosView.setVisible(true);
        }
        
    }
    
    @FXML
    private void clkBtResetarView(ActionEvent event) {
        canvasViewPort.getGraphicsContext2D().clearRect(0, 0, canvasViewPort.getWidth(), canvasViewPort.getHeight());//Limpa o canvas
        tfAltura.clear();
        tfLargura.clear();
    }

    
    
    
    private void desenhaPolignos(GraphicsContext gc)
    {
        gc.getCanvas().getGraphicsContext2D().clearRect(0, 0, gc.getCanvas().getWidth(), gc.getCanvas().getHeight());//Limpa o canvas
        for(int i = 0; i < contaPoli; i++)
        {
            redesenha(listaP.get(i), gc);
        }
    }
    
    private void redesenha(Poligno aux, GraphicsContext gc)
    {
        for(int i = 0; i < aux.size()-1; i++)
        {
            Reta r = new Reta();
            r.bresenham(new Point((int)aux.getCx(i), (int)aux.getCY(i)), new Point((int)aux.getCx(i+1), (int)aux.getCY(i+1)));
            desenhaRetaPoli(r, gc);
        }
    }
    
    private void desenhaRetaPoli(Reta r, GraphicsContext gc)
    {
       List<Point> coord = r.getCoordenadas();
       for(int i = 0; i < coord.size(); i++)
           writePixel((int)coord.get(i).getX(), (int)coord.get(i).getY(), gc.getCanvas().getGraphicsContext2D(), Color.RED);
    }

    

    
}
