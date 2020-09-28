import { Component, OnInit } from '@angular/core';
import { MatDialogRef } from '@angular/material/dialog';
import { AuthService } from 'src/app/services/auth/auth.service';
import { ConfigService } from 'src/app/services/config.service';
import { UtilHttpService } from 'src/app/services/util-http.service';
import { ButtonType, DisplayMessage, IconType } from 'src/app/utils/messageSweet';

@Component({
  selector: 'app-borrar',
  templateUrl: './borrar-usuario.component.html',
  styleUrls: ['./borrar-usuario.component.css']
})
export class BorrarUsuarioComponent implements OnInit {

  message=new DisplayMessage();
  constructor(public dialogRef: MatDialogRef<BorrarUsuarioComponent>,
    public http:UtilHttpService,
    private config: ConfigService,
    private auth:AuthService
    ) { }

  ngOnInit(): void {
  }

  confirmDelete(){
    this.http.showBusy();
    this.http.delete(this.config.prop.urlUsuario,this.auth.getUserId())
    .subscribe(result=>{
      let respuesta = this.message.displayInfoMessage("Usuario","Su cuenta ha sido eliminada satisfactoriamente", IconType.info, ButtonType.Ok);      
      respuesta.then(p=>{
        this.http.closeBusy();
        this.auth.logout();
      });
    }, err=>{
      this.http.closeBusy();
      console.log(err.error);   
      this.message.displayInfoMessage("Usuario","Ha ocurrido un error en el proceso de eliminación de la cuenta", IconType.error, ButtonType.Ok);  
    });
  }

  onNoClick(): void {
    this.dialogRef.close();
  }

}
