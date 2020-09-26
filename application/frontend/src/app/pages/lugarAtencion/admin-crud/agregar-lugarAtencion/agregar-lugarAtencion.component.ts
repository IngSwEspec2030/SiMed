import { Component, Inject, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { LugaresAtencion } from 'src/app/dto/lugarAtencion';
import { ConfigService } from 'src/app/services/config.service';
import { UtilHttpService } from 'src/app/services/util-http.service';
import { ButtonType, DisplayMessage, IconType } from 'src/app/utils/messageSweet';

@Component({
  selector: 'app-agregar-lugarAtencion',
  templateUrl: './agregar-lugarAtencion.component.html',
  styleUrls: ['./agregar-lugarAtencion.component.css']
})
export class AgregarLugarAtencionComponent implements OnInit {

  message=new DisplayMessage();
  addForm:FormGroup;
  submitted:boolean=false;
  
  constructor(
    public dialogRef: MatDialogRef<AgregarLugarAtencionComponent>,
    public http:UtilHttpService,
    public config:ConfigService,
    private formBuilder: FormBuilder,
    ) { }

  ngOnInit(): void {

   this.addForm =  this.formBuilder.group({   
      nombreLugarAtencion: ['', Validators.required],
      direccionLugarAtencion: ['', Validators.required],
      telefonoLugarAtencion:[],
      sitioWebLugarAtencion:['', Validators.required],
      latitudLugarAtencion: ['', Validators.required],
      longitudLugarAtencion: ['', Validators.required],
      estadoLugarAtencion:1
     });
  }

  // convenience getter for easy access to form fields
  get f() { return this.addForm.controls; } 

  onNoClick(): void {
    this.dialogRef.close();
  }

  public confirmAdd(): void {
    this.submitted = true;

    // stop here if form is invalid
    if (this.addForm.invalid) {
      return;
    }

    this.http.showBusy();
    this.http.post(this.config.prop.urlcreateLugarAtencion, this.addForm.value)
    .subscribe(resp=>{
      this.http.closeBusy();
      this.message.displayInfoMessage("Admin","Lugar Atencion Agregada satisfactoriamente", IconType.info, ButtonType.Ok);      
    }, err=>{
      this.http.closeBusy();
      this.message.displayInfoMessage("Admin","Error tratando de agregar la lugarAtencion", IconType.error, ButtonType.Ok);  
    });
    
  }
}
