package mehdi.pol;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.math.*;

public class MyGdxGame implements ApplicationListener
{
	Texture texture;
	SpriteBatch batch;
	OrthographicCamera camera;
	float time;
	Animation walkAnimation;
	


	public void create()
	{	
		//number of sprites 3*3
		int FRAME_ROWS = 3;
		int FRAME_COLS = 3;
		
		
		Texture balltexture = new Texture("spritesheet.png");
		
		//parting the spritesheet into 9 sprites and store them in a 2D table
		//3072 is the width and height of spritesheet.png in pixel
		TextureRegion[][] tmp = TextureRegion.split(balltexture, 3072 / FRAME_COLS,
																 3072 / FRAME_ROWS);
		
		//store the sprites in 1d table									 
		TextureRegion[] walkFrames = new TextureRegion[FRAME_COLS * FRAME_ROWS];
		int index = 0;
		for (int i = 0; i < FRAME_ROWS; i++)
		{
			for (int j = 0; j < FRAME_COLS; j++)
			{
				walkFrames[index++] = tmp[i][j];
			}
		}
		
		//make the animation and the time between 2 frame
		walkAnimation = new Animation(0.5f, walkFrames);

		batch = new SpriteBatch();
		camera = new OrthographicCamera();
	}

	public void render()
	{
		//rectangle to set the position && the size of the ball  
		//initialize Rectangle before use it
		Rectangle ballPositionAndSize= new Rectangle();
		
		//size of the ball
		ballPositionAndSize.width = 300;
		ballPositionAndSize.height = 300;
		
		//position of the ball in the middle of the screen
		ballPositionAndSize.x = Gdx.graphics.getWidth()/2 -ballPositionAndSize.width/2;
		ballPositionAndSize.y = Gdx.graphics.getHeight()/2 -ballPositionAndSize.height/2;
		
		time+=Gdx.graphics.getDeltaTime();

		//background black
	    Gdx.gl.glClearColor(0,0,0,0);
	    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		camera.update();
		
		
		batch.begin();
		//draw the ball
		batch.draw(walkAnimation.getKeyFrame(time,true),ballPositionAndSize.x , ballPositionAndSize.y , ballPositionAndSize.width , ballPositionAndSize.height);

		batch.end();
	}

	@Override
	public void dispose()
	{
	}

	@Override
	public void resize(int width, int height)
	{
	}

	@Override
	public void pause()
	{
	}

	@Override
	public void resume()
	{
	}
}
