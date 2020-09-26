import { Component, Inject, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { Especialidad } from 'src/app/dto/Especialidad';
import { ConfigService } from 'src/app/services/config.service';
import { UtilHttpService } from 'src/app/services/util-http.service';
import { ButtonType, DisplayMessage, IconType } from 'src/app/utils/messageSweet';

@Component({
  selector: 'app-editar-especialidad',
  templateUrl: './editar-especialidad.component.html',
  styleUrls: ['./editar-especialidad.component.css']
})
export class EditarEspecialidadComponent implements OnInit {

  id:string;
  estadoEspecialidad:string;
  editForm:FormGroup;
  submitted:boolean=false;
  message=new DisplayMessage();

  constructor(
    public dialogRef: MatDialogRef<EditarEspecialidadComponent>,
    @Inject(MAT_DIALOG_DATA) public data: Especialidad,
    public http:UtilHttpService,
    public config:ConfigService,
    private formBuilder: FormBuilder,
    ) { 
      this.id=data.idEspecialidad.toString();
      this.estadoEspecialidad=data.estadoEspecialidad?'Activo':'Inactivo';
    }

  ngOnInit(): void {
    this.editForm =  this.formBuilder.group({  
      idEspecialidad:[],
      nombreEspecialidad: ['', Validators.required],
      estadoEspecialidad:[]
     });

     this.onLoadInfo();
  }

  onLoadInfo(){
    this.editForm.setValue({
      idEspecialidad:this.data.idEspecialidad,
      nombreEspecialidad:this.data.nombreEspecialidad,
      estadoEspecialidad:this.data.estadoEspecialidad
    })
  }


    // convenience getter for easy access to form fields
    get f() { return this.editForm.controls; } 

    onNoClick(): void {
      this.dialogRef.close();
    }

    public onConfirm(): void {
      this.submitted = true;  

      if (this.editForm.invalid) { 
        return;
      }
     
      this.http.showBusy();
      this.http.put(this.config.prop.urlupdateEspecialidad, this.editForm.value, this.id)
      .subscribe(resp=>{
        this.http.closeBusy();
        this.message.displayInfoMessage("Admin","Especialidad actualizada correctamente", IconType.info, ButtonType.Ok);      
      }, err=>{
        this.http.closeBusy();
        this.message.displayInfoMessage("Admin","Error tratando de actualizar la especialidad", IconType.error, ButtonType.Ok);  
      });
    }

}
