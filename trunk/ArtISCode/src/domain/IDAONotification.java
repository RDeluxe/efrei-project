package domain;
/**
 * Artis v 0.1
Copyright (c) 2011 Apex Team (EFREI Engineering School)

Please don't use this software or any classes coming from this software without permission.

*/
public interface IDAONotification {

	public void addNotification(Notification n);
	public void removeNotification(Notification n);
	public Notification getById(long id);
}
