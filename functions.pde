void updateImg() {
    mainImage = drawBackground(width, height, mainSeed);
}


//----------------------------------------------------------------------------------------------
// DRAW BACKGROUND
//----------------------------------------------------------------------------------------------
PImage drawBackground(int width_, int height_, int mainSeed_) {
    randomSeed(mainSeed_);
    noiseSeed(mainSeed_);

    PGraphics graph = createGraphics(width_ * imageScale, height_ * imageScale, P3D);
    PImage img = createImage(width_ * imageScale, height_ * imageScale, ARGB);

    graph.beginDraw();

    PVector lightPos = new PVector( random( height ), random( width ) );

    graph.directionalLight( red( directLight ), green( directLight ), blue( directLight ), 0, 0, -1);
    graph.pointLight( red( spotLight1 ), green( spotLight1 ), blue( spotLight1 ), lightPos.x + random(400,800) * imageScale, lightPos.y, -1000 * imageScale);
    graph.pointLight( red( spotLight2 ), green( spotLight2 ), blue( spotLight2 ), lightPos.x - random(400,800) * imageScale, lightPos.y, -1000 * imageScale);
    graph.lightSpecular( red( specMaterial ), green( specMaterial ), blue( specMaterial ) );

    graph.beginCamera();
    graph.camera(width_/2.0, -height/2, imageScale * headDistance, width_/2.0, -height/2, 0, 0, 1, 0);
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