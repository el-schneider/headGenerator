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


color bgColor = color(0,0);
color bgLighting = color( 80, 80, 80);

color directLight = color(140,140,140);
color specMaterial = color(80,80,100);
color spotLight1 = color(155,0,0);
color spotLight2 = color(0,0,155);

ControlP5 cp5;