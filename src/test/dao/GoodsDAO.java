package test.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import test.bean.GoodsVO;

@Repository	// bean 객체 설정
public class GoodsDAO {
	Connection conn;
	PreparedStatement pstmt;
	ResultSet rs;

	String url = "jdbc:oracle:thin:@localhost:1521:xe";
	String user = "C##jspexam";
	String password = "m1234";

	// sql
	String goods_insert = "insert into goods values (?, ?, ?, ?)";
	String goods_update = "update goods set name=?, price=?, maker=? " + " where code=?";
	String goods_delete = "delete goods where code=?";
	String goods_get = "select * from goods where code=?";
	String goods_list = "select * from goods";

	public GoodsDAO() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public Connection getConnection() {
		try {
			conn = DriverManager.getConnection(url, user, password);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}

	public void close() {
		try {
			if (rs != null)
				rs.close();
			if (pstmt != null)
				pstmt.close();
			if (conn != null)
				conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// 책 등록
	public int insertGoods(GoodsVO vo) {
		int result = 0;
		conn = getConnection();
		try {
			pstmt = conn.prepareStatement(goods_insert);
			pstmt.setString(1, vo.getCode());
			pstmt.setString(2, vo.getName());
			pstmt.setInt(3, vo.getPrice());
			pstmt.setString(4, vo.getMaker());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return result;
	}

	// 책 수정
	public int updateGoods(GoodsVO vo) {
		int result = 0;
		conn = getConnection();
		try {
			pstmt = conn.prepareStatement(goods_update);
			pstmt.setString(1, vo.getName());
			pstmt.setInt(2, vo.getPrice());
			pstmt.setString(3, vo.getMaker());
			pstmt.setString(4, vo.getCode());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return result;
	}

	// 책 삭제
	public int deleteGoods(String code) {
		int result = 0;
		conn = getConnection();
		try {
			pstmt = conn.prepareStatement(goods_delete);
			pstmt.setString(1, code);

			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return result;
	}

	// 책 1권 검색
	public GoodsVO getGoods(String code) {
		GoodsVO vo = null;
		conn = getConnection();
		try {
			pstmt = conn.prepareStatement(goods_get);
			pstmt.setString(1, code);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				vo = new GoodsVO();
				vo.setCode(rs.getString("code"));
				vo.setName(rs.getString("Name"));
				vo.setPrice(rs.getInt("price"));
				vo.setMaker(rs.getString("maker"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return vo;
	}

	// 책 목록
	public List<GoodsVO> getGoodsList() {
		List<GoodsVO> list = new ArrayList<GoodsVO>();
		conn = getConnection();
		try {
			pstmt = conn.prepareStatement(goods_list);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				GoodsVO vo = new GoodsVO();
				vo.setCode(rs.getString("code"));
				vo.setName(rs.getString("name"));
				vo.setPrice(rs.getInt("price"));
				vo.setMaker(rs.getString("maker"));
				// 리스트에 저장
				list.add(vo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return list;
	}
}
