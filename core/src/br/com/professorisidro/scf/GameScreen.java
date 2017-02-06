package br.com.professorisidro.scf;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.math.Matrix4;

public class GameScreen implements Screen {

	// desenhar um background
	SpriteBatch spriteBatch;
	Texture     texture;
	Matrix4     viewMatrix;
	Matrix4     tranMatrix;
	
	// desenhar em 3D
	PerspectiveCamera camera;
	Environment       environment;
	ModelBatch        modelBatch;
	
	Player     p1;
	Player     p2;
	public GameScreen(){
		spriteBatch = new SpriteBatch(); // buffer ok
		texture     = new Texture(Gdx.files.internal("bg/gameBG.jpg"));
		viewMatrix  = new Matrix4();  // matriz de visualização
		tranMatrix  = new Matrix4();  // matriz de escala
		
		// camera
		camera = new PerspectiveCamera(67.0f, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		camera.near = 0.1f;
		camera.far  = 1000f;
		camera.position.set(0, 5, 10);
		camera.lookAt(0, 5, 0);
		camera.update();
		
		// ambiente
		environment = new Environment();
		environment.set(new ColorAttribute(ColorAttribute.AmbientLight, 1,1,1,1));
		
		// renderizador
		modelBatch = new ModelBatch();
		p1 = new Player(1);
		p2 = new Player(2);
		
		
		
	}
	
	// metodo para inserir funcionalidades da regra do jogo
	public void update(float delta){
		// nao tem nada!!!
		if (Gdx.input.isKeyJustPressed(Input.Keys.S)){
			p1.chuta();
		}
		if (Gdx.input.isKeyJustPressed(Input.Keys.K)){
			p2.chuta();
		}
		if (Gdx.input.isKeyPressed(Input.Keys.A)){
			p1.andaPraTras();
		}
		if (Gdx.input.isKeyPressed(Input.Keys.D)){
			p1.andaPraFrente();
		}
		
		p1.update(delta);
		p2.update(delta);
	}
	
	// metodo para desenhar
	public void draw(float delta){
		Gdx.gl.glViewport(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		Gdx.gl.glClear(GL20.GL_DEPTH_BUFFER_BIT | GL20.GL_COLOR_BUFFER_BIT);
		Gdx.gl.glClearColor(0,0,0,0);
		
		viewMatrix.setToOrtho2D(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		spriteBatch.setProjectionMatrix(viewMatrix);
		spriteBatch.setTransformMatrix(tranMatrix);
		
		spriteBatch.begin();
		spriteBatch.draw(texture, 0,0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight(),
				                  0,0,  texture.getWidth(), texture.getHeight(), false, false);
		spriteBatch.end();
		
		// desenhar o que tem no meu 3D
		modelBatch.begin(camera);
		modelBatch.render(p1.getCurrent(), environment);
		modelBatch.render(p2.getCurrent(), environment);
		modelBatch.end();
	}
	@Override
	public void render(float delta) {
		// TODO Auto-generated method stub
		update(delta);
		draw(delta);
	}

	
	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}
	
	
	
	
// metodos nao utilizados ate aqui	
	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}

	
	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		// sem utilidade
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		// sem utilidade aqui
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		// sem utilidade
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		// sem utilidade
	}


}
