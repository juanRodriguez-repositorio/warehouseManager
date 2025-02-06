/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author kamus
 */
public class User {

    private String username;
    private String password;
    
    public User(String name,String password){
         this.setName(name);
         this.setPassword(password);
    }
    public User(){}

    public String getName() {
        return username;
    }

    public void setName(String name) {
        this.username = name;
    }

    public void setPassword(String password){
        this.password = password;
    }
    public String getPassword(){
        return password;
    }
    @Override
    public String toString() {
        return "User{" +
                "username='" + this.getName() + '\'' +
                ", password='" + this.getPassword() + '\'' + 
                '}';
    }
    

   
}
