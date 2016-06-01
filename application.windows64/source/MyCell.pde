class MyCell {
  PVector pos;
  float size;
  float vel;
  float divRate;
  color cellColor;
  float life;
  float maxLife;
  float timeToDivide;
  int livedTime;
  int specie;
  int specieTime;
  MyCell(PVector _pos, float _size, float _divRate, float _life, color _cellColor, int _specieTime) {
    pos=_pos;
    size=_size;
    vel=0.3+(velSizeT/size);
    divRate=_divRate;
    cellColor=_cellColor;
    life=_life;
    maxLife=maxLifeT*size+2*size;
    timeToDivide=divRate;
    livedTime=0;
    specieTime=_specieTime;
  }
  void display() {

    fill(cellColor);
    ellipse(pos.x, pos.y, size, size);
    fill(255);
    ellipse(pos.x, pos.y, size*life/250,size* life/250);

    fill(0);

  }

  PVector locateFood() {
    PVector nearestFood=new PVector(-10000, -10000);
    for (int i=0; i<food.size(); i++) {
      if (food.get(i).pos.dist(pos)<nearestFood.dist(pos)) {
        nearestFood=food.get(i).pos;
      }
    }
    return nearestFood;
  }
  void moveTo(PVector dpos) {

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
  void searchFood() {
    moveTo(locateFood());
  }
  void eat() {
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
  void divide() {
    if (int(random(mutationT))==1) {
      cells.add( new MyCell(new PVector(pos.x+random(-1, 1)*size, pos.y+random(-1, 1)*size), size+random(-10, 10), divRate+random(-40,40), life/2, color(random(255), random(255), random(255)), 0)); //MUTATED CELL
    } else {
      cells.add(new MyCell(new PVector(pos.x+random(-1, 1)*size, pos.y+random(-1, 1)*size), size, divRate, life/2, cellColor, specieTime+livedTime));//NON-MUTATED CELL
    }
    timeToDivide=divRate;
    life-=life/2;
  }
}