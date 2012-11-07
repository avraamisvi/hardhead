package com.pyx.games.hardhead.game;

import android.graphics.Bitmap;

import com.pyx.games.hardhead.Engine;
import com.pyx.games.hardhead.core.Factory;
import com.pyx.games.hardhead.core.SoundManager;

public class Vidro extends BlocoQuebravel {

	public Bitmap normal;
	
	public Vidro(String id) {
		super(id);	
	
		setNormal(Factory.get().V);
		setSound(SoundManager.VIDRO_CRASH);
	}

	@Override
	public void colidiu() {
		super.colidiu();
		
		gerarPaw();
		
		int cx = getRect().centerX();
		int cy = getRect().centerY();
		int b = getRect().bottom;
		int t = getRect().top;				
		int l = getRect().left;
		int r = getRect().right;
		
		setRemove(true);
		Engine.get().getScene().add(Factory.get().getPedacoTijolo(Factory.get().V_pedaco, l, b, -80, 280), 2);
		Engine.get().getScene().add(Factory.get().getPedacoTijolo(Factory.get().V_pedaco, cx, b, 0, 280), 2);
		Engine.get().getScene().add(Factory.get().getPedacoTijolo(Factory.get().V_pedaco, r, b, 80, 280), 2);
		
		Engine.get().getScene().add(Factory.get().getPedacoTijolo(Factory.get().V_pedaco, l, t, -50, 260), 2);
		Engine.get().getScene().add(Factory.get().getPedacoTijolo(Factory.get().V_pedaco, cx, t, 0, 260), 2);
		Engine.get().getScene().add(Factory.get().getPedacoTijolo(Factory.get().V_pedaco, r, t, 50, 260), 2);
		
		Engine.get().getScene().add(Factory.get().getPedacoTijolo(Factory.get().V_pedaco, l, cy, -120, 255), 2);
		Engine.get().getScene().add(Factory.get().getPedacoTijolo(Factory.get().V_pedaco, r, cy, 120, 255), 2);
	}

	int brilhoTime = 0;
	@Override
	public void updateBehaviour(long delay) throws Exception {		
		
	}
	
	public void setNormal(Bitmap normal) {
		this.normal = normal;
		setImage(normal);
	}

	@Override
	public int getPoint() {
		return 100;
	}
	
	public int getMaxPoint() {
		return 100;
	}	
}
