package com.adailsilva.jdbc.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.adailsilva.jdbc.entities.Model;

public class DAO {

	private Connection connection = null;

	public void EventoDAO(Connection connection) {
		this.connection = connection;
	}

	public Integer create(Model model) {
		String sql = "INSERT INTO model (id, name, date) VALUES (null, ?, ?)";

		try (PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
			preparedStatement.setString(1, model.getName());
			preparedStatement.setDate(2, new Date(model.getDate().getTime()));

			preparedStatement.executeUpdate();

			try (ResultSet resultSet = preparedStatement.getGeneratedKeys()) {
				resultSet.next();

				return resultSet.getInt(1);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public List<Model> read() {
		String sql = "SELECT * FROM model";

		try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
			try (ResultSet resultSet = preparedStatement.executeQuery()) {
				List<Model> models = new ArrayList<Model>();

				while (resultSet.next()) {
					models.add(
							new Model(resultSet.getLong("id"), resultSet.getString("name"), resultSet.getDate("date")));
				}

				return models;
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public Model readByID(Long id) {
		String sql = "SELECT * FROM model WHERE id = ?";

		try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
			preparedStatement.setLong(1, id);

			try (ResultSet resultSet = preparedStatement.executeQuery()) {
				if (!resultSet.next()) {
					return null;
				}

				return new Model(id, resultSet.getString("name"), resultSet.getDate("date"));
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public void update(Model model) {
		String sql = "UPDATE model SET name = ?, date = ? WHERE id = ?";

		try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
			preparedStatement.setString(1, model.getName());
			preparedStatement.setDate(2, new Date(model.getDate().getTime()));
			preparedStatement.setLong(3, model.getId());

			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public void delete(Long id) {
		String sql = "DELETE FROM model WHERE id = ?";

		try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
			preparedStatement.setLong(1, id);

			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

}
