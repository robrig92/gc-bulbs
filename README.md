# gc-bulbs

Hello there!

Prerequisites
- maven
- docker
- docker-compose

In order to run this project you have to run the following commands.

Navigate to the root project folder, then execute:
- cd bulbs
- mvn package

When the jar has been created then you have to run the next command to create the images and containers
- docker-compose up --build
When the containers are complety mounted (the spring boot's run output will be attached in the terminal screen),
then you can access to the application by the next url http://localhost:8000/

How to use
In the front ent app you will find a menu with two options

Upload
Here you can select a TXT file from your system and upload it, if everything went okey then you'll see a green message that tells you
that the file was uploaded.

Generate
Here when you first enter will see the room's blueprint or a legend that tells you to upload a room's blueprint first. When the system has
a file uploaded then you will see the room's blueprint with no colors in the free cells, that means that everything works fine, so it is time
to press the button "Solve blueprint" to get the bulbs distribution.

That's it, enjoy

Note:
If you want to see the algorithm then go to bulbs/src/main/java/com/robisoft/bulbs/services/impls
and open the file SimpleBulbDistributionCalculator.java

Regards!
