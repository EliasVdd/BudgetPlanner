package be.pxl.student.dao;

import be.pxl.student.bean.Account;

import java.sql.*;
import java.util.List;

public class AccountDao {

	public static Account insertAccount (Connection connection, Account Account) throws AccountException {

		String query = "INSERT INTO Account (`number`,`IBAN`,`name`) VALUES (?,?,?)";

		try (PreparedStatement stmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
			stmt.setString(1, Account.getNumber());
			stmt.setString(2, Account.getIBAN());
			stmt.setString(3, Account.getName());

			stmt.executeUpdate();
			try (ResultSet resultSet = stmt.getGeneratedKeys()) {
				if (resultSet.next()) {
					Account.setId (resultSet.getInt(1));
				}
			}

			return Account;

		} catch (SQLException e) {
			throw new AccountException(e);
		}

	}


	public static Account getAccountById(Connection connection, int id) throws AccountException {
		String query = "SELECT `id`, `number`,`IBAN`,`name` FROM Account WHERE id = ?";

		try (PreparedStatement stmt = connection.prepareStatement(query)) {
			stmt.setInt(1, id);
			try (ResultSet resultSet = stmt.executeQuery()) {
				if (resultSet.next()) {
					return new Account (
							resultSet.getInt("id"),
							resultSet.getString("number"),
							resultSet.getString("IBAN"),
							resultSet.getString("name"));
				}
			}
			throw new AccountException(String.format("Account with id [%s] not found",id));
		} catch (SQLException e) {
			throw new AccountException(e);
		}
	}

	public static Account getAccountByNumber(Connection connection, String accountNumber) throws AccountException {
		String query = "SELECT `id`, `number`,`IBAN`,`name` FROM Account WHERE number = ?";

		try (PreparedStatement stmt = connection.prepareStatement(query)) {
			stmt.setString(1, accountNumber);
			try (ResultSet resultSet = stmt.executeQuery()) {
				if (resultSet.next()) {
					return new Account (
							resultSet.getInt("id"),
							resultSet.getString("number"),
							resultSet.getString("IBAN"),
							resultSet.getString("name"));
				}
			}
			throw new AccountException(String.format("Account with number [%s] not found",accountNumber));
		} catch (SQLException e) {
			throw new AccountException(e);
		}
	}
}
