package test.service;

import java.util.List;

import test.bean.GoodsVO;

// 목차 열할
public interface GoodsService {
	public int insertGoods(GoodsVO vo); // 책 등록
	public int updateGoods(GoodsVO vo);	// 책수정
	public int deleteGoods(String code); // 책삭제
	public GoodsVO getGoods(String code); // 책 1권 검색
	public List<GoodsVO> getGoodsList();	// 책 목록
}
