

import Swal, {SweetAlertIcon} from 'sweetalert2'

export class DisplayMessage{
        swalWithBootstrapButtons = Swal.mixin({customClass:{
        confirmButton: 'btn btn-primary',
        cancelButton: 'btn btn-default'
        },
        buttonsStyling:false
    })


    public displayInfoMEsage(
        title:string, 
        message:string,
        txtOk:string="Ok",
        txtCancel:string="Cancel",
        sweetAlertIconType:SweetAlertIcon,
        buttonType:ButtonType
        ):any{
        let promise:any;

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
    success="",
    error="error",
    warning="warning",
    info="info",
    question="question"
}

export enum ButtonType{
    Ok=1,
    OkCancel=2,
}