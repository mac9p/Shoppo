import {FormControl, ValidationErrors} from "@angular/forms";

export class ShoppoValidators {


    static notWhiteSpace(control : FormControl) : ValidationErrors | null{
      if (control.value.matches(".*\\s.*")){
        return { 'notWhiteSpace': true}
      }else
        return null;
    }
}
