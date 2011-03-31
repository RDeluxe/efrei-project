package domain;

public interface IDAONotification {

	public void addNotification(Notification n);
	public void removeNotification(Notification n);
	public Notification getById(long id);
}
