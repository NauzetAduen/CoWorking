package model;

public class UserSession {
    private final String username;
    private final UserTypes type;
    private final String name;

    public UserSession(String username, UserTypes type, String name) {
        this.username = username;
        this.name = name;
        this.type = type;
    }

    public UserTypes getType() {
        return type;
    }

    public String getName() {
        return name;
    }


    public String getUsername() {
        return username;
    }

    
    public String toString(){
        
        return "--->" + username + " --- " + type;
    }
    public boolean isAdmin(){
        
        return type == UserTypes.ADMIN;
    }
    
}
