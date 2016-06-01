final float velSizeT=55;
final float maxLifeT =3;
final float lifeT = 0.7;
final float foodRegardT = 80;
final int mutationT = 30;
//final int startingCellsT = 2;

import java.util.List;
List<MyCell>cells=new ArrayList();
//MyCell[] cells;
// MyCell(PVector _pos, float _size, float _divRate, float _life, color _cellColor) {
List<Food>food=new ArrayList();
void setup() {
  size(1200,650);
  //cells= new MyCell[startingCellsT];
  for (int i=0; i<20; i++) {
    food.add(new Food(new PVector(random(1200), random(650)), color(0)));
  }
  for (int i=0; i<1; i++) {
    cells.add(new MyCell(new PVector(random(1200), random(650)), random(40, 60), random(200,300),100, color(random(255), random(255), random(255)),0));
  }
}

void draw() {
  noStroke();
  background(#2AB7D8);
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
  if((millis()%int(3+abs(4*sin(0.00001*millis()))))==0)food.add( new Food(new PVector(random(1200), random(650)), color(0))); //
//  text(int(3+abs(4*sin(0.00001*millis()))),10,10);
 // text(millis()/1000,10,20);
}