import { Component, Inject, OnInit } from '@angular/core';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { LugaresAtencion } from 'src/app/dto/lugarAtencion';
import { ConfigService } from 'src/app/services/config.service';
import { UtilHttpService } from 'src/app/services/util-http.service';
import { ButtonType, DisplayMessage, IconType } from 'src/app/utils/messageSweet';

@Component({
  selector: 'app-borrar-lugarAtencion',
  templateUrl: './borrar-lugarAtencion.component.html',
  styleUrls: ['./borrar-lugarAtencion.component.css']
})
export class BorrarLugarAtencionComponent implements OnInit {

  message=new DisplayMessage();
  constructor(
    public dialogRef: MatDialogRef<BorrarLugarAtencionComponent>,
    @Inject(MAT_DIALOG_DATA) public lugarAtencion: LugaresAtencion,
    public http:UtilHttpService,
    private config: ConfigService

    ) { }

  ngOnInit(): void {
  }

  onNoClick(): void {
    this.dialogRef.close();
  }

  confirmDelete(): void {
    this.http.showBusy();
    this.http.delete(this.config.prop.urldeleteLugarAtencion, this.lugarAtencion.idLugaresAtencion)
    .subscribe(resp=>{
      this.http.closeBusy();
      this.message.displayInfoMessage("Admin","EPS borrada satisfactoriamente", IconType.info, ButtonType.Ok);      
    }, err=>{
      this.http.closeBusy();
      console.log(err.error);
   
      this.message.displayInfoMessage("Admin","No se pudo borrar el recurso", IconType.error, ButtonType.Ok);  
    });
  }

}
