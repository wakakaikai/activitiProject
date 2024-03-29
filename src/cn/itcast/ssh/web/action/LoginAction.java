package cn.itcast.ssh.web.action;

import cn.itcast.ssh.domain.Employee;
import cn.itcast.ssh.service.IEmployeeService;
import cn.itcast.ssh.utils.SessionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

/**   
 * @Description:跳转到登陆首页，包括登陆相关的Action
 * @author: liuzk 
 * @date: 2019年2月20日 上午10:35:52     
 */
@SuppressWarnings("serial")
public class LoginAction extends ActionSupport implements ModelDriven<Employee> {

	private Employee employee = new Employee();
	
	@Override
	public Employee getModel() {
		return employee;
	}
	
	private IEmployeeService employeeService;

	public void setEmployeeService(IEmployeeService employeeService) {
		this.employeeService = employeeService;
	}


	/**
	 * 登录
	 * @return
	 */
	public String login(){
		//1.获取用户名
		String name = employee.getName();
		//2.使用用户名作为查询条件，查询员工表，获取当前用户名的用户信息
		Employee emp = employeeService.findEmployeeByName(name);
		//3.将查询的对象（唯一）放置到Session中
		SessionContext.setUser(emp);
		return "success";
	}
	
	/**
	 * 标题
	 * @return
	 */
	public String top() {
		return "top";
	}
	
	/**
	 * 左侧菜单
	 * @return
	 */
	public String left() {
		return "left";
	}
	
	/**
	 * 主页显示
	 * @return
	 */
	public String welcome() {
		return "welcome";
	}
	
	/**    
	 * 退出系统  
	 * @return           
	 */
	public String logout(){
		//清空Session
		SessionContext.setUser(null);
		return "login";
	}
}
