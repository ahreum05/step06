package test.main;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.stereotype.Component;

import test.service.GoodsService;

@Component("main")
public class JdbcExample2 {
	@Autowired
	GoodsService service;
	
	void test() {
		String code = "p0001";
		int result = service.deleteGoods(code);
		if(result > 0) {
			System.out.println("삭제 성공");
		}else {
			System.out.println("삭제 실패");
		}
	}
	
	public static void main(String[] args) {
		GenericXmlApplicationContext context = 
				new GenericXmlApplicationContext("bean.xml");
		
		JdbcExample2 main = (JdbcExample2) context.getBean("main");
		main.test();
		
		context.close();
	}
}
