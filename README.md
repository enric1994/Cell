#Evolution simulation using Processing

"At some point a particularly remarkable molecule was formed by
accident."


In this sketch, I want to simulate the beginning of the life. The rules are as follows:


-Each cell has a size and a division rate that force the cell to divide every X seconds.

-Bigger cells move slow but can store more food, smalls cells are faster but have less food capacity.

-When a cell can't store more food it divides.

-The cells need to eat constantly, otherwise they dead.

-The cells can't eat other cells! This is not agar.io. 


Each time a cell divides, there is a possibility that a mutation appear, which will have diferent color and similar characteristics.

To simulate external factors, the amount of food that is generated every second varies sinusoidally, a complete cycle takes about 10 minutes. Only the adapted cells will survive.

#VIDEO

[![ScreenShot](https://raw.github.com/enric1994/Cell/master/cells.png)](https://www.youtube.com/watch?v=ixRlhI6WiJc&feature=youtu.be)


"As mis-copyings were made and propagated, the primeval soup
became filled by a population not of identical replicas, but of several
varieties of replicating molecules, all 'descended' from the same
ancestor. Would some varieties have been more numerous than
others? Almost certainly yes." 

Richars Dawkins. (1976). The Selfish Gene. New York: Oxford University Press.

Use the mouse to interact and create food and cells.


