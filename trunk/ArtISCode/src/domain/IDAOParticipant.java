package domain;

import java.util.List;

public interface IDAOParticipant {

	public void addParticipant(Participant p);
	public void updateParticipant(Participant p);
	public List<Participant> getAllParticipant();
	public void deleteParticipant(Participant p);
}
