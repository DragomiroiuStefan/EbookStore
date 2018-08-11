package service;

import dao.UserDao;

public class LogInService {
    
    public static String authenticate(String username, String password) {
        if (username == null || username.isEmpty() || password == null || password.isEmpty())
            return "Please insert username and password ";
        String retrievedPassword = UserDao.getPassword(username);
        if (retrievedPassword == null)
            return "This account does not exist";
        else if (!retrievedPassword.equals(password))
            return "Password is incorrect";
        else 
            return "ok";
            
    }
    
}
