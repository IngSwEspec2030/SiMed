import { Component, Inject, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { LugaresAtencion } from 'src/app/dto/lugarAtencion';
import { ConfigService } from 'src/app/services/config.service';
import { UtilHttpService } from 'src/app/services/util-http.service';
import { ButtonType, DisplayMessage, IconType } from 'src/app/utils/messageSweet';

@Component({
  selector: 'app-editar-lugarAtencion',
  templateUrl: './editar-lugarAtencion.component.html',
  styleUrls: ['./editar-lugarAtencion.component.css']
})
export class EditarLugarAtencionComponent implements OnInit {

  id:string;
  estadoLugarAtencion:string;
  editForm:FormGroup;
  submitted:boolean=false;
  message=new DisplayMessage();

  constructor(
    public dialogRef: MatDialogRef<EditarLugarAtencionComponent>,
    @Inject(MAT_DIALOG_DATA) public data: LugaresAtencion,
    public http:UtilHttpService,
    public config:ConfigService,
    private formBuilder: FormBuilder,
    ) { 
      this.id=data.idLugaresAtencion.toString();
      this.estadoLugarAtencion=data.estadoLugarAtencion?'Activo':'Inactivo';
    }

  ngOnInit(): void { 
    this.editForm =  this.formBuilder.group({  
      idLugaresAtencion:[],
      nombreLugarAtencion: ['', Validators.required],
      direccionLugarAtencion: ['', Validators.required],
      telefonoLugarAtencion:[],
      sitioWebLugarAtencion:['', Validators.required],
      latitudLugarAtencion: ['', Validators.required],
      longitudLugarAtencion: ['', Validators.required],
      estadoLugarAtencion:[]
     });

     this.onLoadInfo();
  }

  onLoadInfo(){
    this.editForm.setValue({
      idLugaresAtencion:this.data.idLugaresAtencion,
      nombreLugarAtencion:this.data.nombreLugarAtencion,
      direccionLugarAtencion:this.data.direccionLugarAtencion,
      telefonoLugarAtencion:this.data.telefonoLugarAtencion,
      sitioWebLugarAtencion:this.data.sitioWebLugarAtencion,
      latitudLugarAtencion:this.data.latitudLugarAtencion,
      longitudLugarAtencion:this.data.longitudLugarAtencion,
      estadoLugarAtencion:this.data.estadoLugarAtencion
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
      this.http.put(this.config.prop.urlupdateLugarAtencion, this.editForm.value, this.id)
      .subscribe(resp=>{
        this.http.closeBusy();
        this.message.displayInfoMessage("Admin","Lugar Atención actualizada correctamente", IconType.info, ButtonType.Ok);      
      }, err=>{
        this.http.closeBusy();
        this.message.displayInfoMessage("Admin","Error tratando de actualizar la lugarAtencion", IconType.error, ButtonType.Ok);  
      });
    }

}
