package domain;
/**
 * Artis v 0.1
Copyright (c) 2011 Apex Team (EFREI Engineering School)

Please don't use this software or any classes coming from this software without permission.

*/
import java.util.List;

public interface IDAOParticipant {

	public void addParticipant(Participant p);
	public void updateParticipant(Participant p);
	public List<Participant> getAllParticipant();
	public void deleteParticipant(Participant p);
}
