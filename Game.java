/**
 * @author (Richard W. Zacho)
 * @version (1.0.0.0)
 */

import java.util.Scanner;

public class Game
{
    Scanner info = new Scanner(System.in);
    //The X and Y pos of the player
    int xPos, yPos, damage, health, coins, healthPotion;
    //True for new direction, or items, or if the switch is related to an event
    boolean newInput, sword, shield, armor, map, event, cabinWindowBroken;
    //Different knowledge you can gain/display
    boolean info1, info2;
    //Location 1 = sword, 2 = shield, 3 = old temple, 4 = village
    boolean swordLocation, shieldLocation, villageLocation, shopLocation;
    //goblin ambush bools
    boolean goblinAmbush, swordGoblinDead;
    //boolean to prevent an event to be forced again if you write the wrong direction/illegal input
    boolean noEventStart;
    //ogre event
    int ogreHealth;
    boolean ogreSleep, ogreDead;
    
    //constructor for the game data
    public Game()
    {
        newInput = true;
        sword = false;
        shield = false;
        armor = false;
        damage = 10;
        map = false;
        event = false;
        cabinWindowBroken = false;
        noEventStart = false;
        //info
        info1 = false;
        info2 = false;
        //locations and info
        swordLocation = false;
        shieldLocation = false;
        villageLocation = false;
        shopLocation = false;
        //Goblin ambush
        goblinAmbush = false;
        swordGoblinDead = false;
        //ogre
        ogreHealth = 100;
        ogreSleep = true;
        ogreDead = false;
        
        xPos = 0;
        yPos = 0;
        health = 100;
        coins = 0;
        healthPotion = 0;
    }
    
    //Initiates the game
    public void startGame()
    {
        System.out.println("Welcome to Zachorian Adventure");
        System.out.println("The world is yours to explore!");
        System.out.println("With your trusted notebook, go out into the world.");
        System.out.println("But take care, you never know");
        System.out.println("what lurks in the depths of this place");
        System.out.println();
        
        System.out.println("You are standing in the middle of a large field");
        System.out.println("To the north, you think you can spot a couple of tree topps");
        System.out.println("What seems to sound like a few muffeled screams is coming from the west");
        System.out.println("You're almost certan you can hear the water splashing from the south");
        System.out.println("There's a large forest to the east");
        System.out.println();
        
        inputPathChoice();
    }
    
    //Ask for the player input to determin what will happen
    private void getInfo()
    {
        if (event == false)
        {
            System.out.println();
            System.out.println("Where would you like to go?");
        }
        System.out.println("Remember, you can write 'hint' to get available commands");
        
        String input = info.nextLine();
        String inputUpper = input.toUpperCase();
        
        newInput = true;
      
        choosePath(inputUpper);
    }
    
    //Checks the position of the player, and possible events on that location
    private void checkPosition()
    {
        printStats();
        
        if (health >= 0)
        {
            //Sets the right border
            if (xPos > 7)
            {
                System.out.println("There doesn't seem to be anything other than forest this way");
                System.out.println("Better turn back");
                System.out.println();
                xPos = 7;
            }
            //Sets the left border
            if (xPos < -6)
            {
                System.out.println("There doesn't seem to be anything other than jungle this way");
                System.out.println("Better turn back");
                System.out.println();
                xPos = -6;
            }
            //set the top border
            if (yPos > 9)
            {
                System.out.println("There doesn't seem to be anything other than forest this way");
                System.out.println("Better turn back");
                System.out.println();
                yPos = 9;
            }
            //sets the bottom border
            if (yPos < -5)
            {
                System.out.println("There doesn't seem to be anything other than ocean this way");
                System.out.println("I can't go en further south");
                System.out.println();
                yPos = -5;
            }
            //Cabin event
            if (xPos == 1 && yPos == -1 && noEventStart == false)
            {
                System.out.println();
                System.out.println("You've found a cabin");
                System.out.println("There's a note on the door");
                System.out.println("What would you like to do?");
                System.out.println();
                event = true;
                getInfo();
            }
            //forest sword event
            if (xPos == 2 && yPos == 7 && noEventStart == false)
            {
                System.out.println();
                System.out.println("You've found a forest shrine");
                System.out.println("In the middle of a large clearing you spot a sword");
                System.out.println("What would you like to do?");
                System.out.println();
                event = true;
                getInfo();
            }
            //lost temple shield event
            if (xPos == -3 && yPos == 4 && noEventStart == false)
            {
                System.out.println();
                System.out.println("You've found a temple to forgotten gods");
                System.out.println("There appears to be a large stone door blocking your path");
                System.out.println("What would you like to do?");
                System.out.println();
                event = true;
                getInfo();
            }
            //lighthouse event
            if (xPos == -4 && yPos == -4 && noEventStart == false)
            {
                System.out.println();
                System.out.println("You've found a lighthouse");
                System.out.println("There's a note on the door");
                System.out.println("What would you like to do?");
                System.out.println();
                event = true;
                getInfo();
            }
            //jungle health refresh event
            if (xPos == -5 && yPos == -2 && noEventStart == false)
            {
                System.out.println();
                System.out.println("You've found a jungle shrine");
                System.out.println("In the middle of a large clearing, theres is a small pool of clear water");
                System.out.println("What would you like to do?");
                System.out.println();
                event = true;
                getInfo();
            }
            //village outskirt north event
            if (xPos == 5 && yPos == 1 && noEventStart == false)
            {
                System.out.println();
                System.out.println("You've entered the northern village outskirts");
                System.out.println("There seems to be a lot of devesation here");
                System.out.println("The villagers must have left in a hurry");
                System.out.println("What would you like to do?");
                System.out.println();
                event = true;
                getInfo();
            }
            //village outskirt south event
            if (xPos == 5 && yPos == -1 && noEventStart == false)
            {
                System.out.println();
                System.out.println("You've entered the southern village outskirts");
                System.out.println("There seems to be a lot of devesation here");
                System.out.println("The villagers must have left in a hurry");
                System.out.println("What would you like to do?");
                System.out.println();
                event = true;
                getInfo();
            }
            //village event
            if (xPos == 5 && yPos == 0 && noEventStart == false)
            {
                System.out.println();
                System.out.println("You've entered the village");
                System.out.println("The destrucion of this place is beyond repair");
                System.out.println("You spot a giant ogre sleeping in the middle of the town");
                System.out.println("What would you like to do?");
                System.out.println();
                event = true;
                getInfo();
            }
            //boat escape event
            if(xPos == -1 && yPos == -5 && noEventStart == false)
            {
                System.out.println();
                System.out.println("You've found a boat");
                System.out.println("There appears to be some fishing gear next to the boat");
                System.out.println("What would you like to do?");
                System.out.println();
                event = true;
                getInfo();
            }
            //leaves trap event
            if(xPos == 2 && yPos == 2 && noEventStart == false)
            {
                System.out.println();
                System.out.println("You've found a pile of leaves");
                System.out.println("What would you like to do?");
                System.out.println();
                event = true;
                getInfo();
            }
            //shop
            if(xPos == 4 && yPos == -4 && noEventStart == false)
            {
                System.out.println();
                System.out.println("Welcome to the blue lagoon shop");
                System.out.println("We have many wares, what might interest you?");
                System.out.println("-----------------------------------------");
                System.out.println("-      Potion, +30 health, 15 coins     -");
                System.out.println("- Restore my health to 100%, 15 coins   -");
                System.out.println("- Shield, 10 damage reduction, 40 coins -");
                System.out.println("- Sword, +20 damage increase, 40 coins  -");
                System.out.println("-----------------------------------------");
                System.out.println();
                event = true;
                getInfo();
            }
        }
        else
        {
            died();
        }
        inputPathChoice();
    }
    
    //Middle info, would not fit in with events, only exploration
    private void inputPathChoice()
    {
        //Debugg
        System.out.println("xPos: " + xPos + " yPos: " + yPos);
        System.out.println();
        System.out.println("You can open your notebook, by typing: Notebook");
        System.out.println();
        getInfo();
    }
    
    //Chose where you want to go/what you want to do
    private void choosePath(String input)
    {   
        if (event == false)
        {
            while (newInput == true)
            {
                switch(input)
                {
                    case "NORTH":
                        System.out.println("You went north");
                        System.out.println();
                        noEventStart = false;
                        newInput = false;
                        yPos += 1;
                        break;
                    case "SOUTH":
                        System.out.println("You went south");
                        System.out.println();
                        noEventStart = false;
                        newInput = false;
                        yPos -= 1;
                        break;
                    case "EAST":
                        System.out.println("You went east");
                        System.out.println();
                        noEventStart = false;
                        newInput = false;
                        xPos += 1;
                        break;
                    case "WEST":
                        System.out.println("You went west");
                        System.out.println();
                        noEventStart = false;
                        newInput = false;
                        xPos -= 1;
                        break;
                    case "NOTEBOOK":
                        System.out.println("You opened your notebook");
                        System.out.println();
                        newInput = false;
                        notebook();
                        break;
                    case "DRINK POTION" :
                        if (healthPotion == 0)
                        {
                            System.out.println("You don't have any potions");
                            System.out.println();
                        }
                        else
                        {
                            System.out.println("You drank a potion");
                            System.out.println("Health restored by 30 points (max 100)");
                            if (health < 70)
                            {
                                health += 30;
                            }
                            else
                            {
                                health = 100;
                            }
                        }
                        checkPosition();
                        break;
                    case "HINT":
                        System.out.println("You can go in these directions: ");
                        System.out.println("North, South, East, West, Drink Potion");
                        System.out.println();
                        checkPosition();
                        break;
                    case "RESET":
                        System.out.println();
                        System.out.println();
                        System.out.println();
                        System.out.println("GAME HAVE BEEN RESET");
                        System.out.println();
                        System.out.println();
                        System.out.println();
                        resetInfo();
                        break;
                    default:
                        System.out.println("You have to write the correct command");
                        checkPosition();
                        break;
                }
            }
            checkPosition();
        }
        //What you want to do at events
        else
        {
            noEventStart = true;
            //cabin event
            while (newInput == true && xPos == 1 && yPos == -1)
            {
                switch(input)
                {
                    case "READ NOTE":
                        System.out.println("John, if you find this letter, take these coins");
                        System.out.println("Go to the shop and get yourself some dinner");
                        System.out.println("You find 4 coins underneath the doormat");
                        System.out.println("Coordinates for  the shop have been updated in your notebook");
                        shopLocation = true;
                        coins += 4;
                        checkPosition();
                        break;
                    case "KNOCK":
                        System.out.println("No one is answering");
                        System.out.println("This place seems deserted");
                        System.out.println("There has to be some other way in");
                        System.out.println();
                        checkPosition();
                        break;
                    case "ENTER":
                        if (cabinWindowBroken == false)
                        {
                            System.out.println("The door is locked");
                            System.out.println("There has to be some other way in");
                            System.out.println();
                            checkPosition();
                        }
                        else
                        {
                            System.out.println("The door is open");
                            System.out.println("You decide to walk in");
                            System.out.println();
                            eventCabin();
                        }
                        break;
                    case "BREAK WINDOW":
                        if (cabinWindowBroken == false)
                        {
                            System.out.println("You manage to break the glass and open the window");
                            System.out.println("As you open the window, you manage to cut yourself");
                            System.out.println("-15 Health");
                            System.out.println();
                            cabinWindowBroken = true;
                            health -= 15;
                            eventCabin();
                        }
                        else
                        {
                            System.out.println("The door is open");
                            System.out.println("Maybe you should leave the window as it is");
                            System.out.println();
                            checkPosition();
                        }
                        break;
                    case "YELL":
                        System.out.println("No one is answering");
                        System.out.println("This place seems deserted");
                        System.out.println("There has to be some other way in");
                        System.out.println();
                        checkPosition();
                        break;
                    case "DRINK POTION" :
                        if (healthPotion == 0)
                        {
                            System.out.println("You don't have any potions");
                            System.out.println();
                        }
                        else
                        {
                            System.out.println("You drank a potion");
                            System.out.println("Health restored by 30 points (max 100)");
                            if (health < 70)
                            {
                                health += 30;
                            }
                            else
                            {
                                health = 100;
                            }
                        }
                        checkPosition();
                        break;
                    case "LEAVE":
                        System.out.println("You decided to leave the cabin");
                        System.out.println();
                        //stops the event
                        event = false;
                        getInfo();
                        break;
                    case "HINT":
                        System.out.println("These commands are available: ");
                        System.out.println("Read Note, Knock, Enter, Break Window, Yell, Drink Potion, Leave");
                        System.out.println();
                        checkPosition();
                        break;
                    default:
                        System.out.println("Your character doesn't know what that means");
                        System.out.println();
                        checkPosition();
                        break;
                }
            }
            //forest sword event
            while (newInput == true && xPos == 2 && yPos == 7)
            {
                switch (input)
                {
                    case "TAKE SWORD":
                        if (sword = false)
                        {
                            System.out.println("You've decide to go up to the shrine and pull the sword");
                            System.out.println("The sword vibrates as you pull it from the stone");
                            System.out.println("You've aquired a sword");
                            damage += 20;
                            goblinAmbush();
                        }
                        else
                        {
                            System.out.println("You've already picked up the sword");
                        }
                        getInfo();
                        break;
                    case "LISTEN":
                        System.out.println("You begin to listen");
                        System.out.println("The sounds of birds pierce the air");
                        System.out.println("A few crickets decides to play the song of their people");
                        System.out.println("You can't hear any threat in the area");
                        checkPosition();
                        break;
                    case "LOOK AROUND":
                        System.out.println("You decide to take a look around");
                        System.out.println("There is nothing on the ground out of the usual");
                        System.out.println("You think you see something shining in the trees");
                        System.out.println();
                        checkPosition();
                        break;
                    case "DRINK POTION" :
                        if (healthPotion == 0)
                        {
                            System.out.println("You don't have any potions");
                            System.out.println();
                        }
                        else
                        {
                            System.out.println("You drank a potion");
                            System.out.println("Health restored by 30 points (max 100)");
                            if (health < 70)
                            {
                                health += 30;
                            }
                            else
                            {
                                health = 100;
                            }
                        }
                        checkPosition();
                        break;
                    case "LEAVE":
                        System.out.println("You decided to leave forest shrine");
                        System.out.println();
                        //stops the event
                        event = false;
                        getInfo();
                        break;
                    case "HINT":
                        System.out.println("These commands are available: ");
                        System.out.println("Take Sword, Listen, Look Around, Drink Potion, Leave");
                        System.out.println();
                        checkPosition();
                        break;
                    default:
                        System.out.println("Your character doesn't know what that means");
                        System.out.println();
                        checkPosition();
                        break;
                }
            }
            
            //The event for the goblin ambush of sword collect
            while (goblinAmbush = true && xPos == 2 && yPos == 7)
            {
                switch (input)
                {
                    case "FLEE":
                        System.out.println();
                        System.out.println("You try to flee, on of the goblins stabs you with a spear");
                        System.out.println("You loose 15 points of health");
                        if (shield == true)
                        {
                            System.out.println("Luckily you had aquired a shield earlier");
                            System.out.println("Damage reduced by 10 points");
                            System.out.println();
                        }
                        else
                        {
                            health -= 15;
                        }
                        getInfo();
                        break;
                    case "FIGHT SPEAR GOBLIN":
                        if (swordGoblinDead = false)
                        {
                            System.out.println();
                            System.out.println("You decide to fight the spear goblin");
                            System.out.println("With a swing of your newfound sword the goblin is sliced in half");
                            System.out.println("The sword goblin is terrified and flees into the forest");
                            System.out.println("Looting the goblin gives you a set of armor and 14 coins");
                            armor = true;
                            System.out.println();
                            goblinAmbush = false;
                        }
                        else
                        {
                            System.out.println();
                            System.out.println("You now turn to fight the spear goblin");
                            System.out.println("With a slice of your sword, the goblins head is severed from it shoulders");
                            System.out.println("Looting the goblin gives you a set of armor and 14 coins");
                            armor = true;
                            System.out.println();
                            goblinAmbush = false;
                        }
                        checkPosition();
                        break;
                    case "FIGHT SWORD GOBLIN":
                        if (swordGoblinDead == false)
                        {
                            System.out.println();
                            System.out.println("You decide to fight the sword goblin");
                            System.out.println("With a swing of your newfound sword the goblin is sliced in half");
                            System.out.println("The spear goblin thrusts his spear into your leg");
                            System.out.println("You loose 15 points of health");
                            System.out.println();
                        }
                        else
                        {
                            System.out.println();
                            System.out.println("This goblin is already dead");
                            System.out.println();
                        }
                        checkPosition();
                        break;
                    case "PLEAD FOR MERCY":
                        System.out.println();
                        System.out.println("You go down on your knees and start to plead");
                        System.out.println("The goblins laugh at your attempt to plead for your life");
                        System.out.println("The sword goblin slices quickly at your neck");
                        System.out.println("Your head is severed from your body and tumbles towards the ground");
                        System.out.println("You have died");
                        died();
                        break;
                    case "DRINK POTION" :
                        if (healthPotion == 0)
                        {
                            System.out.println("You don't have any potions");
                            System.out.println();
                        }
                        else
                        {
                            System.out.println("You drank a potion");
                            System.out.println("Health restored by 30 points (max 100)");
                            if (health < 70)
                            {
                                health += 30;
                            }
                            else
                            {
                                health = 100;
                            }
                        }
                        checkPosition();
                        break;
                    case "HINT":
                        System.out.println("These commands are available: ");
                        System.out.println("Flee, Fight spear goblin, Fight sword goblin, Plead for mercy, Drink Potion");
                        System.out.println();
                        checkPosition();
                        break;
                    default:
                        System.out.println("Your character doesn't know what that means");
                        System.out.println();
                        checkPosition();
                        break;
                }
            }
            //lost temple shield event
            while (newInput == true && xPos == -3 && yPos == 4)
            {
                switch (input)
                {
                    case "ENTER":
                        System.out.println("The doors to the ancient temple is deadlocked");
                        System.out.println("There's something shining in the corner of the temple door,");
                        System.out.println("Underneath a pile of leaves and dirt, you find a shield");
                        shield = true;
                        event = false;
                        getInfo();
                        break;
                    case "DRINK POTION" :
                        if (healthPotion == 0)
                        {
                            System.out.println("You don't have any potions");
                            System.out.println();
                        }
                        else
                        {
                            System.out.println("You drank a potion");
                            System.out.println("Health restored by 30 points (max 100)");
                            if (health < 70)
                            {
                                health += 30;
                            }
                            else
                            {
                                health = 100;
                            }
                        }
                        checkPosition();
                        break;
                    case "LEAVE":
                        System.out.println("You decided to leave the ancient temple");
                        System.out.println();
                        //stops the event
                        event = false;
                        getInfo();
                        break;
                    case "HINT":
                        System.out.println("These commands are available: ");
                        System.out.println("Enter, Drink Potion, Leave");
                        System.out.println();
                        checkPosition();
                        break;
                    default:
                        System.out.println("Your character doesn't know what that means");
                        System.out.println();
                        checkPosition();
                        break;
                }
            }
            //lighthouse event
            while (newInput == true && xPos == -4 && yPos == -4)
            {
                switch (input)
                {
                    case "READ NOTE":
                        System.out.println("You pick up the note and start to read: ");
                        System.out.println("Off to defend the village");
                        System.out.println("The lighthouse is out of service untill i return");
                        System.out.println("I'll make my way to the abandoned temple first");
                        System.out.println("Meet me in the village if you want to help!");
                        System.out.println();
                        System.out.println("Location for the forgotten temple have been added to notebook");
                        System.out.println("Location for the village have been added to notebook");
                        shieldLocation = true;
                        villageLocation = true;
                        event = false;
                        getInfo();
                        break;
                    case "KNOCK":
                        System.out.println("No one is answering");
                        System.out.println("This place seems deserted");
                        System.out.println("There has to be some other way in");
                        System.out.println();
                        checkPosition();
                        break;
                    case "ENTER":
                        System.out.println("The door is locked");
                        System.out.println("There has to be some other way in");
                        System.out.println();
                        checkPosition();
                        break;
                    case "YELL":
                        System.out.println("No one is answering");
                        System.out.println("This place seems deserted");
                        System.out.println("There has to be some other way in");
                        System.out.println();
                        checkPosition();
                        break;
                    case "DRINK POTION" :
                        if (healthPotion == 0)
                        {
                            System.out.println("You don't have any potions");
                            System.out.println();
                        }
                        else
                        {
                            System.out.println("You drank a potion");
                            System.out.println("Health restored by 30 points (max 100)");
                            if (health < 70)
                            {
                                health += 30;
                            }
                            else
                            {
                                health = 100;
                            }
                        }
                        checkPosition();
                        break;
                    case "LEAVE":
                        System.out.println("You decided to leave the lighthouse");
                        System.out.println();
                        //stops the event
                        event = false;
                        getInfo();
                        break;
                    case "HINT":
                        System.out.println("These commands are available: ");
                        System.out.println("Read Note, Knock, Enter, Yell, Drink Potion, Leave");
                        System.out.println();
                        checkPosition();
                        break;
                    default:
                        System.out.println("Your character doesn't know what that means");
                        System.out.println();
                        checkPosition();
                        break;
                }
            }
            //jungle health refresh event
            while (newInput == true && xPos == -5 && yPos == -2)
            {
                switch (input)
                {
                    case "LISTEN":
                        System.out.println("You begin to listen");
                        System.out.println("The sounds of birds pierce the air");
                        System.out.println("A few crickets decides to play the song of their people");
                        System.out.println("You can't hear any threat in the area");
                        checkPosition();
                        break;
                    case "LOOK AROUND":
                        System.out.println("You decide to take a look around");
                        System.out.println("There is nothing on the ground out of the usual");
                        System.out.println("This place seems perfectly at peace");
                        System.out.println();
                        checkPosition();
                        break;
                    case "DRINK WATER":
                        System.out.println("You decide to drink the water");
                        System.out.println("A refreshing surge is felt through your body");
                        System.out.println("Your health have been restored");
                        System.out.println();
                        health = 100;
                        checkPosition();
                        break;
                    case "DRINK POTION" :
                        if (healthPotion == 0)
                        {
                            System.out.println("You don't have any potions");
                            System.out.println();
                        }
                        else
                        {
                            System.out.println("You drank a potion");
                            System.out.println("Health restored by 30 points (max 100)");
                            if (health < 70)
                            {
                                health += 30;
                            }
                            else
                            {
                                health = 100;
                            }
                        }
                        checkPosition();
                        break;
                    case "LEAVE":
                        System.out.println("You decided to leave the jungle shrine");
                        System.out.println();
                        //stops the event
                        event = false;
                        getInfo();
                        break;
                    case "HINT":
                        System.out.println("These commands are available: ");
                        System.out.println("Listen, Look around, Drink Water, Drink Potion, Leave");
                        System.out.println();
                        checkPosition();
                        break;
                    default:
                        System.out.println("Your character doesn't know what that means");
                        System.out.println();
                        checkPosition();
                        break;
                }
            }
            //village outskirt north event
            while (newInput == true && xPos == 5 && yPos == 1)
            {
                switch (input)
                {
                    case "LISTEN":
                        System.out.println("You begin to listen");
                        System.out.println("There is no sound of animals in this place");
                        System.out.println("A few crickets decides to play the song of their people");
                        System.out.println("You can't hear any threat in the area");
                        checkPosition();
                        break;
                    case "LOOK AROUND":
                        System.out.println("You decide to take a look around");
                        System.out.println("Looking around you find a few coins and a red drink of some sort");
                        System.out.println("This place seems perfectly at peace, despite all the destruction");
                        System.out.println();
                        checkPosition();
                        coins += 34;
                        healthPotion += 1;
                        break;
                    case "DRINK POTION" :
                        if (healthPotion == 0)
                        {
                            System.out.println("You don't have any potions");
                            System.out.println();
                        }
                        else
                        {
                            System.out.println("You drank a potion");
                            System.out.println("Health restored by 30 points (max 100)");
                            if (health < 70)
                            {
                                health += 30;
                            }
                            else
                            {
                                health = 100;
                            }
                        }
                        checkPosition();
                        break;
                    case "LEAVE":
                        System.out.println("You decided to leave the village outskirts");
                        System.out.println();
                        //stops the event
                        event = false;
                        getInfo();
                        break;
                    case "HINT":
                        System.out.println("These commands are available: ");
                        System.out.println("Listen, Look around, Drink Potion, Leave");
                        System.out.println();
                        checkPosition();
                        break;
                    default:
                        System.out.println("Your character doesn't know what that means");
                        System.out.println();
                        checkPosition();
                        break;
                }
            }
            //village outskirt south event
            while (newInput == true && xPos == 5 && yPos == -1)
            {
                switch (input)
                {
                    case "LISTEN":
                        System.out.println("You begin to listen");
                        System.out.println("There is no sound of animals in this place");
                        System.out.println("A few crickets decides to play the song of their people");
                        System.out.println("You can't hear any threat in the area");
                        checkPosition();
                        break;
                    case "LOOK AROUND":
                        System.out.println("You decide to take a look around");
                        System.out.println("Looking around you find a few coins and a red drink of some sort");
                        System.out.println("This place seems perfectly at peace, despite all the destruction");
                        System.out.println();
                        checkPosition();
                        coins += 23;
                        healthPotion += 2;
                        break;
                    case "DRINK POTION" :
                        if (healthPotion == 0)
                        {
                            System.out.println("You don't have any potions");
                            System.out.println();
                        }
                        else
                        {
                            System.out.println("You drank a potion");
                            System.out.println("Health restored by 30 points (max 100)");
                            if (health < 70)
                            {
                                health += 30;
                            }
                            else
                            {
                                health = 100;
                            }
                        }
                        checkPosition();
                        break;
                    case "LEAVE":
                        System.out.println("You decided to leave the village outskirts");
                        System.out.println();
                        //stops the event
                        event = false;
                        getInfo();
                        break;
                    case "HINT":
                        System.out.println("These commands are available: ");
                        System.out.println("Listen, Look around, Drink Potion, Leave");
                        System.out.println();
                        checkPosition();
                        break;
                    default:
                        System.out.println("Your character doesn't know what that means");
                        System.out.println();
                        checkPosition();
                        break;
                }
            }
            //village/ogre event
            while (newInput == true && xPos == 5 && yPos == 0)
            {
                switch (input)
                {
                    case "DRINK POTION" :
                        if (healthPotion == 0)
                        {
                            System.out.println("You don't have any potions");
                            System.out.println();
                        }
                        else
                        {
                            System.out.println("You drank a potion");
                            System.out.println("Health restored by 30 points (max 100)");
                            if (health < 70)
                            {
                                health += 30;
                            }
                            else
                            {
                                health = 100;
                            }
                        }
                        checkPosition();
                        break;
                    case "LEAVE":
                        System.out.println("You decided to leave silently to not wake the ogre");
                        System.out.println();
                        //stops the event
                        event = false;
                        getInfo();
                        break;
                    case "HINT":
                        System.out.println("These commands are available: ");
                        System.out.println("Drink Potion, Leave");
                        System.out.println();
                        checkPosition();
                        break;
                    default:
                        System.out.println("Your character doesn't know what that means");
                        System.out.println();
                        checkPosition();
                        break;
                }
            }
            //boat escape event
            while (newInput == true && xPos == -1 && yPos == -5)
            {
                switch (input)
                {
                    case "LONG TROP":
                        System.out.println("You've decided to take the opportunity and use the boat");
                        System.out.println("You pick up the fishing equipment, and get onboard");
                        System.out.println("After a while, your feet begins to get wet");
                        System.out.println("You suddenly realize that your boat is leaking");
                        System.out.println("The damage was discovered too late");
                        System.out.println("Your boat sinks, and you begin to swim towards the shore");
                        System.out.println("You soon realise you took the boat out too far");
                        System.out.println("Exhausted you can't go on, and sink to the bottom of the sea");
                        System.out.println("You have died");
                        died();
                        break;
                    case "SHORT TRIP":
                        System.out.println("You've decided to take the opportunity and use the boat");
                        System.out.println("You pick up the fishing equipment, and get onboard");
                        System.out.println("After a while, your feet begins to get wet");
                        System.out.println("You suddenly realize that your boat is leaking");
                        System.out.println("The damage was discovered almost too late");
                        System.out.println("Your boat sinks, and you begin to swim towards the shore");
                        System.out.println("On your way in, you scratch yourself on a few corals");
                        System.out.println("Health minus 10");
                        System.out.println("Exhausted you barely make it to the shore");
                        health -= 10;
                        getInfo();
                        break;
                    case "DRINK POTION":
                        if (healthPotion == 0)
                        {
                            System.out.println("You don't have any potions");
                            System.out.println();
                        }
                        else
                        {
                            System.out.println("You drank a potion");
                            System.out.println("Health restored by 30 points (max 100)");
                            if (health < 70)
                            {
                                health += 30;
                            }
                            else
                            {
                                health = 100;
                            }
                        }
                        checkPosition();
                        break;
                    case "LEAVE":
                        System.out.println("You decided to leave the boat resting on the shore");
                        System.out.println();
                        //stops the event
                        event = false;
                        getInfo();
                        break;
                    case "HINT":
                        System.out.println("These commands are available: ");
                        System.out.println("Long Trip, Short Trip, Drink Potion, Leave");
                        System.out.println();
                        checkPosition();
                        break;
                    default:
                        System.out.println("Your character doesn't know what that means");
                        System.out.println();
                        checkPosition();
                        break;
                }
            }
            //leaves trap event
            while (newInput == true && xPos == 2 && yPos == 2)
            {
                switch (input)
                {
                    case "INSPECT LEAVES":
                        System.out.println("You decide to inspect the pile of leaves");
                        System.out.println("As you move closer, the ground beneath you vanishes");
                        System.out.println("You plumet into a deep hole filled with spikes");
                        System.out.println("A large spike pierces your heart");
                        System.out.println("You have died");
                        System.out.println();
                        died();
                        break;
                    case "DRINK POTION" :
                        if (healthPotion == 0)
                        {
                            System.out.println("You don't have any potions");
                            System.out.println();
                        }
                        else
                        {
                            System.out.println("You drank a potion");
                            System.out.println("Health restored by 30 points (max 100)");
                            if (health < 70)
                            {
                                health += 30;
                            }
                            else
                            {
                                health = 100;
                            }
                        }
                        checkPosition();
                        break;
                    case "LEAVE":
                        System.out.println("You decided to avoid the pile of leaves");
                        System.out.println();
                        //stops the event
                        event = false;
                        getInfo();
                        break;
                    case "HINT":
                        System.out.println("These commands are available: ");
                        System.out.println("Inspect leaves, Drink Potion, Leave");
                        System.out.println();
                        checkPosition();
                        break;
                    default:
                        System.out.println("Your character doesn't know what that means");
                        System.out.println();
                        checkPosition();
                        break;
                }
            }
            //Shop
            while (newInput == true && xPos == 4 && yPos == -4)
            {
                switch (input)
                {
                   case "RESTORE HEALTH":
                        if (coins >= 15)
                        {
                            coins -= 15;
                            health = 100;
                            System.out.println("Health have been restored");
                            System.out.println("15 coins have been removed from your items");
                        }
                        else
                        {
                            System.out.println("You do not have enough money for this");
                        }
                        checkPosition();
                        break;
                   case "BUY POTION":
                        if (coins >= 15)
                        {
                            coins -= 15;
                            healthPotion += 1;
                            System.out.println("You have bought a potion");
                            System.out.println("15 coins have been removed from your items");
                        }
                        else
                        {
                            System.out.println("You do not have enough money for this");
                        }
                        checkPosition();
                        break;
                   case "BUY SHIELD":
                        if (coins >= 40)
                        {
                            coins -= 40;
                            System.out.println("You have bought a shield");
                            System.out.println("40 coins have been removed from your items");
                            shield = true;
                        }
                        else
                        {
                            System.out.println("You do not have enough money for this");
                        }
                        checkPosition();
                        break;
                   case "BUY SWORD":
                        if (coins >= 40)
                        {
                            coins -= 40;
                            damage += 20;
                            System.out.println("You have bought a sword");
                            System.out.println("40 coins have been removed from your items");
                            sword = true;
                        }
                        else
                        {
                            System.out.println("You do not have enough money for this");
                        }
                        checkPosition();
                        break;
                   case "LEAVE":
                        System.out.println("You decided to leave the shop");
                        System.out.println();
                        //stops the event
                        event = false;
                        getInfo();
                        break;
                   case "HINT":
                        System.out.println("These commands are available: ");
                        System.out.println("Restore Health, Buy Potion, Buy Shield, Buy Sword, Leave");
                        System.out.println();
                        checkPosition();
                        break;
                   default:
                        System.out.println("Your character doesn't know what that means");
                        System.out.println();
                        checkPosition();
                        break;
                }
            }
            getInfo();
        }
        //Resets the input for the switch
        newInput = false;
    }
    
    private void printStats()
    {
        System.out.println();
        System.out.println("-----------------------------------");
        System.out.println("Health: " + health + "  Coins: " + coins + "  Potions: " + healthPotion);
        if (sword == true)
        {
            System.out.println("----------Sword Equipped-----------");
        }
        if (shield == true)
        {
            System.out.println("----------Shield Equipped----------");
        }
        if (armor == true)
        {
            System.out.println("----------Armor Equipped-----------");
        }
        
        System.out.println("-----------------------------------");
        System.out.println();
    }
    
    private void eventCabin()
    {
        System.out.println();
        System.out.println("The cabin is long since abandoned");
        System.out.println("The place seem to have been stripped of all value");
        System.out.println("A sudden breeze blows through the broken window");
        System.out.println("There's a piece of paper on the counter blaffering in the wind");
        System.out.println("You pick it up, It reads:");
        System.out.println();
        System.out.println("Nothing can stop it!");
        System.out.println("We've tried everything, you are our only hope.");
        System.out.println("I know you retired from the service,");
        System.out.println("and stashed your sword in the forest.");
        System.out.println("But i beg of you to retreive it,");
        System.out.println("and come to us in our time of need");
        System.out.println("If you will not come, i will get it myself!");
        System.out.println("I know the sword is located at coordinates 2x and 7y");
        System.out.println("Just hope it's not too late");
        System.out.println();
        swordLocation = true;
        event = false;
        checkPosition();
    }
    
    private void goblinAmbush()
    {
        sword = true;
        System.out.println();
        System.out.println("With a flash, two goblins leaps from the trees and lands behind you");
        System.out.println("You notice one is equiped with a spear, and the other with a sword");
        System.out.println("What do you want to do?");
        System.out.println();
        goblinAmbush = true;
        getInfo();
    }
    
    private void notebook()
    {
        System.out.println("You current coordinates are: " + xPos + "x and " + yPos + "y");
        System.out.println();
        
        if (info1 == true && swordLocation == true)
        {
            System.out.println("You may find the sword at coordinates: 2x, 7y");
        }
        
        if(shieldLocation == true)
        {
            System.out.println("You may find the forgotten temple of the old gods at coordinates: -3x, 4y");
        }
        
        if(villageLocation == true)
        {
            System.out.println("You may find the village at coordinates: 5x, 0y");
        }
        
        if(shopLocation == true)
        {
            System.out.println("You may find the local shop at coordinates: 4x, -4y");
        }
        checkPosition();
    }
    
    private void resetInfo()
    {
        newInput = true;
        sword = false;
        shield = false;
        armor = false;
        map = false;
        event = false;
        cabinWindowBroken = false;
        info1 = false;
        info2 = false;
        swordLocation = false;
        shieldLocation = false;
        villageLocation = false;
        shopLocation = false;
        goblinAmbush = false;
        swordGoblinDead = false;
        ogreSleep = true;
        
        xPos = 0;
        yPos = 0;
        health = 100;
        coins = 0;
        healthPotion = 0;
        ogreHealth = 100;
        
        startGame();
    }
    
    private void died()
    {
        System.out.println();
        System.out.println("You have died and can not continue your adventure");
        System.out.println("The system will now reset your stats, and let you regain your life");
        System.out.println("May you survive your future adventures");
        System.out.println();
        resetInfo();
    }
}