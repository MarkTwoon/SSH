package com.chinasoft.SSHDemo1.action;
import com.opensymphony.xwork2.ActionSupport;
import com.chinasoft.SSHDemo1.model.Goods;
import com.chinasoft.SSHDemo1.model.User;
import com.chinasoft.SSHDemo1.service.UserService;

import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

//默认可以不写
@ParentPackage("struts-default")
//根命名空间,可以不写
@Namespace("/")
public class UserAction  extends ActionSupport{
	//前台传到后台需要set，后台传到前台需要get
    // 用户名-必须与页面请求的对应表单username值相同
    private String username;

    // 密码必须与页面请求的对应表单password值相同
    private String password;

    private String resultJson;

    /*
    * struts 接收参数三种方式：属性方式接收，JavaBean方式接收，ModelDriven方式接收参数,前两种都必须设置get和set方法
    * */
    //Spring 注入方式
    @Autowired
    private UserService userService;

    //@Action(value="login")
    @Action(value = "login", results = {
        @Result(name = "success", location = "/success.jsp", params = {"resultJson", "resultJson"}),
        @Result(name = "error", location = "/error.jsp")})
    public String execute() throws Exception
    {
        HttpServletResponse response = ServletActionContext.getResponse();
        HttpServletRequest request = ServletActionContext.getRequest();

        JSONObject result = new JSONObject();
        User user = userService.getByName(username);
        	 
       /*result.put("user", user); */

        if (user != null && user.getName().equals(username) && user.getPassword().equals(password))
        {
        	List<Object[]> list=userService.getByUser(username);
        	 for(Object[] params:list){
				 User user1=(User) params[0];
				Goods  goods=(Goods) params[1];
				request.setAttribute("user1", user1);
				request.setAttribute("goods", goods); 
			}  
         
           
        	
        	
        	result.put("message", "登录成功");
            result.put("status", "true");
            resultJson = result.toString();
            request.setAttribute("resultJson", resultJson);
            request.setAttribute("user", user);
            writeResponseData(request, response, result);
            return "success";
        }
        result.put("message", "登录失败");
        result.put("status", "false");
        resultJson = result.toString();
        writeResponseData(request, response, result);
        return "error";
    }

    private void writeResponseData(HttpServletRequest request, HttpServletResponse response, Object data)
    {
        response.setContentType("text/html;charset=utf-8");
        try
        {
            PrintWriter out = response.getWriter();
            out.println(data.toString());
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public String getUsername()
    {
        return username;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public String getResultJson()
    {
        return resultJson;
    }

    public void setResultJson(String resultJson)
    {
        this.resultJson = resultJson;
    }
}
