import processing.core.*; 
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 

import controlP5.*; 
import peasy.*; 

import java.util.HashMap; 
import java.util.ArrayList; 
import java.io.File; 
import java.io.BufferedReader; 
import java.io.PrintWriter; 
import java.io.InputStream; 
import java.io.OutputStream; 
import java.io.IOException; 

public class headGenerator extends PApplet {






public void setup () {
    

    imageDimension = imageScale * width;

    head3D = loadShape("neutral-head-2.obj");

    controlp5Setup();

    updateImg();
}

public void draw() {
    background(bgColor);
    image(mainImage, 0, 0, width, height);
    textAlign(CENTER, CENTER);
    text("" + imageDimension, 110, 50, 100, 20);

    if(saveIMG){
        mainImage.save("jpeg/g18-head "+ mainSeed +".png");
        saveIMG = false;
    }

}

public void mouseClicked() {

  // if (mouseButton == LEFT) {
  //   mainSeed+=mainSeedInterval;
  //   updateImg();
  // }

  // if (mouseButton == RIGHT) {
  //   mainSeed-=mainSeedInterval;
  //   updateImg();
  // }

  cp5.get(Textfield.class,"seedNumber").setValue(str(mainSeed));
}


// void keyPressed(){
//   if (key == 's') {
//       saveIMG = true;
//   }
// }
public void controlp5Setup() {
    PFont font = createFont("arial",20);

    cp5 = new ControlP5(this);

    println(mainSeed);

    cp5.addTextfield("seedNumber")
     .setPosition(10,10)
     .setSize(100,20)
    //  .setScrollSensitivity(1.1)
    //  .setValue(mainSeed)
     .setValue(str(mainSeed))
     ;

    cp5.addSlider("headDistance")
     .setPosition(10,50)
     .setSize(100,20)
     .setRange(800,1200)
     .setValue(headDistance)
     .setLabel("")
     ;

    cp5.addSlider("imageScale")
     .setPosition(10,80)
     .setSize(100,20)
     .setRange(1,5)
     .setNumberOfTickMarks(5)
     .setValue(4)
     .setLabel("")
     ;

    cp5.addButton("plusSeed")
     .setValue(0)
     .setPosition(120,10)
     .setSize(20,20)
     .setLabel("+");
     ;

    cp5.addButton("minusSeed")
     .setValue(0)
     .setPosition(150,10)
     .setSize(20,20)
     .setLabel("-");
     ;

    cp5.addButton("saveImage")
     .setValue(0)
     .setPosition(10,120)
     .setSize(100,20)
     .setLabel("Save Image");
     ;
}

public void seedNumber(String theNumber) {
  mainSeed = PApplet.parseInt(theNumber);
  updateImg();
}

public void imageScale(float theNumber) {
  imageScale = PApplet.parseInt(theNumber);
  updateImg();
  imageDimension = mainImage.width;
}

public void headDistance(float theNumber) {
  headDistance = PApplet.parseInt(theNumber);
  updateImg();
}

public void minusSeed() {
  mainSeed-=mainSeedInterval;
  updateImg();  
}

public void plusSeed(){
  mainSeed+=mainSeedInterval;
  updateImg();
}

public void saveImage(){
  saveIMG = true;
}
public void updateImg() {
    mainImage = drawBackground(width, height, mainSeed);
}


//----------------------------------------------------------------------------------------------
// DRAW BACKGROUND
//----------------------------------------------------------------------------------------------
public PImage drawBackground(int width_, int height_, int mainSeed_) {
    randomSeed(mainSeed_);
    noiseSeed(mainSeed_);

    PGraphics graph = createGraphics(width_ * imageScale, height_ * imageScale, P3D);
    PImage img = createImage(width_ * imageScale, height_ * imageScale, ARGB);

    graph.beginDraw();
    // graph.background(bgColor);

    PVector lightPos = new PVector( random( height ), random( width ) );

    graph.directionalLight( red( directLight ), green( directLight ), blue( directLight ), 0, 0, -1);
    graph.pointLight( red( spotLight1 ), green( spotLight1 ), blue( spotLight1 ), lightPos.x + random(400,800) * imageScale, lightPos.y, -1000 * imageScale);
    graph.pointLight( red( spotLight2 ), green( spotLight2 ), blue( spotLight2 ), lightPos.x - random(400,800) * imageScale, lightPos.y, -1000 * imageScale);
    graph.lightSpecular( red( specMaterial ), green( specMaterial ), blue( specMaterial ) );

    graph.beginCamera();
    graph.camera(width_/2.0f, -height/2, imageScale * headDistance, width_/2.0f, -height/2, 0, 0, 1, 0);
    graph.endCamera();


    graph.pushMatrix();
    graph.translate(width_/2, height_/2, 0 );
    graph.rotateZ(PI);
    graph.rotateY( random( -HALF_PI, HALF_PI ) );
    graph.rotateX( random( -QUARTER_PI, QUARTER_PI ) );
    graph.scale(imageScale);
    graph.shape(head3D, 0 , 0);

    graph.popMatrix();
    graph.endDraw();

    return img = graph.get();
}
//----------------------------------------------------------------------------------------------
// GENERAL
//----------------------------------------------------------------------------------------------
int mainSeed = 9999;
int mainSeedInterval = 100;

int imageScale = 4;

PImage mainImage;
PShape head3D;

boolean saveIMG = false;
int imageDimension;
float headDistance = 800;


int bgColor = color(0,0);
int bgLighting = color( 80, 80, 80);

int directLight = color(140,140,140);
int specMaterial = color(80,80,100);
int spotLight1 = color(155,0,0);
int spotLight2 = color(0,0,155);

ControlP5 cp5;
  public void settings() {  size( 800, 800, P3D ); }
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "headGenerator" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
