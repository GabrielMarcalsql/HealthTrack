package br.com.HealthTrack.Interface;

import java.util.List;

public interface DAOInterface {
	public boolean insert(EntityInterface entity);
	public List<EntityInterface> getAll();
	public boolean update(int id, EntityInterface entity);
	public boolean delete(int id);
	public EntityInterface findById(int id);
}
