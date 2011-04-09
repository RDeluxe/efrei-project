package domain;
/**
 * Artis v 0.1
Copyright (c) 2011 Apex Team (EFREI Engineering School)

Please don't use this software or any classes coming from this software without permission.

*/
import java.util.List;

public interface IDAONews {

	public void addNews(News n);
	public void deleteNews(News n);
	public List<News> getAllNews();
	public List<News> getLastNews();
}
