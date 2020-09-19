import { Component, Inject, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { Eps } from 'src/app/dto/eps';
import { ConfigService } from 'src/app/services/config.service';
import { UtilHttpService } from 'src/app/services/util-http.service';
import { ButtonType, DisplayMessage, IconType } from 'src/app/utils/messageSweet';

@Component({
  selector: 'app-agregar-eps',
  templateUrl: './agregar-eps.component.html',
  styleUrls: ['./agregar-eps.component.css']
})
export class AgregarEpsComponent implements OnInit {

  message=new DisplayMessage();
  addForm:FormGroup;
  submitted:boolean=false;
  
  constructor(
    public dialogRef: MatDialogRef<AgregarEpsComponent>,
    public http:UtilHttpService,
    public config:ConfigService,
    private formBuilder: FormBuilder,
    ) { }

  ngOnInit(): void {

   this.addForm =  this.formBuilder.group({   
      nombreEps: ['', Validators.required],
      direccionEps: ['', Validators.required],
      telefonoEps:[],
      estadoEps:1
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
    this.http.post(this.config.prop.urlEps, this.addForm.value)
    .subscribe(resp=>{
      this.http.closeBusy();
      this.message.displayInfoMessage("Admin","Eps Agregada satisfactoriamente", IconType.info, ButtonType.Ok);      
    }, err=>{
      this.http.closeBusy();
      this.message.displayInfoMessage("Admin","Error tratando de agregar la eps", IconType.error, ButtonType.Ok);  
    });
    
  }
}
