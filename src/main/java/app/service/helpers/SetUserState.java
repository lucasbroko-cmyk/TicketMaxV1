package app.service.helpers;

import app.domain.enums.SelectStateEnum;
import app.service.validators.DataTypeValidator;

public class SetUserState {


    public static String getUserState(){

        int option = DataTypeValidator.validateInt("Seleccione 1. Activo 2. Inactivo 3. Bloqueado");
        String state = "";
        switch (option){
            case 1:
                state = SelectStateEnum.ACTIVE.getState();
                break;
            case 2:
                state =  SelectStateEnum.INACTIVE.getState();
                break;
            case 3:
                state = SelectStateEnum.BLOCKED.getState();
                break;
            default:
                System.out.println("Opción no valida");
        }

        return state;
    }


}
