import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { LugaresAtencion } from 'src/app/dto/lugarAtencion';
import { ConfigService } from 'src/app/services/config.service';
import { UtilHttpService } from 'src/app/services/util-http.service';
import { DisplayMessage } from 'src/app/utils/messageSweet';

@Component({
  selector: 'app-rel-site-specialty',
  templateUrl: './rel-site-specialty.component.html',
  styleUrls: ['./rel-site-specialty.component.css']
})
export class RelSiteSpecialtyComponent implements OnInit {

  editForm:FormGroup;
  lugaresList:LugaresAtencion[]=[];
  submitted = false;
  message:  DisplayMessage = new DisplayMessage();
  constructor(
    private formBuilder:FormBuilder,
    private http:UtilHttpService, 
    private config:ConfigService
    ) { }

  ngOnInit(): void {
    this.onBuildForm();
    this.getLugares();
  }

  onBuildForm(){
    this.editForm = this.formBuilder.group({
      // idEps: ['', [Validators.required]],
      idLugar: ['', [Validators.required]],
      
    })
  }

  
  getLugares() {
    this.http.showBusy();
    this.http.get(this.config.prop.urllistAllLugarAtencion, null).subscribe((resp:LugaresAtencion[]) => {
      this.lugaresList = resp;
      this.http.closeBusy();
    }, error => {
      
      console.error("Error Lugares de atención: "+error);
      this.http.closeBusy();
    })
  }

  // convenience getter for easy access to form fields
  get f() { return this.editForm.controls; }

  onConfirm(){
    console.log('trato de guardar');
    
  }

}
