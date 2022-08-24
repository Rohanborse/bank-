package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;

import com.model.User;
import com.util.MyDatabase;

public class UserDao {
	public int[] createAccount(User u) {
		int[] a = new int[2];
		a[1] = generatePin();
		Connection con = new MyDatabase().createConnection();
		PreparedStatement pst = null;
		String sql = "insert into user1(uacname,uactype,uacpin,uacbalance)values(?,?,?,?)";
		try {
			pst = con.prepareStatement(sql);
			pst.setString(1, u.getUacname());
			pst.setString(2, u.getUactype());
			pst.setInt(3, a[1]);
			pst.setDouble(4, u.getUacbalance());
			if (pst.executeUpdate() > 0) {
				a[0] = getMaxAccountNumber();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			new MyDatabase().closeConnection(pst, con);
		}
		return a;
	}

	private int generatePin() {
		String s = new Integer(new Random().nextInt(9999)).toString();
		String s1 = s;
		for (int i = 4; i > s1.length(); i--)
			s += "0";
		return Integer.parseInt(s);
	}

	private int getMaxAccountNumber() {
		int n = 0;
		Connection con = new MyDatabase().createConnection();
		PreparedStatement pst = null;
		ResultSet rs = null;
		String sql = "select max(uacid) as uacid from user1";
		try {
			pst = con.prepareStatement(sql);
			rs = pst.executeQuery();
			while (rs.next())
				n = (int) rs.getObject("uacid");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			new MyDatabase().closeConnection(rs, pst, con);
		}
		return n;
	}

	public double checkBalance(int uacid, int uacpin) {
		double balance = 0;
		Connection con = new MyDatabase().createConnection();
		PreparedStatement pst = null;
		ResultSet rs = null;
		String sql = "select uacbalance from user1 where uacid=? and uacpin=?";
		try {
			pst = con.prepareStatement(sql);
			pst.setInt(1, uacid);
			pst.setInt(2, uacpin);
			rs = pst.executeQuery();
			while (rs.next())
				balance = (double) rs.getObject("uacbalance");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			new MyDatabase().closeConnection(rs, pst, con);
		}
		return balance;
	}

	public double depositeAmount(int uacid, int uacpin, double amount) {
		amount += checkBalance(uacid, uacpin);
		Connection con = new MyDatabase().createConnection();
		PreparedStatement pst = null;
		String sql = "update user1 set uacbalance=? where uacid=? and uacpin=?";
		try {
			pst = con.prepareStatement(sql);
			pst.setDouble(1, amount);
			pst.setInt(2, uacid);
			pst.setInt(3, uacpin);
			pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			new MyDatabase().closeConnection(pst, con);
		}
		return amount;
	}

	public double withdrawlAmount(int uacid, int uacpin, double amount) {
		amount = checkBalance(uacid, uacpin) - amount;
		if (amount < 0) {
			amount = 0;
			System.out.println("insufficient funds");
		} else {
			Connection con = new MyDatabase().createConnection();
			PreparedStatement pst = null;
			String sql = "update user1 set uacbalance=? where uacid=? and uacpin=?";
			try {
				pst = con.prepareStatement(sql);
				pst.setDouble(1, amount);
				pst.setInt(2, uacid);
				pst.setInt(3, uacpin);
				pst.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				new MyDatabase().closeConnection(pst, con);
			}
		}
		return amount;
	}

	public User diplayAccountDetails(int uacid, int uacpin) {
		User u = null;
		Connection con = new MyDatabase().createConnection();
		PreparedStatement pst = null;
		ResultSet rs = null;
		String sql = "select uacname,uactype,uacid,uacbalance from user1 where uacid=? and uacpin=?";
		try {
			pst = con.prepareStatement(sql);
			pst.setInt(1, uacid);
			pst.setInt(2, uacpin);
			rs = pst.executeQuery();
			while (rs.next()) {
				u = new User();
				u.setUacid((int) rs.getObject("uacid"));
				u.setUacname((String) rs.getObject("uacname"));
				u.setUactype((String) rs.getObject("uactype"));
				u.setUacbalance((double) rs.getObject("uacbalance"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			new MyDatabase().closeConnection(rs, pst, con);
		}
		return u;
	}
	public boolean validateAccount(int uacid, int uacpin) {
		boolean b = false;
		Connection con = new MyDatabase().createConnection();
		PreparedStatement pst = null;
		ResultSet rs = null;
		String sql = "select uacbalance from user1 where uacid=? and uacpin=?";
		try {
			pst = con.prepareStatement(sql);
			pst.setInt(1, uacid);
			pst.setInt(2, uacpin);
			rs = pst.executeQuery();
			while (rs.next())
				b = true;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			new MyDatabase().closeConnection(rs, pst, con);
		}
		return b;
	}
}
