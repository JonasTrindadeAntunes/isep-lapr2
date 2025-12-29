package app.domain.model.Platform;

import pt.isep.lei.esoft.auth.AuthFacade;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Register created as a workaround to the fact that the authfacade class was not serializable.
 */
public class RegisterUsers implements Serializable {

    /**
     * The User list.
     */
    private List<User> userList;

    /**
     * The UserRole list.
     */
    private List<UserRole> roleList;

    /**
     * The Auth facade.
     */
    public transient AuthFacade authFacade;

    /**
     * Instantiates a new Register users.
     *
     * @param authFacade the auth facade
     */
    public RegisterUsers(AuthFacade authFacade){
        this.authFacade = authFacade;
        userList = new ArrayList<>();
        roleList = new ArrayList<>();
    }

    /**
     * Add user .
     *
     * @param name  the name
     * @param email the email
     * @param pwd   the pwd
     * @param role  the role
     * @return the boolean
     */
    public boolean addUser(String name, String email, String pwd,String role){
        this.getAuthFacade().addUserWithRole(name,email,pwd,role);
        return userList.add(new User(name,email,pwd,role));
    }

    /**
    Used to register users running the after running the deserialize function.
     */
    private boolean registerUsers(){
        for (User u : userList){
            System.out.println(u.email+ " " +u.pwd);
            String[] rolesArr = u.roles.toArray(new String[0]);
            authFacade.addUserWithRoles(u.name,u.email,u.pwd, rolesArr);
        }
        return true;
    }

    /**
     * Adds users roles to the list of roles.
     */
    private boolean registerUserRoles(){
        for(UserRole ur : roleList){
            authFacade.addUserRole(ur.id,ur.designation);
        }
        return true;
    }

    /**
     * Add user role.
     *
     * @param id          the id
     * @param description the description
     * @return the boolean
     */
    public boolean addUserRole(String id, String description) {
        UserRole role = new UserRole(id,description);
        this.roleList.add(role);
        return this.authFacade.addUserRole(id,description);
    }

    /**
     * Gets authFacade.
     *
     * @return the authFacade
     */
    public AuthFacade getAuthFacade() {
        return this.authFacade;
    }

    /**
     * When reading the object also reset the authfacade
     * @param in object input stream
     * @throws IOException signals that an I/O exception of some sort has occured.
     * @throws ClassNotFoundException thrown when the application tried to load a class through its string name, but it cannot be found
     */
    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException{
        this.roleList = (List<UserRole>) in.readObject();
        this.userList = (List<User>) in.readObject(); // read the object data
        this.authFacade = new AuthFacade(); //initialize a new auth facade
        this.registerUserRoles(); //register all the user roles
        this.registerUsers(); //register all users
    }


    /**
     * The type User role.
     */
    protected class UserRole implements  Serializable{
        String id;
        String designation;

        public UserRole(String id, String designation){
            this.id = id;
            this.designation = designation;
        }

    }

    /**
     * The type User.
     */
    protected class User implements Serializable{

        String name;
        String email;
        String pwd;
        List<String> roles;

        public User(String name, String email, String pwd, String roleId){
            this.roles = new ArrayList<>();
            this.name = name;
            this.email = email;
            this.pwd = pwd;
            this.roles.add(roleId);
        }
    }
}
