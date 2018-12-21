package com.saeyan.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.saeyan.dto.ProductDTO;

public class ProductDAO
{
	Connection conn = null;
	PreparedStatement psmt = null;
	ResultSet rs = null;
	
	private ProductDAO()
	{
		
	}
	
	private static ProductDAO instance = new ProductDAO();
	
	public static ProductDAO getInstance()
	{
		return instance;
	}
	
	public ArrayList<ProductDTO> selectAllProducts()
	{
		ArrayList<ProductDTO> list = new ArrayList<ProductDTO>();
		
		// 최신 등록된 항목부터 화면에 보여주기 위해 내림차순으로 작성해준다.
		String sql = "SELECT * FROM PRODUCT ORDER BY CODE DESC";
		
		try
		{
			conn = this.getConnection();
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();
			
			while(rs.next())
			{
				ProductDTO product = new ProductDTO();
				
				product.setCode(rs.getInt("code"));
				product.setName(rs.getString("name"));
				product.setPrice(rs.getInt("price"));
				product.setPictureUrl(rs.getString("pictureUrl"));
				product.setDescription(rs.getString("description"));
				
				list.add(product);
			}
		} 
		catch (SQLException e)
		{
			e.printStackTrace();
		} 
		finally
		{
			closeDatabaseResource();
		}
		
		return list;
		
	}
	
	private void closeDatabaseResource()
	{
		try
		{
			if(conn != null) conn.close();
			if(rs != null) rs.close();
			if(psmt != null) psmt.close();
		} catch (SQLException e)
		{
			e.printStackTrace();
		}
	}
	
	private Connection getConnection()
	{
		Connection conn = null;
		DataSource ds = null;
		try
		{
			Context context = new InitialContext();
			Context envContext = (Context) context.lookup("java:/comp/env");
			
			// jdbc/myoracle이란 이름의 객체를 찾아서 DataSource가 받는다.
			ds = (DataSource) envContext.lookup("jdbc/myoracle");
			
			conn = ds.getConnection();
		} 
		catch (NamingException e)
		{
			e.printStackTrace();
		} catch (SQLException e)
		{
			e.printStackTrace();
		}
		
		return conn;
	}

}
