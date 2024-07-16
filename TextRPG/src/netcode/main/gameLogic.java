
package netcode.main;
import java.io.IOException;
import java.util.Scanner;

public class gameLogic {
    static Scanner scanner = new Scanner(System.in);
    static Player player;
    static int userId;
    static int score;
    static boolean dead = false;
    static boolean exit = false;
    public static boolean isRunning;
    public static int place = 0, stage = 1;
    static scoreManager ScoreManager = new scoreManager();
    public static String[] places = {"The Forgotten Village", "The Enchanted Forest", "The Dark Citadel"};
    
    public static String[] encounter = {"Battle", "Battle", "Battle", "Rest", "Rest"};
    
    public static String[] enemy = {"Golem", "Goblin", "Slime", "Gargoyle", "King's Guard"};
    
    public static int readInt(String prompt, int userChoice){
        int input;
        do{
            System.out.println(prompt);
            try{
                input = Integer.parseInt(scanner.next());
            }catch(Exception e){
            input =-1;
            System.out.println("Invalid Input!");
            }
        }while(input < 1 || input > userChoice);
        return input;
    }
    
    public static void ClearTerminal(){
        for(int i = 1; i < 100; i++){
            System.out.println();
        }
    }
    public static void printSeperator(int n){
        for(int i=1; i<n; i++){
            System.out.print("-");
        }System.out.println();
    }
    public static void printHeading(String title){
        printSeperator(30);
        System.out.println(title);
        printSeperator(30);
    }
    
    public static void updateScore(int newScore) {
        score = newScore;
        ScoreManager.saveGameScore(userId, score);
    }
    
    
    public static void Continue(){
        System.out.println("\nEnter a key to start");
        try {
            System.in.read();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void Startgame(){
        boolean nameSet = false;
        String name;
        ClearTerminal();
        printSeperator(40);
        printSeperator(30);
        System.out.println("ADVENTURE OF THE CHOSEN ONE");
        
        Continue();
        
        do{
            System.out.println("Enter your name, The chosen one...");
            name = scanner.next();
            printHeading("Are you " +name+ ",The Chosen One?");
            System.out.println("(1) I am " +name);
            System.out.println("(2) I Refuse "+name+ " Change Your Name?");
            int input = readInt("-> ", 2);
            if(input == 1){
                nameSet = true;
            }
        }while(!nameSet);
        
        player = new Player(name);
        
        Story.printFirstActIntro();
        
        isRunning = true;
        
        gameLoop();
    }
    public static void printMenu(){
        ClearTerminal();
        printHeading(places[place]);
        System.out.println("Choose an action: ");
        System.out.println("(1) Continue on your adventure");
        System.out.println("(2) Character Info");
        System.out.println("(3) Exit");
        
    }
    
    public static void checkStage(){
        if(player.xp >= 10 && stage == 1){
            stage = 2;
            place = 1;
            player.chooseBuff();
            Story.printSecondActIntro();
            enemy[0] = "Goblin";
            enemy[1] = "Zombie";
            enemy[2] = "Mummy";
            enemy[3] = "Goblin Fire";
            enemy[4] = "Golem";
            
            encounter[0] = "Battle";
            encounter[1] = "Battle";
            encounter[2] = "Battle";
            encounter[3] = "Rest";
            encounter[4] = "Rest";
            
            player.hp = player.maxHp;
        }else if(player.xp >= 30 && stage == 2){
            stage = 3;
            place = 2;
            player.chooseBuff();
            Story.printThirdActIntro();
            
            enemy[0] = "King's Guard";
            enemy[1] = "Strong Brute";
            enemy[2] = "King's Henchman";
            enemy[3] = "Goblin Fire";
            enemy[4] = "Slime King";
            
            encounter[0] = "Battle";
            encounter[1] = "Battle";
            encounter[2] = "Battle";
            encounter[3] = "Rest";
            encounter[4] = "Rest";
            
            player.hp = player.maxHp;
            finalBattle();
        }
    }
    
    public static void randomEncounter(){
        int encounters = (int) (Math.random()* encounter.length);
        if(encounter[encounters].equals("Battle")){
            randomBattle();
        }else if(encounter[encounters].equals("Rest")){
            takeRest();
        }
    }
   
    public static void randomBattle(){
        ClearTerminal();
        printHeading("Prepare To Fight!");
        Continue();
        
        battle(new Enemies(enemy[(int)(Math.random()*enemy.length)], player.xp));
    }
    
    public static void battle(Enemies enemie){
        while(true){
            if (exit) {
                break;
            }
            else if (player.hp <= 0) {
                playerDead();
                break;
            } else {
                ClearTerminal();
                printHeading(enemie.name + "\nHP: " + enemie.hp +'/' + enemie.maxHp);
                printHeading(player.name + "\nHP: " + player.hp +'/' + player.maxHp);
                printSeperator(20);
                System.out.println("(1) Attack\n{2) Defend & Heal\n(3) Run away");
                int input = readInt("-> ", 3);
                
                if(input == 1){ //Attack
                int dmg = player.attack() - enemie.defend();
                int dmgGet = enemie.attack() - player.defend();
                if(dmgGet < 0){
                    dmg -= dmgGet/2;
                    dmgGet = 0;
                }
                if(dmg < 0){
                    dmg = 0;
                }
                player.hp -= dmgGet;
                enemie.hp -= dmg;
                ClearTerminal();
                printHeading("FIGHT!");
                System.out.println("You Dealt " + dmg + " Damage to " + enemie.name);
                printSeperator(15);
                System.out.println(enemie.name + " Dealt " + dmgGet + " Damage to you");
                Continue();
                
                if(player.hp <= 0){
                    playerDead();
                    
                    break;
                }else if(enemie.hp <= 0){
                    ClearTerminal();
                    printHeading("You Defeated " + enemie.name);
                    player.xp += enemie.xp;
                    System.out.println("You Got " + enemie.xp + " XP");
                    boolean addRest = (Math.random()*5 + 1 <= 2.25);
                    Continue();
                    break;
                    
                }
                
                
            }else if(input == 2){
                    ClearTerminal();
                    if(player.potion > 0 && player.hp < player.maxHp){
                        printHeading("Do you want to defend enemy attack and drink the potion? You have ("+ player.potion + " potion(s) left).");
                        System.out.println("(1) Yes\n(2) Mot Right Now");
                        input = readInt("-> ", 2);
                        if(input == 1){
                            player.hp = player.maxHp;
                            ClearTerminal();
                            printHeading("You Drank a Health Potion your health is restored to max");
                            player.potion--;
                            Continue();
                        }
                    }else{
                        printHeading("You don't have enough potion");
                        printHeading("Or your HP is max");
                        Continue();
                    }
                }else{
                    ClearTerminal();
                    if(stage != 3){
                        if(player.hp <= 0){
                                playerDead();
                                break;
                            }else{
                        if(Math.random()* 10 + 1 <= 3.5){
                            printHeading(player.name + " Ran away, COWARD!");
                            
                            gameLoop();
                            
                            
                         
                            
                        }else{
                            printHeading("YOU FAILED TO RUN!");
                            int dmgGet = enemie.attack();
                            System.out.println("You Took " +dmgGet+ " Damage");
                            player.hp -= dmgGet;
                            Continue();
                            
                        }
                        
                        }
                        
                        
                    }else{
                    System.out.println("THERE IS NO ESCAPE");
                    Continue();}
                }
            }
            
            
            
        }
    }
    
    public static void playerDead(){
        ClearTerminal();
        dead = true;
        System.out.println("You Died...");
        System.out.println("what a Tragic Fate..");
        isRunning = false;
        
    }
    
    public static void takeRest(){
        ClearTerminal();
        if(player.restsLeft >= 1){
            printHeading("Do you want to take a rest? You Have" +player.restsLeft+ " rest(s) left");
            System.out.println("(1) Yes\n(2)Not right now");
            int input = readInt("-> ", 2);
            if(input == 1){
                player.hp = player.maxHp;
                System.out.println("You took a rest and healed all your HP");
                player.restsLeft--;
            }
        } Continue();
    }
    
    public static void continueJourney(){
        checkStage();
        if(stage != 3){
        randomEncounter();}
        
    }
    
    public static void characterInfo(){
        ClearTerminal();
        printHeading("Character Info");
        System.out.println(player.name + "\tHP" + player.hp + "/" + player.maxHp);
        printSeperator(20);
        System.out.println("XP: " + player.xp);
        printSeperator(20);
        System.out.println("You have " +player.potion+ " Potion(s) left");
        printSeperator(20);
        
        if(player.numAtkBuff > 0){
            System.out.println("Attack Buff: " + player.atkBuff[player.numAtkBuff - 1]);
            printSeperator(20);
        }
        if(player.numDefBuff > 0){
            System.out.println("Attack Buff: " + player.defBuff[player.numDefBuff - 1]);
        }
        Continue();
    }
        
    
    public static void finalBattle(){
        battle(new Enemies("THE YIN KING", 100, 100));
        Story.printEnding(player, dead);
        isRunning = false;
        if(dead == false){
            int finalScore = 30000;
            updateScore(finalScore);
        }
    }
    
    public static void gameLoop(){
        while(isRunning){
            printMenu();
            int input = readInt("-> " ,3 );
            if(input == 1){
                continueJourney();
            }else if(input == 2){
                characterInfo();
            }else{
                exit = true;
                break;
            }
        }
    }
    
    public gameLogic(int userId) {
        this.userId = userId;
        this.ScoreManager = new scoreManager();
    }
    
}
