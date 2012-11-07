package com.pyx.games.hardhead.core;

import java.util.ArrayList;
import java.util.List;

import com.pyx.games.hardhead.Engine;
import com.pyx.games.hardhead.game.HardHead;
import com.pyx.games.hardhead.game.Level;

import android.graphics.Canvas;

public class Layer {
	
	private List<SceneObject> actors = new ArrayList<SceneObject>();
	
	public void draw(Canvas canvas) {
		for (SceneObject actor : actors) {
			actor.draw(canvas);
		}
	}
	

	public void update(long delay) {
		
		Scene sc = Engine.get().getScene();
		
		SceneObject[] acs = this.actors.toArray(new SceneObject[0]);
		for (SceneObject actor : acs) {
			
			if(sc.isLevel) {
				if(actor != Factory.get().getPLayerAvatar() && actor.isCanColide()) {
					if(colide(actor, (HardHead) Factory.get().getPLayerAvatar())) {
						Factory.get().getPLayerAvatar().colision(actor, delay);
					}
				}
				
				if(actor.breakerColide) {
					Level le = (Level) sc;
					if(actor.getRect().intersect(le.breaker.getRect())) {
						try {
							le.breaker.colisionBehaviour(actor, delay);
						} catch (Exception e) {
							Engine.get().performingHandlingException(e);
						}
					}
				}
			}
			
			if(actor.isRemove()) {
				remove(actor);
				continue;
			}
			
			actor.update(delay);
		}
	}
	
	public void add(SceneObject actor) {
		actors.add(actor);
	}


	public void remove(SceneObject actor) {
		actors.remove(actor);
		actor.destroy();
	}
	
	boolean colide(SceneObject o, HardHead h) {
		 return o.getRect().intersect(h.getRect());		 
	}
}
