package com.kh.BankVODAO;

import oracle.sql.DATE;

/**
 * 		Model 
 */
public class BankVO {
	private int account_id;
	private String account_number;
	private String account_name;
	private double balance;
	private String branch_name;
	private DATE last_transaction;
	
	
	public int getAccount_id() {
		return account_id;
	}
	
	public String getAccount_number() {
		return account_number;
	}
	
	public String getAccount_name() {
		return account_name;
	}
	
	public double getBalance() {
		return balance;
	}
	
	public String getBranch_name() {
		return branch_name;
	}
	
	public DATE getLast_transaction() {
		return last_transaction;
	}
	
	public void setAccount_id(int account_id) {
		this.account_id = account_id;
	}

	public void setAccount_number(String account_number) {
		this.account_number = account_number;
	}

	public void setAccount_name(String account_name) {
		this.account_name = account_name;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public void setBranch_name(String branch_name) {
		this.branch_name = branch_name;
	}

	public void setLast_transaction(DATE last_transaction) {
		this.last_transaction = last_transaction;
	}
}
