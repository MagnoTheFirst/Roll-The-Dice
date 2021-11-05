package ch.webe.rollthedice.webeproject.repos;

import ch.webe.rollthedice.webeproject.model.User;

import java.util.UUID;

public interface SessionDao {

    public int insertUser(UUID id, User user1);

    default int addUserIntoSession(User user){
        UUID id = UUID.randomUUID();
        return insertUser(id, user);
    }

}
