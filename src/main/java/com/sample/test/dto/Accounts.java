package com.sample.test.dto;

import java.util.List;

public class Accounts {
	private boolean isPrimary;
	private long accountId;
	private long balance;
	private List<Transactions> transaction;
	public boolean isPrimary() {
		return isPrimary;
	}
	public void setPrimary(boolean isPrimary) {
		this.isPrimary = isPrimary;
	}
	public long getAccountId() {
		return accountId;
	}
	public void setAccountId(long accountId) {
		this.accountId = accountId;
	}
	public long getBalance() {
		return balance;
	}
	public void setBalance(long balance) {
		this.balance = balance;
	}
	public List<Transactions> getTransaction() {
		return transaction;
	}
	public void setTransaction(List<Transactions> transaction) {
		this.transaction = transaction;
	}
	

}
