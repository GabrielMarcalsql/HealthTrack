package br.com.HealthTrack.DAO;

public class DAOFactory<T> {

	private final Class<T> type;

	public DAOFactory(Class<T> type) {

		this.type = type;
	}

	public T getInstance() {

		try {
			// assume type is a public class
			return type.getDeclaredConstructor().newInstance();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	public static <Y> DAOFactory<Y> getInstance(Class<Y> type) {

		return new DAOFactory<Y>(type);
	}
}
