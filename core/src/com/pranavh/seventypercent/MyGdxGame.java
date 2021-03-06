/**
 * MyGdxGame uses a create() and render() game loop to run a simple android game.
 * @author: Pranav Harathi, Ashley Handoko, Brandon Perry
 * Date: 05-26-15
 * Period: 3
 */
package com.pranavh.seventypercent;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;

import java.awt.Rectangle;


public class MyGdxGame extends ApplicationAdapter implements InputProcessor {

    private SpriteBatch batch;
    private TextureAtlas flyAtlas;
    private Animation animation;
    private float timePassed = 0;
    private int curX = 300;
    private int curY = 500;
    private int rectBLX = 0;
    private int rectBLY = 0;
    private int rectTRX = 0;
    private int rectTRY = 0;
    private ShapeRenderer shapeRenderer;
  
    /**
     * Method called before game loop to set up input, render shapes, and set up animation
     */
    @Override
    public void create () {
        batch = new SpriteBatch();

        flyAtlas = new TextureAtlas(Gdx.files.internal("flu.atlas"));
        // fraction is length of one frame in seconds
        animation = new Animation(1/30f, flyAtlas.getRegions());
        // font.scale()??


        // User input
        Gdx.input.setInputProcessor(this);
        shapeRenderer = new ShapeRenderer();

    }

    /**
     * Disposes resources
     */ 
    @Override
    public void dispose() {
        batch.dispose();
        flyAtlas.dispose();
        shapeRenderer.dispose();
    }

    /**
     * Uses a spritebatch to render graphics and renders boxes if they are smaller than 0 x 0
     */
    @Override
	public void render () {
        // Clears screen with white color
        Gdx.gl.glClearColor(0, 0, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        //speeds up drawing
        batch.begin();
        // 0,0 is bottom left in libgdx
        timePassed += Gdx.graphics.getDeltaTime();
        // true is looping, others are coords
        batch.draw(animation.getKeyFrame(timePassed, true), curX++ , curY++);
        if(rectBLX != rectTRX) {
            shapeRenderer.begin(ShapeType.Filled);
            shapeRenderer.rect(rectBLX, rectBLY, Math.abs(rectTRX-rectBLX),Math.abs(rectTRY-rectTRX));
            shapeRenderer.end();
        }

        batch.end();
	}

    /**
     * @param x x coordinate
     * @param y y coordinate
     * @param pointer int to describe pointer
     * @param button button pressed (if pressed)
     * Handles touch down event
     */
    @Override
    public boolean touchDown(int x, int y, int pointer, int button) {
        rectBLX = x;
        rectBLY = y;
        return true;
    }

    /**
     * @param x x coordinate
     * @param y y coordinate
     * @param pointer int to describe pointer
     * Handles touch dragged event
     */
    @Override
    public boolean touchDragged(int x, int y, int pointer) {
        rectTRX = x;
        rectTRY = y;
        return true;
    }

    @Override
    public boolean touchUp(int x, int y, int pointer, int button) {
        return false;
    }
    @Override
    public boolean keyDown (int keycode) {
        return false;
    }

    @Override
    public boolean keyUp (int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped (char character) {
        return false;
    }
    @Override
    public boolean mouseMoved (int x, int y) {
        return false;
    }

    @Override
    public boolean scrolled (int amount) {
        return false;
    }
}
