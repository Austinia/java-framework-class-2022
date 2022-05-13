package jejunu;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SimpleController implements Controller {

    @Override
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        //user 객체로 req.param의 id와 name을 넣음
        User user = new User();
        user.setId(Integer.parseInt(request.getParameter("id")));
        user.setName(request.getParameter("name"));
        //뷰 이름을 user로 지정
        ModelAndView modelAndView = new ModelAndView("user");
        //뷰에 객체 user를 추가
        modelAndView.addObject("user", user);

        return modelAndView;
    }
}
