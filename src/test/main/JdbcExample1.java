package test.main;

import java.util.List;

import org.springframework.context.support.GenericXmlApplicationContext;

import test.bean.GoodsVO;
import test.service.GoodsService;

public class JdbcExample1 {
	public static void main(String[] args) {
		// 1. 스프링 컨테이너 구동
		GenericXmlApplicationContext context = 
				new GenericXmlApplicationContext("bean.xml");
		// 2. 서비스 객체 얻기
		GoodsService service = (GoodsService) context.getBean("service");
		// 3. 책 등록 기능 테스트
		GoodsVO vo = new GoodsVO();
		vo.setCode("p0001");
		vo.setName("Java");
		vo.setPrice(20000);
		vo.setMaker("한삼출판사");
		
		int result = service.insertGoods(vo);
		if(result > 0) {
			System.out.println("저장 성공");
		}else {
			System.out.println("저장 실패");
		}
		// 4. 책 목록 검색 기능 테스트
		List<GoodsVO> list = service.getGoodsList();
		for(GoodsVO goodsVO : list) {
			System.out.println(goodsVO.toString());
		}
		// 5. 스프링 컨테이너 종료
		context.close();
	}
}
