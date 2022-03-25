package kr.ac.jejunu;

public class User { //그릇
    private Integer id; //setter getter 만들기 귀찮지만..
    private String name;
    private String password; //왜 프라이빗?

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
