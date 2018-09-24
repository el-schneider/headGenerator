import controlP5.*;
import peasy.*;



void setup () {
    size( 800, 800, P3D );

    imageDimension = imageScale * width;

    head3D = loadShape("neutral-head-2.obj");

    controlp5Setup();

    updateImg();
}

void draw() {
    background(bgColor);
    image(mainImage, 0, 0, width, height);
    textAlign(CENTER, CENTER);
    text("" + imageDimension, 110, 50, 100, 20);

    if(saveIMG){
        mainImage.save("jpeg/g18-head "+ mainSeed + "-" + int(headDistance) + ".png");
        saveIMG = false;
    }

}

void mouseClicked() {

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