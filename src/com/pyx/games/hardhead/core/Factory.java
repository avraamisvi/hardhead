package com.pyx.games.hardhead.core;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.media.MediaPlayer;

import com.pyx.games.hardhead.Engine;
import com.pyx.games.hardhead.R;
import com.pyx.games.hardhead.game.Aco;
import com.pyx.games.hardhead.game.BlocoQuebravel;
import com.pyx.games.hardhead.game.Capacete;
import com.pyx.games.hardhead.game.Concreto;
import com.pyx.games.hardhead.game.Fumaca;
import com.pyx.games.hardhead.game.HardHead;
import com.pyx.games.hardhead.game.Level;
import com.pyx.games.hardhead.game.MenuScreen;
import com.pyx.games.hardhead.game.MenuSelecao;
import com.pyx.games.hardhead.game.NewPoint;
import com.pyx.games.hardhead.game.Paw;
import com.pyx.games.hardhead.game.PedacoTijolo;
import com.pyx.games.hardhead.game.StartScreen;
import com.pyx.games.hardhead.game.Tijolo;
import com.pyx.games.hardhead.game.Vidro;

public class Factory {

	private static Factory resourceFactory = new Factory();
	private Resources res;
	private HardHead hardHead;
	
	private int idPedacos = 30;
	
	
	public Bitmap fumaca1;
	public Bitmap fumaca2;
	public Bitmap fumaca3;
	
	public Bitmap blocoAco;
	public Bitmap A;
	public Bitmap T;
	public Bitmap T1;
	public Bitmap V;
	public Bitmap C;
	public Bitmap C1;
	public Bitmap C2;
	public Bitmap C3;
	
	/**
	 * Pedaco
	 */
	public Bitmap T_pedaco;
	public Bitmap C_pedaco;
	public Bitmap V_pedaco;
	
	
	public Bitmap scoreBmp;
	public Bitmap lifeBmp;
	
	
	public Bitmap zero;
	public Bitmap one;
	public Bitmap two;
	public Bitmap three;
	public Bitmap four;
	public Bitmap five;
	public Bitmap six;
	public Bitmap seven;
	public Bitmap eight;
	public Bitmap nine;
	
	public Bitmap paw1;
	public Bitmap paw2;
	public Bitmap paw3;
	
	
	public Bitmap ready;
	public Bitmap go;
	
	public Typeface systemFont;
	
	public Bitmap panelFase;
	public Bitmap starPoint;
	public Bitmap starGrey;
	
	public Bitmap btnMenu;
	public Bitmap btnMenu1;
	public Bitmap btnRestart;
	public Bitmap btnRestart1;
	public Bitmap btnNext;
	public Bitmap btnNext1;
	
//	public Bitmap btnBase;
//	public Bitmap btnSelected;

	public Bitmap capaceteFute;
	public Bitmap capaceteObra;
	public Bitmap capaceteBroca;
	public Bitmap capaceteMoto;
	
	private int idPoints;
	
	public Bitmap btnPlaySel;
	public Bitmap btnPlay;
	public Bitmap escolhaFase;
	public Bitmap miniStar;
	public Bitmap miniStarDvt;
	
	public Bitmap logo;
	
	public static Factory get() {
		return resourceFactory;
	}
	
	public void setRes(Resources res) {
		this.res = res;
	}
	
	public Resources getRes() {
		return res;
	}
	
	public Scene getInitialScene() {
		return new StartScreen();
	}
	
	public SceneObject getPLayerAvatar() {		
		return hardHead;
	}	
	
	public void load() {
		blocoAco = BitmapFactory.decodeResource(Factory.get().getRes(), R.drawable.bloco_aco);
		A = BitmapFactory.decodeResource(Factory.get().getRes(), R.drawable.aco);
		T = BitmapFactory.decodeResource(Factory.get().getRes(), R.drawable.tijolo);
		T1 = BitmapFactory.decodeResource(Factory.get().getRes(), R.drawable.tijolo_quebrado);
		V = BitmapFactory.decodeResource(Factory.get().getRes(), R.drawable.vidro);
		C = BitmapFactory.decodeResource(Factory.get().getRes(), R.drawable.concreto);
		C1 = BitmapFactory.decodeResource(Factory.get().getRes(), R.drawable.concreto1);
		C2 = BitmapFactory.decodeResource(Factory.get().getRes(), R.drawable.concreto2);
		C3 = BitmapFactory.decodeResource(Factory.get().getRes(), R.drawable.concreto3);
		
		T_pedaco = BitmapFactory.decodeResource(Factory.get().getRes(), R.drawable.pedaco_tijolo1);
		C_pedaco = BitmapFactory.decodeResource(Factory.get().getRes(), R.drawable.pedaco_concreto1);
		V_pedaco = BitmapFactory.decodeResource(Factory.get().getRes(), R.drawable.pedaco_vidro1);
		
		scoreBmp = BitmapFactory.decodeResource(Factory.get().getRes(), R.drawable.score);
		lifeBmp = BitmapFactory.decodeResource(Factory.get().getRes(), R.drawable.life);
		
		zero = BitmapFactory.decodeResource(Factory.get().getRes(), R.drawable.zero);
		one = BitmapFactory.decodeResource(Factory.get().getRes(), R.drawable.one);
		two = BitmapFactory.decodeResource(Factory.get().getRes(), R.drawable.two);
		three = BitmapFactory.decodeResource(Factory.get().getRes(), R.drawable.three);
		four = BitmapFactory.decodeResource(Factory.get().getRes(), R.drawable.four);
		five = BitmapFactory.decodeResource(Factory.get().getRes(), R.drawable.five);
		six = BitmapFactory.decodeResource(Factory.get().getRes(), R.drawable.seis);
		seven = BitmapFactory.decodeResource(Factory.get().getRes(), R.drawable.sete);
		eight = BitmapFactory.decodeResource(Factory.get().getRes(), R.drawable.eight);
		nine = BitmapFactory.decodeResource(Factory.get().getRes(), R.drawable.nine);		
		
		paw1 = BitmapFactory.decodeResource(Factory.get().getRes(), R.drawable.pan1);
		paw2 = BitmapFactory.decodeResource(Factory.get().getRes(), R.drawable.pan2);
		paw3 = BitmapFactory.decodeResource(Factory.get().getRes(), R.drawable.pan3);
		
		ready = BitmapFactory.decodeResource(Factory.get().getRes(), R.drawable.ready);
		go = BitmapFactory.decodeResource(Factory.get().getRes(), R.drawable.go);
		
		systemFont = Typeface.createFromAsset(res.getAssets(), "fonts/GillSansUltraBold.ttf");
		
		panelFase = BitmapFactory.decodeResource(Factory.get().getRes(), R.drawable.panel_fase);
		starPoint = BitmapFactory.decodeResource(Factory.get().getRes(), R.drawable.estrela_ponto);
		starGrey = BitmapFactory.decodeResource(Factory.get().getRes(), R.drawable.estrela_cinza);
		
		btnMenu = BitmapFactory.decodeResource(Factory.get().getRes(), R.drawable.btn_menu);
		btnMenu1 = BitmapFactory.decodeResource(Factory.get().getRes(), R.drawable.btn_menu1);
		btnRestart = BitmapFactory.decodeResource(Factory.get().getRes(), R.drawable.btn_restart);
		btnRestart1 = BitmapFactory.decodeResource(Factory.get().getRes(), R.drawable.btn_restart1);
		
		
		btnNext = BitmapFactory.decodeResource(Factory.get().getRes(), R.drawable.btn_next);
		btnNext1 = BitmapFactory.decodeResource(Factory.get().getRes(), R.drawable.btn_next1);
		
		capaceteFute = BitmapFactory.decodeResource(Factory.get().getRes(), R.drawable.capacete_fute);
		capaceteObra = BitmapFactory.decodeResource(Factory.get().getRes(), R.drawable.capacete_obra);
		capaceteBroca = BitmapFactory.decodeResource(Factory.get().getRes(), R.drawable.capacete_broca);
		capaceteMoto = BitmapFactory.decodeResource(Factory.get().getRes(), R.drawable.capacete_moto);
		
		btnPlay = BitmapFactory.decodeResource(Factory.get().getRes(), R.drawable.btn_play);
		btnPlaySel = BitmapFactory.decodeResource(Factory.get().getRes(), R.drawable.btn_play_sel);
		
		escolhaFase = BitmapFactory.decodeResource(Factory.get().getRes(), R.drawable.escolha_fase);
		
		miniStar = BitmapFactory.decodeResource(Factory.get().getRes(), R.drawable.estrelalinha_atv);
		miniStarDvt = BitmapFactory.decodeResource(Factory.get().getRes(), R.drawable.estrelinha_dtv);
		
		logo = BitmapFactory.decodeResource(Factory.get().getRes(), R.drawable.logo);
		
		fumaca1 = BitmapFactory.decodeResource(Factory.get().getRes(), R.drawable.fumaca1);
		fumaca2 = BitmapFactory.decodeResource(Factory.get().getRes(), R.drawable.fumaca2);
		fumaca3 = BitmapFactory.decodeResource(Factory.get().getRes(), R.drawable.fumaca3);
		
		hardHead = new HardHead();
		hardHead.load(res);
	}	
	
	public Scene getScene(int lev) {
		Level scene = null;
    	idPedacos = 30;
		try {
			scene = loadLevel(new DataInputStream(res.getAssets().open("cena"+lev+".cn")));
			scene.levelNumber = lev;
			
			Engine.get().levelPoints = 0;
			Engine.get().hiScore = Engine.get().database.getLevelPoints(lev);
			Engine.get().levelLife = 3;
			
		} catch (IOException e) {
			Engine.get().performingHandlingException(e);
		}		
		
		return scene;
	}
	
	Level loadLevel(DataInputStream in) throws NumberFormatException, IOException {
		Level l = new Level();
		int maxLevelPoints = 0;
		
		BufferedReader rd = new BufferedReader(new InputStreamReader(in));

        String ln = rd.readLine();

        String dt[] = ln.split("@");

        int x = Integer.parseInt(dt[0]);
        int y = Integer.parseInt(dt[1]);

        hardHead.setX(x);
        hardHead.setY(y);
        int id = 0;
        
        int gapw = (Engine.get().getDisplay().getWidth() - ((Factory.get().V.getWidth()*5)))/2;
        
        while((ln = rd.readLine()) != null) {
            dt = ln.split("@");
            id++;
            BlocoQuebravel t = parseType(dt[0], id);

            maxLevelPoints += t.getMaxPoint();
            
            x = Integer.parseInt(dt[1])+gapw - 40;//apaga fator default de desloc
            y = Integer.parseInt(dt[2]);
            
            if(y <=0 )
            	continue;
            
            t.setX(x);
            t.setY(y);
                        	
            l.addBloco(t);
            
            if(dt[0].charAt(0) != 'A')
            	l.countBlocos++;
            
        }        
        
		l.maxLevelPoints = maxLevelPoints;
		return l;
	}
		
	
    public BlocoQuebravel parseType(String tp, int id) {

    	BlocoQuebravel ret = null;

        switch(tp.charAt(0))  {
            case 'A':
                ret = new Aco(""+id);
                break;
            case 'V':
                ret = new Vidro(""+id);
                break;
            case 'T':
                ret = new Tijolo(""+id);
                break;
            case 'C':
                ret = new Concreto(""+id);
                break;
        }

        return ret;

    }
    
    public PedacoTijolo getPedacoTijolo(Bitmap bmp, int x, int y, int vx, int vy) {
    	idPedacos++;
    	
    	PedacoTijolo ped = new PedacoTijolo(idPedacos+"ped", bmp, vx, vy);
    	ped.setX(x);
    	ped.setY(y);
    	
    	return ped;
    }
    
    
    public Capacete getCapacete(Bitmap bmp, int x, int y, int forc, int res) {
    	
    	Capacete capacete = new Capacete("cap", bmp, res, forc);
    	capacete.setX(x);
    	capacete.setY(y);
    	
    	return capacete;
    }
    
    public NewPoint getNewPoint(int x, int y, int vx, int vy, String p, int r, int g, int b) {
    	idPoints++;
    	
    	NewPoint point = new NewPoint(idPoints+"ped", vx, vy, p, r, g, b);
    	point.setX(x);
    	point.setY(y);
    	
    	return point;
    }
    
    public Paw getPaw(int x, int y) {
    	
    	Paw p = new Paw();    	
    	p.setX(x);
    	p.setY(y);
    	
    	return p;
    }

    public Fumaca getFumaca(int x, int y) {
    	
    	Fumaca p = new Fumaca();    	
    	p.setX(x);
    	p.setY(y);
    	
    	return p;
    }
    
	public Scene getMenuScreen() {
		return new MenuScreen();
	}
	
	public Scene getMenuSelecao() {
		return new MenuSelecao();
	}
}
