package domain;

import java.util.List;

public interface IDAONews {

	public void addNews(News n);
	public void deleteNews(News n);
	public List<News> getAllNews();
	public List<News> getLastNews();
}
