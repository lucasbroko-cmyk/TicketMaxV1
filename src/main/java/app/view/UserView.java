package app.view;

import app.domain.User;
import app.domain.enums.SelectPreferencesEnum;
import app.service.helpers.SetUserState;
import app.service.inputports.UserService;
import app.service.validators.DataTypeValidator;

import java.util.ArrayList;
import java.util.List;

public class UserView {


    private final UserService userService;

    public UserView(UserService userService){
        this.userService = userService;
    }

    public void createUser() {


        int id = DataTypeValidator.validateInt("INgrese el id del usuario: ");
        String name = DataTypeValidator.validateString("Ingrese el nombre del usuario: ");
        String lastName = DataTypeValidator.validateString("Ingrese el apellido del usuario: ");
        String email = DataTypeValidator.validateString("Ingrese el correo del usuario: ");
        String phone = DataTypeValidator.validateString("Ingrese el telefono del usuario: ");
        System.out.println("Ingrese la contraseña del usuario: ");
        String password = DataTypeValidator.validateString("Ingrese la contraseña del usuario: ");
        System.out.println("Ingrese el estado del usuario: ");
        String state = SetUserState.getUserState();
        System.out.println();
        String city = DataTypeValidator.validateString("Ingrese la ciudad del usuario: ");
        System.out.println("Ingrese las preferencias del usuario: ");
        String preferences = setUserPreferences();

        userService.create(id, name , lastName , email , phone , password , state, city, preferences);

    }


    // Consulta un usuario por su id y lo muestra en consola
    public void selectUserById(int id) {

        User user = userService.selectUserById(id);

        if (user == null) {
            System.out.println("No existe un usuario con el id " + id);
            return;
        }

        printUser(user);
    }


    public void selectUsers(){

        userService.selectUsers();

    }


    // Actualiza los datos de un usuario existente (el id no se modifica)
    public void updateUser() {

        int id = DataTypeValidator.validateInt("Ingrese el id del usuario a actualizar: ");
        User current = userService.selectUserById(id);

        if (current == null) {
            System.out.println("No existe un usuario con el id " + id);
            return;
        }

        System.out.println("Datos actuales del usuario:");
        printUser(current);
        System.out.println("Ingrese los nuevos datos:");

        String name = DataTypeValidator.validateOptionalString("Nombre: ", current.getName());
        String lastName = DataTypeValidator.validateOptionalString("Apellido: ", current.getLastName());
        String email = DataTypeValidator.validateOptionalString("Correo: ", current.getEmail());
        String phone = DataTypeValidator.validateOptionalString("Teléfono: ", current.getPhone());
        String password = DataTypeValidator.validateOptionalString("Contraseña: ", current.getPassword());

        System.out.println("Estado (cualquier otro número conserva el actual: " + current.isState() + ")");
        String state = SetUserState.getUserState();
        if (state.isEmpty()) {
            state = current.isState();
        }

        String city = DataTypeValidator.validateOptionalString("Ciudad: ", current.getCity());

        System.out.println("Preferencia (cualquier otro número conserva la actual: " + current.getPreferences() + ")");
        String preferences = setUserPreferences();
        if (preferences.isEmpty()) {
            preferences = current.getPreferences();
        }

        User updated = new User(id, name, lastName, email, phone, password, state, city, preferences);
        User result = userService.updateUser(updated);

        if (result == null) {
            System.out.println("No se pudo actualizar el usuario");
        } else {
            System.out.println("Usuario actualizado correctamente:");
            printUser(result);
        }
    }


    // Elimina un usuario por id, pidiendo confirmación antes de borrar
    public void deleteUser() {

        int id = DataTypeValidator.validateInt("Ingrese el id del usuario a eliminar: ");
        User user = userService.selectUserById(id);

        if (user == null) {
            System.out.println("No existe un usuario con el id " + id);
            return;
        }

        printUser(user);
        int confirm = DataTypeValidator.validateInt("¿Está seguro de eliminar este usuario? 1. Sí 2. No");

        if (confirm == 1) {
            boolean deleted = userService.deleteUser(id);
            System.out.println(deleted ? "Usuario eliminado correctamente" : "No se pudo eliminar el usuario");
        } else {
            System.out.println("Operación cancelada");
        }
    }


    // Método adicional: muestra cuántos usuarios hay adicionados en el array
    public void countUsers() {

        System.out.println("Total de usuarios registrados: " + userService.countUsers());
    }


    // métodos Helper


    private void printUser(User user) {

        System.out.println("--------------------------------");
        System.out.println("ID: " + user.getId());
        System.out.println("Nombre: " + user.getName() + " " + user.getLastName());
        System.out.println("Correo: " + user.getEmail());
        System.out.println("Teléfono: " + user.getPhone());
        System.out.println("Estado: " + user.isState());
        System.out.println("Ciudad: " + user.getCity());
        System.out.println("Preferencias: " + user.getPreferences());
        System.out.println("--------------------------------");
    }


    public String setUserPreferences(){


        int option = DataTypeValidator.validateInt("Seleccione 1. VIP 2. General 3. Preferencial");
        String preferences = "";

        switch (option){

            case 1:
                preferences = SelectPreferencesEnum.VIP.getPreference();
                break;
            case 2:
                preferences = SelectPreferencesEnum.GENERAL.getPreference();
                break;
            case 3:
                preferences = SelectPreferencesEnum.PREFERENCIAL.getPreference();
                break;
            default:
                System.out.println("Opción no valida");

        }
        return preferences;
    }








}
