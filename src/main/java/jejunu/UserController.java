package jejunu;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.*;

@Controller
//@RequestMapping("/api") parent형식으로 동작
public class UserController {
    //user GET
    @RequestMapping("/user")
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

    //upload GET
    @RequestMapping("/upload") //default로 같은 뷰네임 인식해서 전달
    public void upload() {

    }

    //upload POST
    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public ModelAndView upload(@RequestParam MultipartFile file, HttpServletRequest request) throws IOException {
        //파일 전송 구현
        File path = new File(request.getServletContext().getRealPath("/") + "/WEB-INF/static/" + file.getOriginalFilename());
        FileOutputStream fileOutputStream = new FileOutputStream(path); //자바의 파일하고 스트림으로 연결
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(fileOutputStream);//바이트 하나 루프 돌리면서 하는 것 보다 버퍼로 묶어서 전송이 빠르다
        bufferedOutputStream.write(file.getBytes()); //파일 바이트로 받게 파일 아웃풋을 쓰기모드로 열고
        bufferedOutputStream.close();
        ModelAndView modelAndView = new ModelAndView("upload");
        modelAndView.addObject("url", "/images/" + file.getOriginalFilename());
        return modelAndView;
    }

    //에러 핸들링
    @ExceptionHandler(Exception.class)
    public ModelAndView error(Exception e) {
        ModelAndView modelAndView = new ModelAndView("error");
        modelAndView.addObject("e", e);
        return modelAndView;
    }


}
