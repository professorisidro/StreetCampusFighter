package br.com.professorisidro.scf;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.loaders.ModelLoader;
import com.badlogic.gdx.assets.loaders.ModelLoader.ModelParameters;
import com.badlogic.gdx.graphics.g3d.loader.G3dModelLoader;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.UBJsonReader;

public class Player {

	private int estado;
	private int id;     // Player 1 ou Player 2
	public static final int IDLE    = 0;
	public static final int KICK    = 1;
	public static final int HIT     = 2;
	public static final int VICTORY = 3;
	public static final int DYING   = 4;
	public static final int DEAD    = 5;
	private GameObject estados[];
	
	public Player(int id){
		this.id = id;
		
		estados = new GameObject[5];
		// só por enquanto - isso não garante performance
		ModelLoader<ModelParameters> loader;
		loader = new G3dModelLoader(new UBJsonReader());
		
		estados[IDLE] = new GameObject(loader.loadModel(
				                  Gdx.files.internal("player/idle_fight_a.g3db")));
		estados[KICK] = new GameObject(loader.loadModel(
								  Gdx.files.internal("player/kick_a.g3db")));
		estados[HIT] = new GameObject(loader.loadModel(
				  Gdx.files.internal("player/head_hit_a.g3db")));
		estados[VICTORY] = new GameObject(loader.loadModel(
				  Gdx.files.internal("player/victory_a.g3db")));
		estados[DYING] = new GameObject(loader.loadModel(
				  Gdx.files.internal("player/die_spectacular_a.g3db")));
		// TODO - mais players
		
		if (id == 1){
			for (GameObject ob: estados){
				ob.transform.rotate(Vector3.Y, 90);
				ob.transform.translate(0, 0, -5);
			}
		}
		
		else {
			for (GameObject ob: estados){
				ob.transform.rotate(Vector3.Y, -90);
				ob.transform.translate(0, 0, -5);
			}	
		}
		estado = IDLE;
	}
	
	public void update(float delta){
		estados[estado].update(delta);
		if (estado == KICK && estados[estado].isAnimationFinished()){
			estados[estado].resetAnimation();
			estado = IDLE;
		}
	}
	
	public void chuta(){
		estado = KICK;	
	}
	public void andaPraFrente(){
		for (GameObject ob: estados){
			ob.transform.translate(0, 0, 0.1f);
		}
	}
	public void andaPraTras(){
		for (GameObject ob: estados){
			ob.transform.translate(0, 0, -0.1f);
		}
	}
	
	public GameObject getCurrent(){
		return estados[estado];
	}
}
