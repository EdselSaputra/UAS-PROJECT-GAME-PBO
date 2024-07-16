/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package netcode.main;

/**
 *
 * @author HP
 */
public class Story {
    public static void printFirstActIntro(){
        gameLogic.ClearTerminal();
        gameLogic.printSeperator(20);
        System.out.println("ACT 1//FIRST STAGE");
        gameLogic.printSeperator(20);
        System.out.println("In the mystical land of Auroria, an ancient village named Eldoria lies shrouded in mystery and darkness. Once a thriving community, Eldoria is now abandoned and overrun with dark creatures. The player, a young hero named Aeloria, begins their journey here.");
        System.out.println("Aeloria, equipped with a simple sword and shield, learns from a village elder about the village’s dark curse. This curse, cast by the malevolent sorcerer Malakar, has spread shadows and chaos across the land. To lift the curse, Aeloria must find and restore the three fragments of the lost Amulet of Light, which can break Malakar's dark spell.");
        System.out.println("Aeloria must navigate through the village, battling dark creatures and solving puzzles to find the first fragment of the Amulet of Light hidden in the ruins of Eldoria’s ancient temple. Along the way, she encounters various NPCs who provide guidance and lore about the curse and the amulet.");
        gameLogic.Continue();
    }
    public static void printSecondActIntro(){
        gameLogic.ClearTerminal();
        gameLogic.printSeperator(20);
        System.out.println("ACT 2//SECOND STAGE");
        gameLogic.printSeperator(20);
        System.out.println("Beyond Eldoria lies the Enchanted Forest, a once-beautiful and vibrant woodland now twisted by Malakar's dark magic. The forest is filled with treacherous paths, mystical creatures, and hidden traps.");
        System.out.println("With the first fragment in hand, Aeloria ventures into the forest to find the second fragment. The forest is under the control of Malakar's minions, who seek to prevent anyone from restoring the Amulet of Light. Aeloria must navigate through enchanted mazes and confront magical foes.");
        System.out.println("Aeloria must explore the depths of the forest, following cryptic clues and interacting with forest spirits who assist her in exchange for completing various tasks. These tasks often involve solving nature-based puzzles or rescuing captured creatures. The second fragment is hidden within the heart of the forest, protected by ancient guardians.");
        gameLogic.Continue();
    }
    public static void printThirdActIntro(){
        gameLogic.ClearTerminal();
        gameLogic.printSeperator(20);
        System.out.println("ACT 3//THIRD AND FINAL STAGE");
        gameLogic.printSeperator(20);
        System.out.println("The final stage is Malakar’s stronghold, the Dark Citadel, a fortress of dark magic and twisted architecture. The citadel is filled with traps, dark knights, and powerful spells.");
        System.out.println("Aeloria must fight her way through the citadel, solving complex puzzles and facing increasingly difficult enemies. Along the way, she finds scrolls and artifacts that reveal Malakar’s backstory and the origins of his malevolent power. As she reaches the top of the citadel, she combines the two fragments to form a nearly complete Amulet of Light, which reveals the path to Malakar’s chamber.");
        System.out.println("In the final confrontation, Aeloria faces Malakar, who wields dark magic and summons powerful creatures to aid him. The battle is intense and multi-phased, requiring Aeloria to use all her skills and the power of the Amulet of Light. Upon defeating Malakar, Aeloria retrieves the final fragment and completes the Amulet of Light");
        gameLogic.Continue();
    }
    public static void printEnding(Player player, boolean dead){
        
        if (dead) {
            gameLogic.ClearTerminal();
            gameLogic.printSeperator(30);
            System.out.println("You Died...");
            System.out.println("what a Tragic Fate..");
        } else {
            gameLogic.ClearTerminal();
            gameLogic.printSeperator(30);
            System.out.println("You Did It, " +player.name + " With the fully restored Amulet of Light, Aeloria lifts the curse from Eldoria and the Enchanted Forest, restoring peace and balance to Auroria. The game ends with a grand celebration in Eldoria, where Aeloria is hailed as the hero who saved the land from darkness.");
            gameLogic.printSeperator(30);
        }
    }
}
