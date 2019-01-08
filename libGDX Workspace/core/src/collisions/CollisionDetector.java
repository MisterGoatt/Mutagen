package collisions;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.badlogic.gdx.utils.Array;

import BackEnd.Mutagen;
import entities.CreateBullet;
import entities.Grunt;
import entities.HealthPickUp;
import entities.PlayerOne;
import entities.PlayerTwo;
import entities.Scientist;
import entities.Soldier;
import entities.SoldierBullets;
import entities.Turret;
import screens.DifficultyScreen;
import screens.GunSelectionScreen;
import screens.PlayerMode;

public class CollisionDetector implements ContactListener{

	private Array<Body> tempBodyArray, bodiesToRemove;
	private Array<Grunt> gruntBodyTarget = new Array<Grunt>();
	private Array<Scientist> scientistBodyTarget = new Array<Scientist>();
	private Array<Turret> turretBodyTarget = new Array<Turret>();
	private Array<CreateBullet> bulletBodyTarget = new Array<CreateBullet>();
	private Array<Soldier> soldierBodyTarget = new Array<Soldier>();

	Grunt grunt;
	Scientist scientist;
	Turret turret;
	CreateBullet createBullet;
	Soldier soldier;
	PlayerOne p1;
	private Sound bulletHitWall, bulletBodyImpact, pelletHitWall, laserHitWall, turretHit, hpPickUp;

	public CollisionDetector() {	
		tempBodyArray= new Array<Body>();
		bodiesToRemove = new Array<Body>();
		bulletHitWall = Mutagen.manager.get("sound effects/impacts/bulletImpact.mp3");
		laserHitWall = Mutagen.manager.get("sound effects/impacts/laserImpact.mp3");
		pelletHitWall = Mutagen.manager.get("sound effects/impacts/pelletImpact.mp3");
		bulletBodyImpact = Mutagen.manager.get("sound effects/impacts/bulletBodyImpact.mp3");
		turretHit = Mutagen.manager.get("sound effects/enemies/turretHit.mp3");
		hpPickUp = Mutagen.manager.get("sound effects/hpPickUp.mp3");

		gruntBodyTarget.clear();
		scientistBodyTarget.clear();
		tempBodyArray.clear();
		bulletBodyTarget.clear();
		soldierBodyTarget.clear();
	}

	@Override
	public void beginContact(Contact contact) {
		Fixture fa = contact.getFixtureA();
		Fixture fb = contact.getFixtureB();
		if (fa == null || fb == null) return;
		if (fa.getUserData() == null || fb.getUserData() == null) return;

		//BULLET AND WALL COLLISIONS
		if (fa.getUserData().equals("bullets") || fb.getUserData().equals("bullets")) {
			if (fa.getUserData().equals("walls") || fb.getUserData().equals("walls")) {
				//FA
				if (fa.getUserData().equals("bullets")){
					if (GunSelectionScreen.p1WeaponSelected == "rifle" || GunSelectionScreen.p1WeaponSelected == "revolver" 
							|| GunSelectionScreen.p1WeaponSelected == "assault rifle" ) {
						if (Mutagen.sfxVolume != 0) {
							long bhwId = bulletHitWall.play(Mutagen.sfxVolume - .8f);
						}
					}
					else if (GunSelectionScreen.p1WeaponSelected == "laser") {

						if (Mutagen.sfxVolume != 0) {
							laserHitWall.play(Mutagen.sfxVolume);
						}
					}
					else if (GunSelectionScreen.p1WeaponSelected == "shotgun") {
						if (Mutagen.sfxVolume != 0) {
							long phwId = pelletHitWall.play(Mutagen.sfxVolume - .8f);
						}
					}

					if (GunSelectionScreen.p1WeaponSelected != "battle axe") {
						bodiesToRemove.add(fa.getBody());
					}

				}
				//FB
				if(fb.getUserData().equals("bullets")){
					if (GunSelectionScreen.p1WeaponSelected == "rifle" || GunSelectionScreen.p1WeaponSelected == "revolver" 
							|| GunSelectionScreen.p1WeaponSelected == "assault rifle" ) {
						if (Mutagen.sfxVolume != 0) {
							long bhwId = bulletHitWall.play(Mutagen.sfxVolume - .8f);
						}
					}
					else if (GunSelectionScreen.p1WeaponSelected == "laser") {
						if (Mutagen.sfxVolume != 0) {
							laserHitWall.play(Mutagen.sfxVolume);
						}
					}
					else if (GunSelectionScreen.p1WeaponSelected == "shotgun") {
						if (Mutagen.sfxVolume != 0) {
							long phwId = pelletHitWall.play(Mutagen.sfxVolume - .8f);
						}
					}
					if (GunSelectionScreen.p1WeaponSelected != "battle axe") {
						bodiesToRemove.add(fb.getBody());
					}

				}
			}
		}
		//BULLET AND WALL COLLISIONS
		if (fa.getUserData().equals("bullets2") || fb.getUserData().equals("bullets2")) {
			if (fa.getUserData().equals("walls") || fb.getUserData().equals("walls")) {
				//FA
				if (fa.getUserData().equals("bullets2")){
					if (GunSelectionScreen.p2WeaponSelected == "rifle" || GunSelectionScreen.p2WeaponSelected == "revolver" 
							|| GunSelectionScreen.p2WeaponSelected == "assault rifle" ) {
						if (Mutagen.sfxVolume != 0) {
							long bhwId = bulletHitWall.play(Mutagen.sfxVolume - .8f);
						}
					}
					else if (GunSelectionScreen.p2WeaponSelected == "laser") {

						if (Mutagen.sfxVolume != 0) {
							laserHitWall.play(Mutagen.sfxVolume);
						}
					}
					else if (GunSelectionScreen.p2WeaponSelected == "shotgun") {
						if (Mutagen.sfxVolume != 0) {
							long phwId = pelletHitWall.play(Mutagen.sfxVolume - .8f);
						}
					}

					if (GunSelectionScreen.p2WeaponSelected != "battle axe") {
						bodiesToRemove.add(fa.getBody());
					}

				}
				//FB
				if(fb.getUserData().equals("bullets2")){
					if (GunSelectionScreen.p2WeaponSelected == "rifle" || GunSelectionScreen.p2WeaponSelected == "revolver" 
							|| GunSelectionScreen.p2WeaponSelected == "assault rifle" ) {
						if (Mutagen.sfxVolume != 0) {
							long bhwId = bulletHitWall.play(Mutagen.sfxVolume - .8f);
						}
					}
					else if (GunSelectionScreen.p2WeaponSelected == "laser") {
						if (Mutagen.sfxVolume != 0) {
							laserHitWall.play(Mutagen.sfxVolume);
						}
					}
					else if (GunSelectionScreen.p2WeaponSelected == "shotgun") {
						if (Mutagen.sfxVolume != 0) {
							long phwId = pelletHitWall.play(Mutagen.sfxVolume - .8f);
						}
					}
					if (GunSelectionScreen.p2WeaponSelected != "battle axe") {
						bodiesToRemove.add(fb.getBody());
					}

				}
			}
		}
		//BULLET AND GRUNT COLLISIONS
		if (fa.getUserData().equals("bullets") || fb.getUserData().equals("bullets")) {
			if (fa.getUserData().equals("grunt") || fb.getUserData().equals("grunt")) {
				if (fa.getUserData().equals("bullets")){

					if (GunSelectionScreen.p1WeaponSelected != "battle axe") {
						bodiesToRemove.add(fa.getBody()); //bullet
					}
				}
				if(fb.getUserData().equals("bullets")){
					if (GunSelectionScreen.p1WeaponSelected != "battle axe") {	
						bodiesToRemove.add(fb.getBody()); //bullet
					}
				}				
				if (fa.getUserData().equals("grunt")){
					if (Mutagen.sfxVolume != 0) {
						long bBI = bulletBodyImpact.play(Mutagen.sfxVolume - .2f);
					}
					tempBodyArray.add(fa.getBody());
					Body b = tempBodyArray.first();
					gruntBodyTarget.add((Grunt) b.getUserData()); //casts Grunt on the physics body to get the class instance
					grunt = gruntBodyTarget.get(0);
					grunt.tookDamage = true;

					switch (GunSelectionScreen.p1WeaponSelected){
					case "battle axe": grunt.health -= PlayerOne.battleAxeDamage;
					break;
					case "revolver": grunt.health -= PlayerOne.revolverDamage;
					break;
					case "rifle": grunt.health -= PlayerOne.rifleDamage;
					break;	
					case "assault rifle": grunt.health -= PlayerOne.assaultRifleDamage;
					break;
					case "laser": grunt.health -= PlayerOne.laserLanceDamage;
					break;
					case "shotgun": grunt.health -= PlayerOne.shotgunDamage;
					break;
					default: break;
					}
					gruntBodyTarget.clear();
					tempBodyArray.clear();

					if (grunt.health <= 0) {
						bodiesToRemove.add(fa.getBody()); //grunt
						grunt.tookDamage = false;
					}
				}

				if(fb.getUserData().equals("grunt")){
					if (Mutagen.sfxVolume != 0) {
						long bBI = bulletBodyImpact.play(Mutagen.sfxVolume - .2f);
					}
					tempBodyArray.add(fb.getBody());
					Body b = tempBodyArray.first();
					gruntBodyTarget.add((Grunt) b.getUserData());
					grunt = gruntBodyTarget.get(0);
					grunt.tookDamage = true;

					switch (GunSelectionScreen.p1WeaponSelected){					
					case "battle axe": grunt.health -= PlayerOne.battleAxeDamage;
					break;
					case "revolver": grunt.health -= PlayerOne.revolverDamage;
					break;
					case "rifle": grunt.health -= PlayerOne.rifleDamage;
					break;	
					case "assault rifle": grunt.health -= PlayerOne.assaultRifleDamage;
					break;
					case "laser": grunt.health -= PlayerOne.laserLanceDamage;
					break;
					case "shotgun": grunt.health -= PlayerOne.shotgunDamage;
					break;
					default: break;
					}
					gruntBodyTarget.clear();
					tempBodyArray.clear();

					if (grunt.health <= 0) {
						bodiesToRemove.add(fb.getBody()); //grunt
						grunt.tookDamage = false;
					}
				}
			}
		}
		//GRUNT AND PLAYER COLLISIONS
		if (fa.getUserData().equals("player") || fb.getUserData().equals("player")) {
			if (fa.getUserData().equals("grunt") || fb.getUserData().equals("grunt")) {

				if(fa.getUserData().equals("grunt")){
					tempBodyArray.add(fa.getBody());
					Body b = tempBodyArray.first();
					gruntBodyTarget.add((Grunt) b.getUserData());
					grunt = gruntBodyTarget.get(0);
					grunt.contAtk = true;
					grunt.target = 1;
					gruntBodyTarget.clear();
					tempBodyArray.clear();
				}
				if(fb.getUserData().equals("grunt")){
					tempBodyArray.add(fb.getBody());
					Body b = tempBodyArray.first();
					gruntBodyTarget.add((Grunt) b.getUserData());
					grunt = gruntBodyTarget.get(0);
					grunt.contAtk = true;
					grunt.target = 1;
					gruntBodyTarget.clear();
					tempBodyArray.clear();
				}
			}
		}
		//SCIENTIST AND PLAYER COLLISIONS
		if (fa.getUserData().equals("player") || fb.getUserData().equals("player")) {
			if (fa.getUserData().equals("scientist") || fb.getUserData().equals("scientist")) {

				if(fa.getUserData().equals("scientist")){
					tempBodyArray.add(fa.getBody());
					Body b = tempBodyArray.first();
					scientistBodyTarget.add((Scientist) b.getUserData());
					scientist = scientistBodyTarget.get(0);
					scientist.contAtk = true;
					scientist.target = 1;
					scientistBodyTarget.clear();
					tempBodyArray.clear();
				}
				if(fb.getUserData().equals("scientist")){
					tempBodyArray.add(fb.getBody());
					Body b = tempBodyArray.first();
					scientistBodyTarget.add((Scientist) b.getUserData());
					scientist = scientistBodyTarget.get(0);
					scientist.contAtk = true;
					scientist.target = 1;
					scientistBodyTarget.clear();
					tempBodyArray.clear();
				}
			}
		}

		//BULLET AND GRUNT COLLISIONS
		if (fa.getUserData().equals("bullets") || fb.getUserData().equals("bullets")) {
			if (fa.getUserData().equals("scientist") || fb.getUserData().equals("scientist")) {
				if (fa.getUserData().equals("bullets")){

					if (GunSelectionScreen.p1WeaponSelected != "battle axe") {
						bodiesToRemove.add(fa.getBody()); //bullet

					}
				}
				if(fb.getUserData().equals("bullets")){
					if (GunSelectionScreen.p1WeaponSelected != "battle axe") {
						bodiesToRemove.add(fb.getBody()); //bullet
					}

				}				
				if (fa.getUserData().equals("scientist")){
					if (Mutagen.sfxVolume != 0) {
						long bBI = bulletBodyImpact.play(Mutagen.sfxVolume - .2f);
					}
					tempBodyArray.add(fa.getBody());
					Body b = tempBodyArray.first();
					scientistBodyTarget.add((Scientist) b.getUserData()); //casts Grunt on the physics body to get the class instance
					scientist = scientistBodyTarget.get(0);
					//not needed yet
					scientist.tookDamage = true;

					switch (GunSelectionScreen.p1WeaponSelected){
					case "battle axe": scientist.health -= PlayerOne.battleAxeDamage;
					break;
					case "revolver": scientist.health -= PlayerOne.revolverDamage;
					break;
					case "rifle": scientist.health -= PlayerOne.rifleDamage;
					break;	
					case "assault rifle": scientist.health -= PlayerOne.assaultRifleDamage;
					break;
					case "laser": scientist.health -= PlayerOne.laserLanceDamage;
					break;
					case "shotgun": scientist.health -= PlayerOne.shotgunDamage;
					break;
					default: break;
					}
					scientistBodyTarget.clear();
					tempBodyArray.clear();

					if (scientist.health <= 0) {
						bodiesToRemove.add(fa.getBody()); //grunt
						scientist.tookDamage = false;
					}
				}

				if(fb.getUserData().equals("scientist")){
					if (Mutagen.sfxVolume != 0) {
						long bBI = bulletBodyImpact.play(Mutagen.sfxVolume - .2f);
					}
					tempBodyArray.add(fb.getBody());
					Body b = tempBodyArray.first();
					scientistBodyTarget.add((Scientist) b.getUserData()); //casts Grunt on the physics body to get the class instance
					scientist = scientistBodyTarget.get(0);
					//not needed yet
					scientist.tookDamage = true;

					switch (GunSelectionScreen.p1WeaponSelected){
					case "battle axe": scientist.health -= PlayerOne.battleAxeDamage;
					break;
					case "revolver": scientist.health -= PlayerOne.revolverDamage;
					break;
					case "rifle": scientist.health -= PlayerOne.rifleDamage;
					break;	
					case "assault rifle": scientist.health -= PlayerOne.assaultRifleDamage;
					break;
					case "laser": scientist.health -= PlayerOne.laserLanceDamage;
					break;
					case "shotgun": scientist.health -= PlayerOne.shotgunDamage;
					break;
					default: break;
					}
					scientistBodyTarget.clear();
					tempBodyArray.clear();

					if (scientist.health <= 0) {
						bodiesToRemove.add(fb.getBody()); //grunt
						scientist.tookDamage = false;
					}
				}
			}
		}
		//BULLET AND TURRET COLLISIONS
		if (fa.getUserData().equals("bullets") || fb.getUserData().equals("bullets")) {
			if (fa.getUserData().equals("turret") || fb.getUserData().equals("turret")) {
				if (fa.getUserData().equals("bullets")){

					if (GunSelectionScreen.p1WeaponSelected != "battle axe") {
						bodiesToRemove.add(fa.getBody()); //7
					}
				}
				if(fb.getUserData().equals("bullets")){
					if (GunSelectionScreen.p1WeaponSelected != "battle axe") {
						bodiesToRemove.add(fb.getBody()); //bullet
					}
				}				
				if (fa.getUserData().equals("turret")){
					if (Mutagen.sfxVolume != 0) {
						long tH = turretHit.play(Mutagen.sfxVolume - .2f);
					}
					tempBodyArray.add(fa.getBody());
					Body b = tempBodyArray.first();
					turretBodyTarget.add((Turret) b.getUserData()); //casts Grunt on the physics body to get the class instance
					turret = turretBodyTarget.get(0);
					//not needed yet
					//scientist.tookDamage = true;

					switch (GunSelectionScreen.p1WeaponSelected){
					case "battle axe": turret.health -= PlayerOne.battleAxeDamage;
					break;
					case "revolver": turret.health -= PlayerOne.revolverDamage;
					break;
					case "rifle": turret.health -= PlayerOne.rifleDamage;
					break;	
					case "assault rifle": turret.health -= PlayerOne.assaultRifleDamage;
					break;
					case "laser": turret.health -= PlayerOne.laserLanceDamage;
					break;
					case "shotgun": turret.health -= PlayerOne.shotgunDamage;
					break;
					default: break;
					}
					turretBodyTarget.clear();
					tempBodyArray.clear();

					if (turret.health <= 0) {
						bodiesToRemove.add(fa.getBody()); //grunt
						//turret.tookDamage = false;
					}
				}
				if (fb.getUserData().equals("turret")){
					if (Mutagen.sfxVolume != 0) {
						long tH = turretHit.play(Mutagen.sfxVolume - .2f);
					}
					tempBodyArray.add(fb.getBody());
					Body b = tempBodyArray.first();
					turretBodyTarget.add((Turret) b.getUserData()); //casts Grunt on the physics body to get the class instance
					turret = turretBodyTarget.get(0);
					//not needed yet
					//scientist.tookDamage = true;

					switch (GunSelectionScreen.p1WeaponSelected){
					case "battle axe": turret.health -= PlayerOne.battleAxeDamage;
					break;
					case "revolver": turret.health -= PlayerOne.revolverDamage;
					break;
					case "rifle": turret.health -= PlayerOne.rifleDamage;
					break;	
					case "assault rifle": turret.health -= PlayerOne.assaultRifleDamage;
					break;
					case "laser": turret.health -= PlayerOne.laserLanceDamage;
					break;
					case "shotgun": turret.health -= PlayerOne.shotgunDamage;
					break;
					default: break;
					}
					turretBodyTarget.clear();
					tempBodyArray.clear();

					if (turret.health <= 0) {
						bodiesToRemove.add(fb.getBody()); //grunt
						//turret.tookDamage = false;
					}
				}
			}
		}

		//TURRET BULLETS AND WALL COLLISIONS
		if (fa.getUserData().equals("turret bullets") || fb.getUserData().equals("turret bullets")) {
			if (fa.getUserData().equals("walls") || fb.getUserData().equals("walls")) {
				//FA
				if (fa.getUserData().equals("turret bullets")){
					if (Mutagen.sfxVolume != 0) {
						long bhwId = bulletHitWall.play(Mutagen.sfxVolume - .8f);
					}
					bodiesToRemove.add(fa.getBody());
				}
				//FB
				if(fb.getUserData().equals("turret bullets")){
					if (Mutagen.sfxVolume != 0) {
						long bhwId = bulletHitWall.play(Mutagen.sfxVolume - .8f);
					}
					bodiesToRemove.add(fb.getBody());
				}
			}
		}
		//TURRET BULLETS AND PLAYER COLLISIONS
		if (fa.getUserData().equals("player") || fb.getUserData().equals("player")) {
			if (fa.getUserData().equals("turret bullets") || fb.getUserData().equals("turret bullets")) {

				if(fa.getUserData().equals("turret bullets")){

					PlayerOne.player1HP -= Turret.atkDmg;
					bodiesToRemove.add(fa.getBody()); 

				}
				if(fb.getUserData().equals("turret bullets")){
					PlayerOne.player1HP -= Turret.atkDmg;
					bodiesToRemove.add(fb.getBody());
				}
			}
		}

		//HP Pickup AND PLAYER COLLISIONS
		if (fa.getUserData().equals("player") || fb.getUserData().equals("player")) {
			if (fa.getUserData().equals("hpPickUp") || fb.getUserData().equals("hpPickUp")) {

				if(fa.getUserData().equals("hpPickUp")){
					bodiesToRemove.add(fa.getBody());
				}
				if(fb.getUserData().equals("hpPickUp")){
					bodiesToRemove.add(fb.getBody());	
				}
				if(fa.getUserData().equals("player")){

					long hpPU = hpPickUp.play(Mutagen.sfxVolume);
					if (DifficultyScreen.difficulty == 1) {
						PlayerOne.player1HP = PlayerOne.player1MaxHP1;
					}else {
						PlayerOne.player1HP = PlayerOne.player1MaxHP2;
					}
				}
				if(fb.getUserData().equals("player")){

					long hpPU = hpPickUp.play(Mutagen.sfxVolume);
					if (DifficultyScreen.difficulty == 1) {
						PlayerOne.player1HP = PlayerOne.player1MaxHP1;
					}else {
						PlayerOne.player1HP = PlayerOne.player1MaxHP2;
					}
				}
			}
		}
		//BULLET AND SOLDIER COLLISIONS
		if (fa.getUserData().equals("bullets") || fb.getUserData().equals("bullets")) {
			if (fa.getUserData().equals("soldier") || fb.getUserData().equals("soldier")) {
				if (fa.getUserData().equals("bullets")){

					if (GunSelectionScreen.p1WeaponSelected != "battle axe") {
						bodiesToRemove.add(fa.getBody()); //bullet
					}
				}
				if(fb.getUserData().equals("bullets")){
					if (GunSelectionScreen.p1WeaponSelected != "battle axe") {
						bodiesToRemove.add(fb.getBody()); //bullet
					}
				}				
				if (fa.getUserData().equals("soldier")){

					tempBodyArray.add(fa.getBody());
					Body b = tempBodyArray.first();
					soldierBodyTarget.add((Soldier) b.getUserData()); //casts Grunt on the physics body to get the class instance
					soldier = soldierBodyTarget.get(0);
					//not needed yet
					//scientist.tookDamage = true;
					if (Mutagen.sfxVolume != 0) {
						long bBI = bulletBodyImpact.play(Mutagen.sfxVolume - .2f);
					}
					switch (GunSelectionScreen.p1WeaponSelected){
					case "battle axe": soldier.health -= PlayerOne.battleAxeDamage;
					break;
					case "revolver": soldier.health -= PlayerOne.revolverDamage;
					break;
					case "rifle": soldier.health -= PlayerOne.rifleDamage;
					break;	
					case "assault rifle": soldier.health -= PlayerOne.assaultRifleDamage;
					break;
					case "laser": soldier.health -= PlayerOne.laserLanceDamage;
					break;
					case "shotgun": soldier.health -= PlayerOne.shotgunDamage;
					break;
					default: break;
					}
					turretBodyTarget.clear();
					tempBodyArray.clear();

					if (soldier.health <= 0) {
						bodiesToRemove.add(fa.getBody()); //grunt
						//turret.tookDamage = false;
					}
				}
				if (fb.getUserData().equals("soldier")){
					tempBodyArray.add(fb.getBody());
					Body b = tempBodyArray.first();
					soldierBodyTarget.add((Soldier) b.getUserData()); //casts Grunt on the physics body to get the class instance
					soldier = soldierBodyTarget.get(0);
					//not needed yet
					//scientist.tookDamage = true;
					if (Mutagen.sfxVolume != 0) {
						long bBI = bulletBodyImpact.play(Mutagen.sfxVolume - .2f);
					}
					
					switch (GunSelectionScreen.p1WeaponSelected){
					case "battle axe": soldier.health -= PlayerOne.battleAxeDamage;
					break;
					case "revolver": soldier.health -= PlayerOne.revolverDamage;
					break;
					case "rifle": soldier.health -= PlayerOne.rifleDamage;
					break;	
					case "assault rifle": soldier.health -= PlayerOne.assaultRifleDamage;
					break;
					case "laser": soldier.health -= PlayerOne.laserLanceDamage;
					break;
					case "shotgun": soldier.health -= PlayerOne.shotgunDamage;
					break;
					default: break;
					}
					soldierBodyTarget.clear();
					tempBodyArray.clear();

					if (soldier.health <= 0) {
						bodiesToRemove.add(fb.getBody()); //grunt
						//turret.tookDamage = false;
					}
				}

			}
		}
		//SOLDIER BULLETS AND PLAYER COLLISIONS
		if (fa.getUserData().equals("player") || fb.getUserData().equals("player")) {
			if (fa.getUserData().equals("soldier bullets") || fb.getUserData().equals("soldier bullets")) {

				if(fa.getUserData().equals("soldier bullets")){
					PlayerOne.player1HP -= Soldier.atkDmg;
					bodiesToRemove.add(fa.getBody()); 
				}
				if(fb.getUserData().equals("soldier bullets")){
					PlayerOne.player1HP -= Soldier.atkDmg;
					bodiesToRemove.add(fb.getBody());
				}
			}
		}
		//SOLDIER & BULLETS AND WALL
		if (fa.getUserData().equals("walls") || fb.getUserData().equals("walls")) {
			if (fa.getUserData().equals("soldier bullets") || fb.getUserData().equals("soldier bullets")) {

				if(fa.getUserData().equals("soldier bullets")){
					if (Mutagen.sfxVolume != 0) {
						long phwId = pelletHitWall.play(Mutagen.sfxVolume - .8f);
					}
					bodiesToRemove.add(fa.getBody()); 
				}
				if(fb.getUserData().equals("soldier bullets")){
					if (Mutagen.sfxVolume != 0) {
						long phwId = pelletHitWall.play(Mutagen.sfxVolume - .8f);
					}
					bodiesToRemove.add(fb.getBody());
				}
			}
		}
		//GRUNT AND PLAYER 2 COLLISIONS
		if (fa.getUserData().equals("player2") || fb.getUserData().equals("player2")) {
			if (fa.getUserData().equals("grunt") || fb.getUserData().equals("grunt")) {

				if(fa.getUserData().equals("grunt")){
					tempBodyArray.add(fa.getBody());
					Body b = tempBodyArray.first();
					gruntBodyTarget.add((Grunt) b.getUserData());
					grunt = gruntBodyTarget.get(0);
					grunt.contAtk = true;
					grunt.target = 2;
					gruntBodyTarget.clear();
					tempBodyArray.clear();
				}
				if(fb.getUserData().equals("grunt")){
					tempBodyArray.add(fb.getBody());
					Body b = tempBodyArray.first();
					gruntBodyTarget.add((Grunt) b.getUserData());
					grunt = gruntBodyTarget.get(0);
					grunt.contAtk = true;
					grunt.target = 2;
					gruntBodyTarget.clear();
					tempBodyArray.clear();
				}
			}
		}
		//SCIENTIST AND PLAYER2 COLLISIONS
		if (fa.getUserData().equals("player2") || fb.getUserData().equals("player2")) {
			if (fa.getUserData().equals("scientist") || fb.getUserData().equals("scientist")) {

				if(fa.getUserData().equals("scientist")){
					tempBodyArray.add(fa.getBody());
					Body b = tempBodyArray.first();
					scientistBodyTarget.add((Scientist) b.getUserData());
					scientist = scientistBodyTarget.get(0);
					scientist.contAtk = true;
					scientist.target = 2;
					scientistBodyTarget.clear();
					tempBodyArray.clear();
				}
				if(fb.getUserData().equals("scientist")){
					tempBodyArray.add(fb.getBody());
					Body b = tempBodyArray.first();
					scientistBodyTarget.add((Scientist) b.getUserData());
					scientist = scientistBodyTarget.get(0);
					scientist.contAtk = true;
					scientist.target = 2;
					scientistBodyTarget.clear();
					tempBodyArray.clear();
				}
			}
		}
		//HP Pickup AND PLAYER COLLISIONS
		if (fa.getUserData().equals("player2") || fb.getUserData().equals("player2")) {
			if (fa.getUserData().equals("hpPickUp") || fb.getUserData().equals("hpPickUp")) {

				if(fa.getUserData().equals("hpPickUp")){
					bodiesToRemove.add(fa.getBody());
				}
				if(fb.getUserData().equals("hpPickUp")){
					bodiesToRemove.add(fb.getBody());	
				}
				if(fa.getUserData().equals("player2")){

					long hpPU = hpPickUp.play(Mutagen.sfxVolume);
					if (DifficultyScreen.difficulty == 1) {
						PlayerTwo.player2HP = PlayerTwo.player2MaxHP1;
					}else {
						PlayerTwo.player2HP = PlayerTwo.player2MaxHP2;
					}
				}
				if(fb.getUserData().equals("player2")){

					long hpPU = hpPickUp.play(Mutagen.sfxVolume);
					if (DifficultyScreen.difficulty == 1) {
						PlayerTwo.player2HP = PlayerTwo.player2MaxHP1;
					}else {
						PlayerTwo.player2HP = PlayerTwo.player2MaxHP2;
					}
				}
			}
		}
		//SOLDIER BULLETS AND PLAYER COLLISIONS
		if (fa.getUserData().equals("player2") || fb.getUserData().equals("player2")) {
			if (fa.getUserData().equals("soldier bullets") || fb.getUserData().equals("soldier bullets")) {

				if(fa.getUserData().equals("soldier bullets")){
					PlayerTwo.player2HP -= Soldier.atkDmg;
					bodiesToRemove.add(fa.getBody()); 
				}
				if(fb.getUserData().equals("soldier bullets")){
					PlayerTwo.player2HP -= Soldier.atkDmg;
					bodiesToRemove.add(fb.getBody());
				}
			}
		}
		//TURRET BULLETS AND PLAYER COLLISIONS
		if (fa.getUserData().equals("player2") || fb.getUserData().equals("player2")) {
			if (fa.getUserData().equals("turret bullets") || fb.getUserData().equals("turret bullets")) {

				if(fa.getUserData().equals("turret bullets")){

					PlayerTwo.player2HP -= Turret.atkDmg;
					bodiesToRemove.add(fa.getBody()); 

				}
				if(fb.getUserData().equals("turret bullets")){
					PlayerTwo.player2HP -= Turret.atkDmg;
					bodiesToRemove.add(fb.getBody());
				}
			}
		}

		//P2's BULLETS AND SOLDIER COLLISIONS
		//BULLET AND GRUNT COLLISIONS
		if (fa.getUserData().equals("bullets2") || fb.getUserData().equals("bullets2")) {
			if (fa.getUserData().equals("scientist") || fb.getUserData().equals("scientist")) {
				if (fa.getUserData().equals("bullets2")){

					if (GunSelectionScreen.p2WeaponSelected != "battle axe") {
						bodiesToRemove.add(fa.getBody()); //bullet

					}
				}
				if(fb.getUserData().equals("bullets2")){
					if (GunSelectionScreen.p2WeaponSelected != "battle axe") {
						bodiesToRemove.add(fb.getBody()); //bullet
					}

				}				
				if (fa.getUserData().equals("scientist")){
					if (Mutagen.sfxVolume != 0) {
						long bBI = bulletBodyImpact.play(Mutagen.sfxVolume - .2f);
					}
					tempBodyArray.add(fa.getBody());
					Body b = tempBodyArray.first();
					scientistBodyTarget.add((Scientist) b.getUserData()); //casts Grunt on the physics body to get the class instance
					scientist = scientistBodyTarget.get(0);
					//not needed yet
					scientist.tookDamage = true;

					switch (GunSelectionScreen.p2WeaponSelected){
					case "battle axe": scientist.health -= PlayerTwo.battleAxeDamage;
					break;
					case "revolver": scientist.health -= PlayerTwo.revolverDamage;
					break;
					case "rifle": scientist.health -= PlayerTwo.rifleDamage;
					break;	
					case "assault rifle": scientist.health -= PlayerTwo.assaultRifleDamage;
					break;
					case "laser": scientist.health -= PlayerTwo.laserLanceDamage;
					break;
					case "shotgun": scientist.health -= PlayerTwo.shotgunDamage;
					break;
					default: break;
					}
					scientistBodyTarget.clear();
					tempBodyArray.clear();

					if (scientist.health <= 0) {
						bodiesToRemove.add(fa.getBody()); //grunt
						scientist.tookDamage = false;
					}
				}

				if(fb.getUserData().equals("scientist")){
					if (Mutagen.sfxVolume != 0) {
						long bBI = bulletBodyImpact.play(Mutagen.sfxVolume - .2f);
					}
					tempBodyArray.add(fb.getBody());
					Body b = tempBodyArray.first();
					scientistBodyTarget.add((Scientist) b.getUserData()); //casts Grunt on the physics body to get the class instance
					scientist = scientistBodyTarget.get(0);
					//not needed yet
					scientist.tookDamage = true;

					switch (GunSelectionScreen.p2WeaponSelected){
					case "battle axe": scientist.health -= PlayerTwo.battleAxeDamage;
					break;
					case "revolver": scientist.health -= PlayerTwo.revolverDamage;
					break;
					case "rifle": scientist.health -= PlayerTwo.rifleDamage;
					break;	
					case "assault rifle": scientist.health -= PlayerTwo.assaultRifleDamage;
					break;
					case "laser": scientist.health -= PlayerTwo.laserLanceDamage;
					break;
					case "shotgun": scientist.health -= PlayerTwo.shotgunDamage;
					break;
					default: break;
					}
					scientistBodyTarget.clear();
					tempBodyArray.clear();

					if (scientist.health <= 0) {
						bodiesToRemove.add(fb.getBody()); //grunt
						scientist.tookDamage = false;
					}
				}
			}
		}

		//P2's BULLET AND SOLDIER COLLISIONS
		if (fa.getUserData().equals("bullets2") || fb.getUserData().equals("bullets2")) {
			if (fa.getUserData().equals("soldier") || fb.getUserData().equals("soldier")) {
				if (fa.getUserData().equals("bullets2")){

					if (GunSelectionScreen.p2WeaponSelected != "battle axe") {
						bodiesToRemove.add(fa.getBody()); //bullet
					}
				}
				if(fb.getUserData().equals("bullets2")){
					if (GunSelectionScreen.p2WeaponSelected != "battle axe") {
						bodiesToRemove.add(fb.getBody()); //bullet
					}
				}				
				if (fa.getUserData().equals("soldier")){

					tempBodyArray.add(fa.getBody());
					Body b = tempBodyArray.first();
					soldierBodyTarget.add((Soldier) b.getUserData()); 
					soldier = soldierBodyTarget.get(0);
					//not needed yet
					//scientist.tookDamage = true;

					switch (GunSelectionScreen.p2WeaponSelected){
					case "battle axe": soldier.health -= PlayerTwo.battleAxeDamage;
					break;
					case "revolver": soldier.health -= PlayerTwo.revolverDamage;
					break;
					case "rifle": soldier.health -= PlayerTwo.rifleDamage;
					break;	
					case "assault rifle": soldier.health -= PlayerTwo.assaultRifleDamage;
					break;
					case "laser": soldier.health -= PlayerTwo.laserLanceDamage;
					break;
					case "shotgun": soldier.health -= PlayerTwo.shotgunDamage;
					break;
					default: break;
					}
					turretBodyTarget.clear();
					tempBodyArray.clear();
					if (soldier.health <= 0) {
						bodiesToRemove.add(fa.getBody());
						//turret.tookDamage = false;
					}
				}
				if (fb.getUserData().equals("soldier")){

					tempBodyArray.add(fb.getBody());
					Body b = tempBodyArray.first();
					soldierBodyTarget.add((Soldier) b.getUserData()); 
					soldier = soldierBodyTarget.get(0);
					//not needed yet
					//scientist.tookDamage = true;

					switch (GunSelectionScreen.p2WeaponSelected){
					case "battle axe": soldier.health -= PlayerTwo.battleAxeDamage;
					break;
					case "revolver": soldier.health -= PlayerTwo.revolverDamage;
					break;
					case "rifle": soldier.health -= PlayerTwo.rifleDamage;
					break;	
					case "assault rifle": soldier.health -= PlayerTwo.assaultRifleDamage;
					break;
					case "laser": soldier.health -= PlayerTwo.laserLanceDamage;
					break;
					case "shotgun": soldier.health -= PlayerTwo.shotgunDamage;
					break;
					default: break;
					}
					soldierBodyTarget.clear();
					tempBodyArray.clear();
					System.out.println("here");

					if (soldier.health <= 0) {
						bodiesToRemove.add(fb.getBody());
					}
				}

			}
		}
		//P2 BULLETS AND GRUNTS
		if (fa.getUserData().equals("bullets2") || fb.getUserData().equals("bullets2")) {
			if (fa.getUserData().equals("grunt") || fb.getUserData().equals("grunt")) {
				if (fa.getUserData().equals("bullets2")){

					if (GunSelectionScreen.p2WeaponSelected != "battle axe") {
						bodiesToRemove.add(fa.getBody()); //bullet
					}
				}
				if(fb.getUserData().equals("bullets2")){
					if (GunSelectionScreen.p2WeaponSelected != "battle axe") {	
						bodiesToRemove.add(fb.getBody()); //bullet
					}
				}				
				if (fa.getUserData().equals("grunt")){
					if (Mutagen.sfxVolume != 0) {
						long bBI = bulletBodyImpact.play(Mutagen.sfxVolume - .2f);
					}
					tempBodyArray.add(fa.getBody());
					Body b = tempBodyArray.first();
					gruntBodyTarget.add((Grunt) b.getUserData()); //casts Grunt on the physics body to get the class instance
					grunt = gruntBodyTarget.get(0);
					grunt.tookDamage = true;

					switch (GunSelectionScreen.p2WeaponSelected){
					case "battle axe": grunt.health -= PlayerTwo.battleAxeDamage;
					break;
					case "revolver": grunt.health -= PlayerTwo.revolverDamage;
					break;
					case "rifle": grunt.health -= PlayerTwo.rifleDamage;
					break;	
					case "assault rifle": grunt.health -= PlayerTwo.assaultRifleDamage;
					break;
					case "laser": grunt.health -= PlayerTwo.laserLanceDamage;
					break;
					case "shotgun": grunt.health -= PlayerTwo.shotgunDamage;
					break;
					default: break;
					}
					gruntBodyTarget.clear();
					tempBodyArray.clear();

					if (grunt.health <= 0) {
						bodiesToRemove.add(fa.getBody()); //grunt
						grunt.tookDamage = false;
					}
				}

				if(fb.getUserData().equals("grunt")){
					if (Mutagen.sfxVolume != 0) {
						long bBI = bulletBodyImpact.play(Mutagen.sfxVolume - .2f);
					}
					tempBodyArray.add(fb.getBody());
					Body b = tempBodyArray.first();
					gruntBodyTarget.add((Grunt) b.getUserData());
					grunt = gruntBodyTarget.get(0);
					grunt.tookDamage = true;

					switch (GunSelectionScreen.p2WeaponSelected){					
					case "battle axe": grunt.health -= PlayerTwo.battleAxeDamage;
					break;
					case "revolver": grunt.health -= PlayerTwo.revolverDamage;
					break;
					case "rifle": grunt.health -= PlayerTwo.rifleDamage;
					break;	
					case "assault rifle": grunt.health -= PlayerTwo.assaultRifleDamage;
					break;
					case "laser": grunt.health -= PlayerTwo.laserLanceDamage;
					break;
					case "shotgun": grunt.health -= PlayerTwo.shotgunDamage;
					break;
					default: break;
					}
					gruntBodyTarget.clear();
					tempBodyArray.clear();

					if (grunt.health <= 0) {
						bodiesToRemove.add(fb.getBody()); //grunt
						grunt.tookDamage = false;
					}
				}
			}
		}
		//BULLET P2 AND TURRET COLLISIONS
		if (fa.getUserData().equals("bullets2") || fb.getUserData().equals("bullets2")) {
			if (fa.getUserData().equals("turret") || fb.getUserData().equals("turret")) {
				if (fa.getUserData().equals("bullets2")){

					if (GunSelectionScreen.p2WeaponSelected != "battle axe") {
						bodiesToRemove.add(fa.getBody()); //bullet
					}
				}
				if(fb.getUserData().equals("bullets2")){
					if (GunSelectionScreen.p2WeaponSelected != "battle axe") {
						bodiesToRemove.add(fb.getBody()); //bullet
					}
				}				
				if (fa.getUserData().equals("turret")){
					if (Mutagen.sfxVolume != 0) {
						long tH = turretHit.play(Mutagen.sfxVolume - .2f);
					}
					tempBodyArray.add(fa.getBody());
					Body b = tempBodyArray.first();
					turretBodyTarget.add((Turret) b.getUserData()); //casts Grunt on the physics body to get the class instance
					turret = turretBodyTarget.get(0);
					//not needed yet
					//scientist.tookDamage = true;

					switch (GunSelectionScreen.p2WeaponSelected){
					case "battle axe": turret.health -= PlayerTwo.battleAxeDamage;
					break;
					case "revolver": turret.health -= PlayerTwo.revolverDamage;
					break;
					case "rifle": turret.health -= PlayerTwo.rifleDamage;
					break;	
					case "assault rifle": turret.health -= PlayerTwo.assaultRifleDamage;
					break;
					case "laser": turret.health -= PlayerTwo.laserLanceDamage;
					break;
					case "shotgun": turret.health -= PlayerTwo.shotgunDamage;
					break;
					default: break;
					}
					turretBodyTarget.clear();
					tempBodyArray.clear();

					if (turret.health <= 0) {
						bodiesToRemove.add(fa.getBody()); //grunt
						//turret.tookDamage = false;
					}
				}
				if (fb.getUserData().equals("turret")){
					if (Mutagen.sfxVolume != 0) {
						long tH = turretHit.play(Mutagen.sfxVolume - .2f);
					}
					tempBodyArray.add(fb.getBody());
					Body b = tempBodyArray.first();
					turretBodyTarget.add((Turret) b.getUserData()); //casts Grunt on the physics body to get the class instance
					turret = turretBodyTarget.get(0);
					//not needed yet
					//scientist.tookDamage = true;

					switch (GunSelectionScreen.p1WeaponSelected){
					case "battle axe": turret.health -= PlayerTwo.battleAxeDamage;
					break;
					case "revolver": turret.health -= PlayerTwo.revolverDamage;
					break;
					case "rifle": turret.health -= PlayerTwo.rifleDamage;
					break;	
					case "assault rifle": turret.health -= PlayerTwo.assaultRifleDamage;
					break;
					case "laser": turret.health -= PlayerTwo.laserLanceDamage;
					break;
					case "shotgun": turret.health -= PlayerTwo.shotgunDamage;
					break;
					default: break;
					}
					turretBodyTarget.clear();
					tempBodyArray.clear();

					if (turret.health <= 0) {
						bodiesToRemove.add(fb.getBody()); //grunt
						//turret.tookDamage = false;
					}
				}
			}
		}
	}


	public Array <Body> getbodiesToRemove(){
		return bodiesToRemove;
	}		
	// *******************************************************END CONTACT********************************************************

	@Override
	public void endContact(Contact contact) {
		Fixture fa = contact.getFixtureA();
		Fixture fb = contact.getFixtureB();
		if (fa == null || fb == null) return;
		if (fa.getUserData() == null || fb.getUserData() == null) return;

		//GRUNT AND PLAYER COLLISIONS
		if (fa.getUserData().equals("player") || fb.getUserData().equals("player")) {
			if (fa.getUserData().equals("grunt") || fb.getUserData().equals("grunt")) {

				if(fa.getUserData().equals("grunt")){
					tempBodyArray.add(fa.getBody());
					Body b = tempBodyArray.first();
					gruntBodyTarget.add((Grunt) b.getUserData());
					grunt = gruntBodyTarget.get(0);
					grunt.contAtk = false;
					gruntBodyTarget.clear();
					tempBodyArray.clear();
				}

				if(fb.getUserData().equals("grunt")){
					tempBodyArray.add(fb.getBody());
					Body b = tempBodyArray.first();
					gruntBodyTarget.add((Grunt) b.getUserData());
					grunt = gruntBodyTarget.get(0);
					grunt.contAtk = false;
					gruntBodyTarget.clear();
					tempBodyArray.clear();
				}
			}
		}
		//SCIENTIST AND PLAYER COLLISIONS
		if (fa.getUserData().equals("player") || fb.getUserData().equals("player")) {
			if (fa.getUserData().equals("scientist") || fb.getUserData().equals("scientist")) {
				if(fa.getUserData().equals("scientist")){
					tempBodyArray.add(fa.getBody());
					Body b = tempBodyArray.first();
					scientistBodyTarget.add((Scientist) b.getUserData());
					scientist = scientistBodyTarget.get(0);
					scientist.contAtk = false;
					scientistBodyTarget.clear();
					tempBodyArray.clear();
				}

				if(fb.getUserData().equals("scientist")){
					tempBodyArray.add(fb.getBody());
					Body b = tempBodyArray.first();
					scientistBodyTarget.add((Scientist) b.getUserData());
					scientist = scientistBodyTarget.get(0);
					scientist.contAtk = false;
					scientistBodyTarget.clear();
					tempBodyArray.clear();
				}
			}
		}
		//GRUNT AND PLAYER2 COLLISIONS
		if (fa.getUserData().equals("player2") || fb.getUserData().equals("player2")) {
			if (fa.getUserData().equals("grunt") || fb.getUserData().equals("grunt")) {

				if(fa.getUserData().equals("grunt")){
					tempBodyArray.add(fa.getBody());
					Body b = tempBodyArray.first();
					gruntBodyTarget.add((Grunt) b.getUserData());
					grunt = gruntBodyTarget.get(0);
					grunt.contAtk = false;
					gruntBodyTarget.clear();
					tempBodyArray.clear();
				}

				if(fb.getUserData().equals("grunt")){
					tempBodyArray.add(fb.getBody());
					Body b = tempBodyArray.first();
					gruntBodyTarget.add((Grunt) b.getUserData());
					grunt = gruntBodyTarget.get(0);
					grunt.contAtk = false;
					gruntBodyTarget.clear();
					tempBodyArray.clear();
				}
			}
		}
		//SCIENTIST AND PLAYER2 COLLISIONS
		if (fa.getUserData().equals("player2") || fb.getUserData().equals("player2")) {
			if (fa.getUserData().equals("scientist") || fb.getUserData().equals("scientist")) {
				if(fa.getUserData().equals("scientist")){
					tempBodyArray.add(fa.getBody());
					Body b = tempBodyArray.first();
					scientistBodyTarget.add((Scientist) b.getUserData());
					scientist = scientistBodyTarget.get(0);
					scientist.contAtk = false;
					scientistBodyTarget.clear();
					tempBodyArray.clear();
				}

				if(fb.getUserData().equals("scientist")){
					tempBodyArray.add(fb.getBody());
					Body b = tempBodyArray.first();
					scientistBodyTarget.add((Scientist) b.getUserData());
					scientist = scientistBodyTarget.get(0);
					scientist.contAtk = false;
					scientistBodyTarget.clear();
					tempBodyArray.clear();
				}
			}
		}
	}

	@Override
	public void preSolve(Contact contact, Manifold oldManifold) {
		// TODO Auto-generated method stub

	}

	@Override
	public void postSolve(Contact contact, ContactImpulse impulse) {
		// TODO Auto-generated method stub
	}
}
