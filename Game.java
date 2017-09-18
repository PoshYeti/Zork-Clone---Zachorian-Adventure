/**
 * @author (Richard W. Zacho)
 * @version (1.0.1.0)
 */

import java.util.Scanner;

public class Game
{
    Scanner info = new Scanner(System.in);
    //The X and Y pos of the player
    int xPos, yPos, health, coins, healthPotion;
    //True for new direction, or items, or if the switch is related to an event
    boolean newInput, sword, shield, armor, map, event, cabinWindowBroken;
    //Different knowledge you can gain/display
    boolean info1, info2;
    //Location 1 = sword, 2 = shield, 3 = old temple, 4 = village
    boolean swordLocation, shieldLocation, villageLocation, info4Location;
    //goblin ambush bools
    boolean goblinAmbush, swordGoblinDead;
    //boolean to prevent an event to be forced again if you write the wrong direction/illegal input
    boolean noEventStart;
    
    //constructor for the game data
    public Game()
    {
        newInput = true;
        sword = false;
        shield = false;
        armor = false;
        map = false;
        event = false;
        cabinWindowBroken = false;
        noEventStart = false;
        info1 = false;
        info2 = false;
        swordLocation = false;
        shieldLocation = false;
        villageLocation = false;
        info4Location = false;
        goblinAmbush = false;
        swordGoblinDead = false;
        
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
        //Cabin event
        if (xPos == 1 && yPos == -1 && noEventStart == false)
        {
            System.out.println();
            System.out.println("You've found a cabin");
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
                        //Not going to get into the cabin, so continue on the exploration
                        getInfo();
                        break;
                    case "HINT":
                        System.out.println("These commands are available: ");
                        System.out.println("Knock, Enter, Break Window, Yell, Drink Potion, Leave");
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
                            sword = true;
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
                        //Not going to get into the cabin, so continue on the exploration
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
            while (goblinAmbush = true)
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
                        //Not going to get into the cabin, so continue on the exploration
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
                        //Not going to get into the cabin, so continue on the exploration
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
                        //Not going to get into the cabin, so continue on the exploration
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
                        //Not going to get into the cabin, so continue on the exploration
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
                        //Not going to get into the cabin, so continue on the exploration
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
            //village event
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
                        //Not going to get into the cabin, so continue on the exploration
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
                        System.out.println("You decided to leave the boat resting on the shore");
                        System.out.println();
                        //stops the event
                        event = false;
                        //Not going to get into the cabin, so continue on the exploration
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
                        //Not going to get into the cabin, so continue on the exploration
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
        else if (shield == true)
        {
            System.out.println("---------Shield Equipped-----------");
        }
        else if (armor == true)
        {
            System.out.println("---------Armor Equipped-----------");
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
        System.out.println("You find 4 coins underneath the piece of paper");
        coins += 4;
        swordLocation = true;
        event = false;
        getInfo();
    }
    
    private void goblinAmbush()
    {
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
        
        else if(shieldLocation == true)
        {
            System.out.println("You may find the forgotten temple of the old gods at coordinates: -3x, 4y");
        }
        
        else if(villageLocation == true)
        {
            System.out.println("You may find the village at coordinates: 5x, 0y");
        }
        
        else if(info4Location == true)
        {
            System.out.println("");
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
        info4Location = false;
        goblinAmbush = false;
        swordGoblinDead = false;
        
        xPos = 0;
        yPos = 0;
        health = 100;
        coins = 0;
        healthPotion = 0;
        
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