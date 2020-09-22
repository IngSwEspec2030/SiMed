import { Component, Inject, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { Eps } from 'src/app/dto/eps';
import { ConfigService } from 'src/app/services/config.service';
import { UtilHttpService } from 'src/app/services/util-http.service';
import { ButtonType, DisplayMessage, IconType } from 'src/app/utils/messageSweet';

@Component({
  selector: 'app-editar-eps',
  templateUrl: './editar-eps.component.html',
  styleUrls: ['./editar-eps.component.css']
})
export class EditarEpsComponent implements OnInit {

  id:string;
  estadoEps:string;
  editForm:FormGroup;
  submitted:boolean=false;
  message=new DisplayMessage();

  constructor(
    public dialogRef: MatDialogRef<EditarEpsComponent>,
    @Inject(MAT_DIALOG_DATA) public data: Eps,
    public http:UtilHttpService,
    public config:ConfigService,
    private formBuilder: FormBuilder,
    ) { 
      this.id=data.idEps.toString();
      this.estadoEps=data.estadoEps?'Activo':'Inactivo';
    }

  ngOnInit(): void {
    this.editForm =  this.formBuilder.group({  
      idEps:[],
      nombreEps: ['', Validators.required],
      direccionEps: ['', Validators.required],
      telefonoEps:[],
      estadoEps:[]
     });

     this.onLoadInfo();
  }

  onLoadInfo(){
    this.editForm.setValue({
      idEps:this.data.idEps,
      nombreEps:this.data.nombreEps,
      direccionEps:this.data.direccionEps,
      telefonoEps:this.data.telefonoEps,
      estadoEps:this.data.estadoEps
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
      this.http.put(this.config.prop.urlupdateEps, this.editForm.value, this.id)
      .subscribe(resp=>{
        this.http.closeBusy();
        this.message.displayInfoMessage("Admin","Eps actualizada correctamente", IconType.info, ButtonType.Ok);      
      }, err=>{
        this.http.closeBusy();
        this.message.displayInfoMessage("Admin","Error tratando de actualizar la eps", IconType.error, ButtonType.Ok);  
      });
    }

}
