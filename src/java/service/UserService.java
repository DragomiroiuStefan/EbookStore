package service;

import dao.UserDao;

public class UserService {
    
    public static boolean isAdmin(String username) {
        String role = UserDao.getRole(username);
        return role.equals("admin");
    }
    
}
