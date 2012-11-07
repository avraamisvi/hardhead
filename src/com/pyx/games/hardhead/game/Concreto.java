package com.pyx.games.hardhead.game;

import android.graphics.Bitmap;

import com.pyx.games.hardhead.Engine;
import com.pyx.games.hardhead.core.Factory;
import com.pyx.games.hardhead.core.SoundManager;

public class Concreto extends BlocoQuebravel {

	public Bitmap normal;
	public Bitmap quebrado;
	public Bitmap quebrado2;
	public Bitmap quebrado3;
	
	int pancadas;
	long firstCollision;
	long lastCollision;
	
	public Concreto(String id) {
		super(id);		
		
		setNormal(Factory.get().C);
		quebrado = Factory.get().C1;
		quebrado2 = Factory.get().C2;
		quebrado3 = Factory.get().C3;
		setSound(SoundManager.CONCRETO_CRASH);
	}

	@Override
	public void colidiu() {
		super.colidiu();

		HardHead hd = (HardHead) Factory.get().getPLayerAvatar();
		
		if(hd.getCapaceteRes() > 0) {
			pancadas = pancadas+hd.capaceteForca;
			firstCollision = System.currentTimeMillis();
		} else {
			pancadas++;
		}
		
		gerarPaw();
		
		int cx = getRect().centerX();
		int cy = getRect().centerY();
		int b = getRect().bottom;
		int t = getRect().top;				
		int l = getRect().left;
		int r = getRect().right;
		
		if(pancadas > 4) {
			pancadas = 4;
		}
		
		if(pancadas == 1) {
			setImage(quebrado);
			firstCollision = System.currentTimeMillis();
		} else if(pancadas == 2) {
			Engine.get().getScene().add(Factory.get().getPedacoTijolo(Factory.get().C_pedaco, l, b, -180, 280), 2);
			Engine.get().getScene().add(Factory.get().getPedacoTijolo(Factory.get().C_pedaco, cx, b, 0, 280), 2);
			Engine.get().getScene().add(Factory.get().getPedacoTijolo(Factory.get().C_pedaco, r, b, 180, 280), 2);			
			setImage(quebrado2);
		} else if(pancadas == 3) {
			setImage(quebrado3);
			Engine.get().getScene().add(Factory.get().getPedacoTijolo(Factory.get().C_pedaco, l, b, -180, 280), 2);
			Engine.get().getScene().add(Factory.get().getPedacoTijolo(Factory.get().C_pedaco, cx, b, 0, 280), 2);
			Engine.get().getScene().add(Factory.get().getPedacoTijolo(Factory.get().C_pedaco, r, b, 180, 280), 2);			
		} else if(pancadas == 4) {
			setRemove(true);
			Engine.get().getScene().add(Factory.get().getPedacoTijolo(Factory.get().C_pedaco, l, b, -80, 280), 2);
			Engine.get().getScene().add(Factory.get().getPedacoTijolo(Factory.get().C_pedaco, cx, b, 0, 280), 2);
			Engine.get().getScene().add(Factory.get().getPedacoTijolo(Factory.get().C_pedaco, r, b, 80, 280), 2);
			
			Engine.get().getScene().add(Factory.get().getPedacoTijolo(Factory.get().C_pedaco, l, t, -50, 260), 2);
			Engine.get().getScene().add(Factory.get().getPedacoTijolo(Factory.get().C_pedaco, cx, t, 0, 260), 2);
			Engine.get().getScene().add(Factory.get().getPedacoTijolo(Factory.get().C_pedaco, r, t, 50, 260), 2);
			
			Engine.get().getScene().add(Factory.get().getPedacoTijolo(Factory.get().C_pedaco, l, cy, -120, 240), 2);
			Engine.get().getScene().add(Factory.get().getPedacoTijolo(Factory.get().C_pedaco, r, cy, 120, 220), 2);
			
			lastCollision = System.currentTimeMillis();
		}
		
	}

	@Override
	public void updateBehaviour(long delay) throws Exception {		
		
	}
	
	public void setNormal(Bitmap normal) {
		this.normal = normal;
		setImage(normal);
	}

	@Override
	public int getPoint() {
		int ret = 300;
		
		if(lastCollision - firstCollision < 5000) {
			ret = ret*3;
		} else if(lastCollision - firstCollision < 8000) {
			ret = ret*2;
		} 
		
		return ret;
	}

	@Override
	public int getMaxPoint() {
		return 300*3;
	}

	@Override
	public int getMinPoint() {
		return 300;
	}
	
}
