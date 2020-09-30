import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { MatDialogRef } from '@angular/material/dialog';
import { Subject } from 'rxjs';
import { LugaresAtencion } from 'src/app/dto/lugarAtencion';
import { ConfigService } from 'src/app/services/config.service';
import { UtilHttpService } from 'src/app/services/util-http.service';

@Component({
  selector: 'app-ips-list',
  templateUrl: './ips-list.component.html',
  styleUrls: ['./ips-list.component.css']
})
export class IpsListComponent implements OnInit {
  lugaresList: LugaresAtencion[]=[];
  editForm:FormGroup;

  public valueSelected:Subject<LugaresAtencion>;

  constructor(
    public dialogRef: MatDialogRef<IpsListComponent>,
    private formBuilder:FormBuilder,
    private http:UtilHttpService, 
    private config:ConfigService
  ) {

    this.valueSelected = new Subject<LugaresAtencion>();
   }

  ngOnInit(): void {
    this.onBuildForm();
    this.getLugares();
  }

  onBuildForm(){
    this.editForm = this.formBuilder.group({
      idLugar: [''],      
    })
  }


  getLugares() {
    this.lugaresList.splice(0,this.lugaresList.length);
    this.http.showBusy();
    this.http.get(this.config.prop.urllistAllLugarAtencion, null).subscribe((resp:LugaresAtencion[]) => {
      resp.map(element=>{
        if(element.estadoLugarAtencion){//solo los activos
          this.lugaresList.push(element);
        }
      })
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
      var idSelected = this.editForm.get('idLugar').value;
      var lugar = this.lugaresList.find(x=>x.idLugaresAtencion===idSelected);
      this.valueSelected.next(lugar);
      this.onNoClick();
    }
    
    onNoClick(){
      this.ngOnDestroy();
      this.dialogRef.close();
    }

    ngOnDestroy(){
      this.valueSelected.complete();
    }


}
