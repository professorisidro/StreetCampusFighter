package br.com.professorisidro.scf;

import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.utils.AnimationController;
import com.badlogic.gdx.graphics.g3d.utils.AnimationController.AnimationDesc;
import com.badlogic.gdx.graphics.g3d.utils.AnimationController.AnimationListener;

public class GameObject extends ModelInstance{
	
	private AnimationController controller;
	private boolean animationFinished;
	
	public GameObject(Model model){
		super(model);
		
		controller = new AnimationController(this);
		controller.setAnimation(this.animations.get(0).id, -1, 
				new AnimationListener() {
					
					@Override
					public void onLoop(AnimationDesc animation) {
						// TODO Auto-generated method stub
					    animationFinished = true;	
					}
					
					@Override
					public void onEnd(AnimationDesc animation) {
						// TODO Auto-generated method stub
						animationFinished = true;
					}
				});
	}
	
	public void update(float delta){
		controller.update(delta);
	}
	
	public boolean isAnimationFinished(){
		return animationFinished;
	}
	public void resetAnimation(){
		animationFinished = false;
	}
}
