/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package netcode.main;

/**
 *
 * @author HP
 */
public class Enemies extends Character{
    int playerXp;
    
    public Enemies(String name, int playerXp){
        super(name, (int) (Math.random()*playerXp + playerXp/3 + 5), (int) (Math.random()*(playerXp/4 + 2) + 1));
        this.playerXp = playerXp;
    }
    
    public Enemies(String name, int playerXp, int hp){
        super(name, hp, (int) (Math.random()*(playerXp/4 + 2) + 1));
        this.playerXp = playerXp;
    }
    @Override
    public int attack(){
        return (int) (Math.random()* (playerXp/6 + 3) + xp/6 + 5);
    } 
    @Override
    public int defend(){
        return (int) (Math.random()* (playerXp/4 + 1) + xp/4 + 3);
    }
}
