# gc-bulbs

Hello there!
In order to run this project you have to run the following commands.

Navigate to the root project folder, then execute:
- cd bulbs
- mvn package

When the jar has been created then you have to run the next command to create the images and containers
- docker-compose up --build
SimpleBulbCalculator
When the containers are complety mounted, then you can access to the application by the next url
http://localhost:3000/

That's it, enjoy

Note:
If you want to see the algorithm then go to bulbs/src/main/java/com/robisoft/bulbs/services/impls
and open the file SimpleBulbDistributionCalculator.java

Regards!
