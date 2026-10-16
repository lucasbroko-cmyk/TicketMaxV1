package app.repository;

import app.domain.User;
import app.service.outputports.UserRepository;

import java.util.ArrayList;
import java.util.List;

public class UserRepositoryImplCollection implements UserRepository {

    List<User> users = new ArrayList<>();

    @Override
    public User save(User user) {

        users.add(user);

        /*
        users.add(String.valueOf(user.getId()));
        users.add(user.getName());
        users.add(user.getLastName());
        users.add(user.getEmail());
        users.add(user.getPhone());
        users.add(user.getPassword());
        users.add(String.valueOf(user.isState()));
        users.add(user.getCity());
        users.add(user.getPreferences());*/

        return user;
    }

    // Busca un usuario por id recorriendo la lista. Retorna null si no existe.
    @Override
    public User selectById(int id) {

        for (User user : users) {
            if (user.getId() != null && user.getId() == id) {
                return user;
            }
        }

        return null;
    }

    @Override
    public List<User> selectAll() {

        for(User user : users){
            System.out.println(user.getId() + "" +
                    " " + user.getName() + "" + user.getLastName() + " " + user.getEmail() + " "
                    + user.getPhone() + "" + user.getPassword() + "" + user.isState() +
                    "" + user.getCity() + "" + user.getPreferences());
        }

        return users;
    }

    // Reemplaza en la lista el usuario que tenga el mismo id. Retorna null si no existe.
    @Override
    public User updateUser(User user) {

        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getId().equals(user.getId())) {
                users.set(i, user);
                return user;
            }
        }

        return null;
    }

    // Elimina el usuario con ese id. Retorna true si se eliminó, false si no existía.
    @Override
    public boolean deleteById(int id) {

        return users.removeIf(user -> user.getId() != null && user.getId() == id);
    }

    // Cantidad de usuarios adicionados en el array.
    @Override
    public int count() {

        return users.size();
    }
}
