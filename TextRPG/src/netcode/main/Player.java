/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package netcode.main;


public class Player extends Character{
    public int numAtkBuff, numDefBuff;
    
    int restsLeft,potion;
    
    public String[] atkBuff = {"Strength", "Strength II", "Godlike Power", "Fiery Fist"};
    public String[] defBuff = {"Iron Skin", "Blut Vene", "Hierro", "Zankt Swinger"};
     
    public Player(String name){
        super(name, 100, 0);
        this.numAtkBuff = 0;
        this.numDefBuff = 0;
        this.restsLeft = 1;
        this.potion = 10;
        
        chooseBuff();
    }
    
    @Override
    public int attack(){
        return (int) (Math.random()*(xp/4 + numAtkBuff* 3 + 3) + xp/10 + numAtkBuff * 2 + numDefBuff + 1);
    }
    
    @Override
    public int defend(){
        return (int) (Math.random()*(xp/4 + numDefBuff* 3 + 3) + xp/10 + numDefBuff * 2 + numAtkBuff + 1);
    }
    
    public void chooseBuff(){
        gameLogic.ClearTerminal();
        gameLogic.printHeading("Choose a buff");
        System.out.println("(1)" + atkBuff[numAtkBuff]);
        System.out.println("(2)" + defBuff[numDefBuff]);
        
        int input = gameLogic.readInt("-> ", 2);
        gameLogic.ClearTerminal();
        
        if(input == 1){
            gameLogic.printHeading("You Got " +atkBuff[numAtkBuff] + "!");
            numAtkBuff++;
        }else{
            gameLogic.printHeading("You Got " +defBuff[numDefBuff] + "!");
            numDefBuff++;
        }
        gameLogic.Continue();
    }
}
