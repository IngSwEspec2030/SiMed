import { Component, OnInit, ViewChild } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Subscription } from 'rxjs';
import { MapsComponent } from 'src/app/components/maps/maps.component';
import { Eps } from 'src/app/dto/eps';
import { AuthService } from 'src/app/services/auth/auth.service';
import { ConfigService } from 'src/app/services/config.service';
import { UtilHttpService } from 'src/app/services/util-http.service';
import { DisplayMessage } from 'src/app/utils/messageSweet';
import { map } from 'rxjs/operators'
import { element } from 'protractor';

@Component({
  selector: 'app-ubicar-lugares',
  templateUrl: './ubicar-lugares.component.html',
  styleUrls: ['./ubicar-lugares.component.css']
})
export class UbicarLugaresComponent implements OnInit {
  @ViewChild(MapsComponent, {static:false})  myMap:MapsComponent;

  message = new DisplayMessage();
  editForm:FormGroup;
  epsList: Eps[]=[];  
  $subs:Subscription;
  idEpsSeleccionada:number;
  constructor(
    private formBuilder:FormBuilder,
    private http:UtilHttpService, 
    private config:ConfigService,
    private auth:AuthService
  ) { }

  ngOnInit(): void {
    this.getEps();
    this.onBuildForm();
  }

  onBuildForm(){
    this.editForm = this.formBuilder.group({
      idEps: ['', [Validators.required]]  
    })
    this.onLoadInfo()
    this.selectorChanges();
  }

  selectorChanges(){
    this.$subs = this.editForm.valueChanges
    .subscribe(values=>{
      this.idEpsSeleccionada=values.idEps;
      console.log('idEpsSelected: ', values.idEps);
      
      this.myMap.onGetLugaresxEps(values.idEps);
    })
  }

  get f() { return this.editForm.controls; }

  onLoadInfo(){
    this.editForm.setValue({
      idEps: this.auth.getidEps()
    })
  }


  getEps() {
    this.epsList.splice(0,this.epsList.length);
    this.http.showBusy();
    this.http.get(this.config.prop.urllistAllEps, null)
    .subscribe((resp:Eps[]) => {
      resp.map(element=>{
        if(element.estadoEps==true){
          this.epsList.push(element);
        }
      });
      
      this.http.closeBusy();
    }, error => {      
      console.error("Error EPS: ", error);
      this.http.closeBusy();
    })
  }

}
