void controlp5Setup() {
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

void seedNumber(String theNumber) {
  mainSeed = int(theNumber);
  updateImg();
}

void imageScale(float theNumber) {
  imageScale = int(theNumber);
  updateImg();
  imageDimension = mainImage.width;
}

void headDistance(float theNumber) {
  headDistance = int(theNumber);
  updateImg();
}

void minusSeed() {
  mainSeed-=mainSeedInterval;
  updateImg();  
}

void plusSeed(){
  mainSeed+=mainSeedInterval;
  updateImg();
}

void saveImage(){
  saveIMG = true;
}