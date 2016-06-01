import processing.core.*; 
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 

import java.util.List; 

import java.util.HashMap; 
import java.util.ArrayList; 
import java.io.File; 
import java.io.BufferedReader; 
import java.io.PrintWriter; 
import java.io.InputStream; 
import java.io.OutputStream; 
import java.io.IOException; 

public class Cell extends PApplet {

final float velSizeT=55;
final float maxLifeT =3;
final float lifeT = 0.7f;
final float foodRegardT = 80;
final int mutationT = 30;
//final int startingCellsT = 2;


List<MyCell>cells=new ArrayList();
//MyCell[] cells;
// MyCell(PVector _pos, float _size, float _divRate, float _life, color _cellColor) {
List<Food>food=new ArrayList();
public void setup() {
  
  //cells= new MyCell[startingCellsT];
  for (int i=0; i<20; i++) {
    food.add(new Food(new PVector(random(1200), random(650)), color(0)));
  }
  for (int i=0; i<1; i++) {
    cells.add(new MyCell(new PVector(random(1200), random(650)), random(40, 60), random(200,300),100, color(random(255), random(255), random(255)),0));
  }
}

public void draw() {
  noStroke();
  background(0xff2AB7D8);
  for (int i=0; i<food.size(); i++) {
    food.get(i).display();
  }
  for (int i=0; i<cells.size(); i++) {
    cells.get(i).display();
    cells.get(i).searchFood();
    cells.get(i).eat();
    if (cells.get(i).life<0)cells.remove(i);
  }
  if (mouseButton==LEFT) {
    food.add( new Food(new PVector(mouseX, mouseY), color(255)));
  }
    if (mouseButton==RIGHT && mousePressed) {
    cells.add(new MyCell(new PVector(mouseX,mouseY), random(40,60), random(200,300),100, color(random(255), random(255), random(255)),0));

    }
  if((millis()%PApplet.parseInt(3+abs(4*sin(0.00001f*millis()))))==0)food.add( new Food(new PVector(random(1200), random(650)), color(0))); //
//  text(int(3+abs(4*sin(0.00001*millis()))),10,10);
 // text(millis()/1000,10,20);
}
class MyCell {
  PVector pos;
  float size;
  float vel;
  float divRate;
  int cellColor;
  float life;
  float maxLife;
  float timeToDivide;
  int livedTime;
  int specie;
  int specieTime;
  MyCell(PVector _pos, float _size, float _divRate, float _life, int _cellColor, int _specieTime) {
    pos=_pos;
    size=_size;
    vel=0.3f+(velSizeT/size);
    divRate=_divRate;
    cellColor=_cellColor;
    life=_life;
    maxLife=maxLifeT*size+2*size;
    timeToDivide=divRate;
    livedTime=0;
    specieTime=_specieTime;
  }
  public void display() {

    fill(cellColor);
    ellipse(pos.x, pos.y, size, size);
    fill(255);
    ellipse(pos.x, pos.y, size*life/250,size* life/250);

    fill(0);

  }

  public PVector locateFood() {
    PVector nearestFood=new PVector(-10000, -10000);
    for (int i=0; i<food.size(); i++) {
      if (food.get(i).pos.dist(pos)<nearestFood.dist(pos)) {
        nearestFood=food.get(i).pos;
      }
    }
    return nearestFood;
  }
  public void moveTo(PVector dpos) {

    if (pos.x-dpos.x>0) {
      pos.x-=vel;
    } else pos.x+=vel;
    if (pos.y-dpos.y>0) {
      pos.y-=vel;
    } else pos.y+=vel;
    life -=lifeT;
    timeToDivide--;
    livedTime++;

    if (timeToDivide<0)divide();
  }
  public void searchFood() {
    moveTo(locateFood());
  }
  public void eat() {
    boolean foodFound=false; //check this
    for (int i=0; i<food.size() && !foodFound; i++) {
      if (food.get(i).pos.dist(pos)<size/2) {
        food.remove(i);
        foodFound=true;
        life+=foodRegardT;
        if (maxLife<life) {
          life=maxLife;
          divide();
        }
      }
    }
  }
  public void divide() {
    if (PApplet.parseInt(random(mutationT))==1) {
      cells.add( new MyCell(new PVector(pos.x+random(-1, 1)*size, pos.y+random(-1, 1)*size), size+random(-10, 10), divRate+random(-40,40), life/2, color(random(255), random(255), random(255)), 0)); //MUTATED CELL
    } else {
      cells.add(new MyCell(new PVector(pos.x+random(-1, 1)*size, pos.y+random(-1, 1)*size), size, divRate, life/2, cellColor, specieTime+livedTime));//NON-MUTATED CELL
    }
    timeToDivide=divRate;
    life-=life/2;
  }
}
class Food{
 PVector pos;
 int foodColor;
 Food(PVector _pos,int _foodColor){
   pos=_pos;
   foodColor=_foodColor;
 }
 public void display(){
  fill(foodColor);
  ellipse(pos.x,pos.y,10,10);
 }

}
  public void settings() {  size(1200,650); }
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "Cell" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
