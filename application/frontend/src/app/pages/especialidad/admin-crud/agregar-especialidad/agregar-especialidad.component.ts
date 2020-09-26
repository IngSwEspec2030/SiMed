import { Component, Inject, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { Especialidad } from 'src/app/dto/Especialidad';
import { ConfigService } from 'src/app/services/config.service';
import { UtilHttpService } from 'src/app/services/util-http.service';
import { ButtonType, DisplayMessage, IconType } from 'src/app/utils/messageSweet';

@Component({ 
  selector: 'app-agregar-especialidad',
  templateUrl: './agregar-especialidad.component.html',
  styleUrls: ['./agregar-especialidad.component.css']
})
export class AgregarEspecialidadComponent implements OnInit {

  message=new DisplayMessage();
  addForm:FormGroup;
  submitted:boolean=false;
  
  constructor(
    public dialogRef: MatDialogRef<AgregarEspecialidadComponent>,
    public http:UtilHttpService,
    public config:ConfigService,
    private formBuilder: FormBuilder,
    ) { }

  ngOnInit(): void {

   this.addForm =  this.formBuilder.group({   
      nombreEspecialidad: ['', Validators.required],
      estadoEspecialidad:1
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
    this.http.post(this.config.prop.urlcreateEspecialidad, this.addForm.value)
    .subscribe(resp=>{
      this.http.closeBusy();
      this.message.displayInfoMessage("Admin","Especialidad Agregada satisfactoriamente", IconType.info, ButtonType.Ok);      
    }, err=>{
      this.http.closeBusy();
      this.message.displayInfoMessage("Admin","Error tratando de agregar la especialidad", IconType.error, ButtonType.Ok);  
    });
    
  }
}
