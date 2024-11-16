package chornarin.com.kh.TP_Operating_System.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import org.springframework.ui.Model;

@Controller
public class UserController {

    @GetMapping("/TP04/task1")
    public String getMethodName() {
        return "task1";
    }

    
    @GetMapping("/TP04/task2")
    public String getLogin() {
        return "task2";
    }

    @GetMapping("/TP04/task3")
    public String getSignup() {
        return "task3";
    }

    @GetMapping("/TP04/task4")
    public String getListForm() {
        return "task4";
    }

    @GetMapping("/TP04/task5")
    public String getValidation() {
        return "task5";
    }

    @GetMapping("/dashboard")
    public String dashboard() {
        return "dashboard"; 
    }
    
    @PostMapping("/TP04/task5")
    public String Validation(@RequestParam("username") String username, @RequestParam("password") String password, Model model) {
        if("Chornarin".equals(username) && "123456".equals(password)){
            return "redirect:/dashboard";
        }else{
            model.addAttribute("error", "Invalid username or password");
            return "task5";
        }   
    }

    
    

    
    

    
    
}
