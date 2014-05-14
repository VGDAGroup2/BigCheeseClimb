Optimization! The game is now optimizes for interface running. This is done with a custom multiMap implementation in the GameObject class.


Some File Changes (The complete list is hella long):
1. Changes the name of RunnableObject to GameObject (This seems more appropriate with the new design).
2. Added 4 new packages to help organize things a bit.
3. Added 2 new interfaces (Runnable, Drawable) and moved Collidable into the new package structure.

How it works:
1. The map has the key values of interfaces implemented by game objects.
2. When an object is created it takes the objects interfaces and uses them to get the map values associated with them. It then adds the object to each key it finds.
3. If you want to get a list of Collidable objects all that you have to do is sends Collidable.class to the getList() method.

There are a few problems with this such as it is slow to input and delete objects. But it resolves the problem of updating objects when there is no need to.
One great example of this is our game title or splash screens. They will not need to be moved or collided with at any point.
We can now and a more diverse set of game objects with ease.

The Big-O notation of this is high initially because of object creation and deletion but as the number of objects increases it is way more efficient. 

I also re-organized our packages. Anything associated with game objects is now in the objects package which has a few sub packages:
Interfaces: I put all the game object interfaces in here.
Game: Game Objects (Objects that run on the main part of the game) go in here.
Menu: Menu objects go in here (Buttons and stuff).

Removed the lists we were previously working with. 1 in game objects and the other in Collision Detection.

The game now runs at a higher ups. Object creation and deletion slows it down a little when done. I used to run at 7000 ups. I am now around 7500 - 8000 ups.

That's it for this update. Enjoy. I think the next one will be adding splash screen functionality. 