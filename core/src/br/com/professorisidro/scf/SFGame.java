package br.com.professorisidro.scf;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;

public class SFGame extends Game {

	GameScreen screen;
	float      delta;
	@Override
	public void create() {
		// TODO Auto-generated method stub
		screen = new GameScreen();
	}
	
	public void render(){
		delta = Gdx.graphics.getDeltaTime();
		screen.render(delta);
	}
	
}
