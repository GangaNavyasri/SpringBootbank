package com.capgemini.icicibank.repository.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.jasper.tagplugins.jstl.core.Catch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.capgemini.icicibank.entities.BankAccount;
import com.capgemini.icicibank.repository.BankAccountRepository;

@Repository
public class BankAccountRepositoryImpl implements BankAccountRepository {

	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public double getBalance(long accountId) throws DataAccessException {
		try {
		
		double balance = jdbcTemplate.queryForObject("SELECT balance FROM bankaccounts Where account_id=?", new Object[] {accountId},Double.class);
		return balance;
		}
		catch(DataAccessException exception)
		{
			exception.initCause(new EmptyResultDataAccessException("balance not displayed", 1));
			throw exception;
		}
	}

	@Override
	public double updateBalance(long accountId, double newBalance) throws DataAccessException{
		try {
		double balance =jdbcTemplate.update("update bankaccounts set balance=? where account_id=?", new Object[] {newBalance,accountId});
		return balance;
	}
	
	catch(DataAccessException exception)
	{
		exception.initCause(new EmptyResultDataAccessException("Balance not updated", 1));
		throw exception;
	}
	}
	
	
	/*class BankAccountRowMapper implements RowMapper<BankAccount>{
		@Override
			public BankAccount mapRow(ResultSet rs, int rowNum) throws SQLException {
				
			BankAccount account=new BankAccount();
			
			account.setAccountId(rs.getInt(1));
			account.setAccountType(rs.getString(2));
			account.setBalance(rs.getDouble(3));
		
					return account;
				}*/

//}
}
