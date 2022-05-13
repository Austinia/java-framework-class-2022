package jejunu;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/user2")
public class UserController {
    @RequestMapping
    public ModelAndView user(@RequestParam("id") Integer id,
                             @RequestParam("name") String name
    ){
        //user 객체로 req.param의 id와 name을 넣음
        User user = new User();
        user.setId(id);
        user.setName(name);
        //뷰 이름을 user로 지정
        ModelAndView modelAndView = new ModelAndView("user");
        //뷰에 객체 user를 추가
        modelAndView.addObject(user);
        return modelAndView;
    }
}
