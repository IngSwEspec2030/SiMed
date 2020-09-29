import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Eps } from 'src/app/dto/eps';
import { LugaresAtencion } from 'src/app/dto/lugarAtencion';
import { ConfigService } from 'src/app/services/config.service';
import { UtilHttpService } from 'src/app/services/util-http.service';
import { DisplayMessage } from 'src/app/utils/messageSweet';

@Component({
  selector: 'app-rel-eps-site',
  templateUrl: './rel-eps-site.component.html',
  styleUrls: ['./rel-eps-site.component.css']
})
export class RelEpsSiteComponent implements OnInit {

  editForm:FormGroup;
  epsList: Eps[]=[];
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
    this.getEps();
    this.getLugares();
  }

  onBuildForm(){
    this.editForm = this.formBuilder.group({
      idEps: ['', [Validators.required]],
      idLugar: ['', [Validators.required]],
      
    })
  }

  getEps() {
    this.http.showBusy();
    this.http.get(this.config.prop.urllistAllEps, null).subscribe((resp:Eps[]) => {
      this.epsList = resp;
      this.http.closeBusy();
    }, error => {
      
      console.error("Error EPS: "+error);
      this.http.closeBusy();
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
