package entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;

import BackEnd.LogFileHandler;
import BackEnd.Mutagen;

public class FlayerThorns {
	public World world; // world player will live in
	public Body b2body; // creates body for player
	private BodyDef bdef = new BodyDef();
	private float speed = 1, startX, startY, posX, posY, shootingAngle, timePassed, angle;
	private TextureAtlas thornTextureAtlas;
	private Animation<TextureRegion> thornAnimation;
	public static Array<FlayerThorns> flayerThorns = new Array<FlayerThorns>();
	LogFileHandler lfh = new LogFileHandler();

	public FlayerThorns(World world, float positionX, float positionY, float angle) {
		try {
			this.world = world;
			startX = positionX;
			startY = positionY;
			shootingAngle = angle;
			thornTextureAtlas = Mutagen.manager.get("sprites/flayer/flayerThorn.atlas");
			thornAnimation = new Animation<TextureRegion>(1f / 15f, thornTextureAtlas.getRegions());
			flayerThorns.add(this);
			defineThorns();
			
		} catch (Exception e) {
			// Logs that this method of this class triggered an exception
			String name = Thread.currentThread().getStackTrace()[1].getMethodName();
			lfh.fileLog(this.getClass().getSimpleName() + " ", name + " ", "ERROR");
		}
	}

	public void defineThorns() {
		try {
			bdef.position.set(startX, startY);
			bdef.type = BodyDef.BodyType.DynamicBody;
			b2body = world.createBody(bdef);
			b2body.setUserData(this);
			FixtureDef fdef = new FixtureDef();
			CircleShape shape = new CircleShape();
			fdef.shape = shape;
			shape.setPosition(new Vector2(0, 1).scl(1 / Mutagen.PPM));
			shape.setRadius(2 / Mutagen.PPM);

			// Sets size of the physics bodies depending on the type of gun
			fdef.filter.categoryBits = Mutagen.ENEMY_BULLET; // identifies the category bit is
			fdef.filter.maskBits = Mutagen.WALL | Mutagen.PLAYER; // what masking bit the category bit collides with
			b2body.createFixture(fdef).setUserData("thorns");
			float shootingAngleRadians = (float) Math.toRadians(shootingAngle);
			// float angleVary = (int)(Math.random() * 40 - 20);
			// shootingAngleRadians = shootingAngleRadians + angleVary / Mutagen.PPM;

			shootingAngleRadians = shootingAngleRadians + 1.57f;
			posX = (float) (Math.cos(shootingAngleRadians)) * speed;
			posY = (float) (Math.sin(shootingAngleRadians)) * speed;

			b2body.setTransform(b2body.getPosition().x, b2body.getPosition().y, shootingAngleRadians); // sets the
			// position of
			// the body to
			// the position
			// of the body
			// and
			// implements
			// rotation
			b2body.applyLinearImpulse(posX, posY, b2body.getWorldCenter().x, b2body.getWorldCenter().y, true);
			
		} catch (Exception e) {
			// Logs that this method of this class triggered an exception
			String name = Thread.currentThread().getStackTrace()[1].getMethodName();
			lfh.fileLog(this.getClass().getSimpleName() + " ", name + " ", "ERROR");
		}
	}

	public void renderSprite(SpriteBatch batch) {
		try {
			batch.draw(thornAnimation.getKeyFrame(timePassed, true), b2body.getPosition().x, b2body.getPosition().y, 0,
					0, 9 / Mutagen.PPM, 20 / Mutagen.PPM, 1, 1, shootingAngle);
			timePassed += Gdx.graphics.getDeltaTime();
			
		} catch (Exception e) {
			// Logs that this method of this class triggered an exception
			String name = Thread.currentThread().getStackTrace()[1].getMethodName();
			lfh.fileLog(this.getClass().getSimpleName() + " ", name + " ", "ERROR");
		}

	}
}
