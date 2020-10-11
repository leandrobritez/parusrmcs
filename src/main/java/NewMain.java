
import java.util.ArrayList;
import py.una.pol.par.parusrmcs.model.entity.User;
import py.una.pol.par.parusrmcs.repository.InMemUserRepository;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author mauricio
 */
public class NewMain {

    private static InMemUserRepository imur = new InMemUserRepository();

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        User u = new User();
        u.setName("Mauricio");
        u.setLastname("Machuca");
        u.setId(1);
//        imur.add(u);
        
        printUsers();
        
        u.setLastname("Machuca Cabral");
        u.setEmail("m.machuca@pol.una.py");
        u.setLoginName("m.machuca");
        u.setPasswd("12345");
//        imur.update(u);

        printUsers();
        
//        imur.remove(1);      
        
        System.out.println(imur.containsLoginPasswd("machu", "12345"));
        System.out.println(imur.containsLoginPasswd("m.machuca", "12345"));
        printUsers();
    }

    private static void printUsers() {
        ArrayList<User> users = (ArrayList<User>) imur.getAll();
        for (User user : users) {
            System.out.println("Usuario: " + user);
        }
    }

}
