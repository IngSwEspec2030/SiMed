

import Swal, {SweetAlertIcon} from 'sweetalert2'

export class DisplayMessage{
        swalWithBootstrapButtons = Swal.mixin({customClass:{
        confirmButton: 'btn btn-primary mr-2',
        cancelButton: 'btn btn-danger ml-2'
        },
        buttonsStyling:false
    })


    public displayInfoMessage(
        title:string, 
        message:string,
        sweetAlertIconType:SweetAlertIcon,
        buttonType:ButtonType
        ):any{
        let promise:any;
        let txtOk:string="Aceptar";
        let txtCancel:string="Cancelar";

        switch(buttonType){
            case ButtonType.Ok:
                promise = this.swalWithBootstrapButtons
                .fire({
                    title:title,
                    html:message,
                    icon:sweetAlertIconType,
                    showCancelButton:false,
                    confirmButtonText:txtOk
                });
                break;
            case ButtonType.OkCancel:
                promise = this.swalWithBootstrapButtons
                .fire({
                    title:title,
                    html:message,
                    icon:sweetAlertIconType,
                    showCancelButton:true,
                    confirmButtonText:txtOk,
                    cancelButtonText:txtCancel,                   
                });
                break;
        }   

        return promise;



        
    }


}


export enum IconType{
    success="success",
    error="error",
    warning="warning",
    info="info",
    question="question"
}

export enum ButtonType{
    Ok=1,
    OkCancel=2,
}