package lk.uva.uvateafactory.controller;

import lk.uva.uvateafactory.dao.UserDao;
import lk.uva.uvateafactory.entity.Privilage;
import lk.uva.uvateafactory.entity.User;
import lk.uva.uvateafactory.entity.Userrole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(value = "/authorities")
public class UserAuthorityController {

    @Autowired
    private UserDao userdao;

    @GetMapping("/{username}")
    @ResponseStatus(HttpStatus.CREATED)
    public List<String> getUserAuthoritiesByUsername(@PathVariable String username) {
        User user = userdao.findByUsername(username);
        List<String> authorities = new ArrayList<>();

        if (user != null){
            List<Userrole> userroles = (List<Userrole>) user.getUserroles();

            for (Userrole u : userroles) {
                List<Privilage> privilages = (List<Privilage>) u.getRole().getPrivilages();
                for (Privilage p : privilages) {
                    String authority = p.getAuthority();
                    authorities.add(authority);
                }
            }
        }else{
            authorities = Arrays.asList("employee-insert","user-select","user-delete","user-update","user-insert",
                                        "privilege-select","privilege-delete","privilege-update","privilege-insert",
                                        "area-select","area-insert","area-update","area-delete"
            );
        }

        return authorities;
    }


}
