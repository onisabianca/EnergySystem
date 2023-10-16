package ro.tuc.ds2020.dtos.builders;

import ro.tuc.ds2020.dtos.UsersDTO;
import ro.tuc.ds2020.dtos.UsersDetailsDTO;
import ro.tuc.ds2020.entities.Users;

public class UserBuilder {

    private UserBuilder(){
    }

    public static UsersDTO toUsersDTO(Users user)
    {
        return new UsersDTO(user.getId(), user.getName(), user.getRole(),user.getUsername(), user.getPassword());
    }

    public static UsersDetailsDTO toUsersDetailsDTO(Users user)
    {
        return new UsersDetailsDTO(user.getId(), user.getName(), user.getRole(),user.getUsername(), user.getPassword());
    }

    public static Users toEntity(UsersDetailsDTO usersDetailsDTO)
    {
        return new Users(usersDetailsDTO.getName(), usersDetailsDTO.getRole(), usersDetailsDTO.getUsername(),usersDetailsDTO.getPassword());
    }
}
