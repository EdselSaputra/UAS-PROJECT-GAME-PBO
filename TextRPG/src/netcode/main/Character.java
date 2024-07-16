/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package netcode.main;

/**
 *
 * @author HP
 */
public abstract class Character {
    public String name;
    public int maxHp,hp,xp;
    
    public Character(String name,int maxHp, int xp){
        this.name = name;
        this.maxHp = maxHp;
        this.xp = xp;
        this.hp = maxHp;
    }
    
    public abstract int attack();
    public abstract int defend();
}
