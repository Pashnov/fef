package university.constant.user;

/**
 * Created by Andrii_Pashnov on 26.12.2014 11:16.
 */
public enum UserRole {
    STUDENT, LECTURER, ADMIN;

    public static UserRole getRole(String name){
        UserRole role;
        try{
            role = valueOf(name.toUpperCase());
        }catch (IllegalArgumentException e){
            role = STUDENT;
        }
        return role;
    }
}
