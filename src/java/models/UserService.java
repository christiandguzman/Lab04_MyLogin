
package models;

/**
 *
 * @author 683676
 */
public class UserService {
    
    
    public static User login(String username,String password){
        if ( username.equalsIgnoreCase("adam")
                && password.equalsIgnoreCase("password")){
            User user = new User(username,password);
            return user;
        }else {
            return null;
        }
    }
    
}
