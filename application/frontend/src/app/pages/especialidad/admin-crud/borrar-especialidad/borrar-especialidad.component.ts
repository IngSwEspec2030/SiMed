import { Component, Inject, OnInit } from '@angular/core';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { Especialidad } from 'src/app/dto/Especialidad';
import { ConfigService } from 'src/app/services/config.service';
import { UtilHttpService } from 'src/app/services/util-http.service';
import { ButtonType, DisplayMessage, IconType } from 'src/app/utils/messageSweet';

@Component({
  selector: 'app-borrar-especialidad',
  templateUrl: './borrar-especialidad.component.html',
  styleUrls: ['./borrar-especialidad.component.css']
})
export class BorrarEspecialidadComponent implements OnInit {

  message=new DisplayMessage();
  constructor(
    public dialogRef: MatDialogRef<BorrarEspecialidadComponent>,
    @Inject(MAT_DIALOG_DATA) public especialidad: Especialidad,
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
    this.http.delete(this.config.prop.urldeleteEspecialidad, this.especialidad.idEspecialidad)
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
