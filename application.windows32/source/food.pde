class Food{
 PVector pos;
 color foodColor;
 Food(PVector _pos,color _foodColor){
   pos=_pos;
   foodColor=_foodColor;
 }
 void display(){
  fill(foodColor);
  ellipse(pos.x,pos.y,10,10);
 }

}