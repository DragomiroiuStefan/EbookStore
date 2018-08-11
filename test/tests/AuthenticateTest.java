package tests;

import service.LogInService;

public class AuthenticateTest {
    
    public static void main(String[] args) {
        String msg = LogInService.authenticate(null, "gigi");
        System.out.println(msg);
        String msg2 = LogInService.authenticate("formuser", "formuser");
        System.out.println(msg2);
        String msg3 = LogInService.authenticate("casalot", "gigi");
        System.out.println(msg3);
        String msg4 = LogInService.authenticate("gigi", "casalot");
        System.out.println(msg4);
    }
    
}
