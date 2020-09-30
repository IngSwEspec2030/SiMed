import { Component, OnInit, ViewChild } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatPaginator } from '@angular/material/paginator';
import { MatTableDataSource } from '@angular/material/table';
import { Subscription } from 'rxjs';
import { Eps } from 'src/app/dto/eps';
import { LugaresAtencion } from 'src/app/dto/lugarAtencion';
import { ConfigService } from 'src/app/services/config.service';
import { UtilHttpService } from 'src/app/services/util-http.service';
import { ButtonType, DisplayMessage, IconType } from 'src/app/utils/messageSweet';

@Component({
  selector: 'app-rel-eps-site',
  templateUrl: './rel-eps-site.component.html',
  styleUrls: ['./rel-eps-site.component.css']
})
export class RelEpsSiteComponent implements OnInit {
  @ViewChild(MatPaginator, { static: true }) paginator: MatPaginator;
  
  displayedColumns: string[] = ['nombreLugarAtencion', 'direccionLugarAtencion', 'telefonoLugarAtencion', 'actions'];
  lugaresAtencionEPS: LugaresAtencion[] = [];//listado de lugares correspondientes a una eps
  datasource = new MatTableDataSource<any>();

  $subs:Subscription;

  editForm:FormGroup;
  epsList: Eps[]=[];
  lugaresList:LugaresAtencion[]=[];
  submitted = false;
  message:  DisplayMessage = new DisplayMessage();
  constructor(
    private formBuilder:FormBuilder,
    private http:UtilHttpService, 
    private config:ConfigService
    ) { 
      this.datasource = new MatTableDataSource<any>();
    }

  ngOnInit(): void {
    this.onBuildForm();
    this.getEps();
    this.getLugares();
  }

  onBuildForm(){
    this.editForm = this.formBuilder.group({
      idEps: ['', [Validators.required]],
      // idLugar: ['', [Validators.required]]      
    })

    this.selectorChanges();
  }

  selectorChanges(){
    this.$subs = this.editForm.valueChanges
    .subscribe(values=>{
      console.log('value changes: ', values);  
      this.getLugaresEps(values.idEps);
    })
  }

  getEps() {
    this.http.showBusy();
    this.http.get(this.config.prop.urllistAllEps, null).subscribe((resp:Eps[]) => {
      this.epsList = resp;
      this.http.closeBusy();
    }, error => {      
      console.error("Error EPS: ", error);
      this.http.closeBusy();
    })
  }

  getLugaresEps(idEps:number){
    this.lugaresAtencionEPS.splice(0,this.lugaresAtencionEPS.length);
    this.http.showBusy();
    this.http.get(this.config.prop.urllistlugaresAtencionEps, idEps.toString()).subscribe((resp:any) => {
      if(resp!=null && resp.status===200){
        console.log('mis lugares:', resp.payLoad);
              
      this.lugaresAtencionEPS = resp.payLoad;
      this.datasource = new MatTableDataSource<any>(this.lugaresAtencionEPS);
      this.datasource.paginator = this.paginator;

      }

      this.http.closeBusy();
    }, error => {      
      console.error("Error cargando lugare de atención de eps: ", error);
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
    console.log('trato de guardar', this.datasource.data); 

    let myArr:[]=[];

    this.datasource.data.forEach(element => {
      console.log(element);
      

      
    });   



    // const lugares={
    //   "lugarAtencionCollection":[]
    // }

  }

  /**
   * Actualiza los lugares de atención asignados a una eps
   * @param idEps 
   * @param body 
   */
  onSave(idEps:number, body:any){
    this.http.showBusy();
    this.http.put(this.config.prop.urlupdateEps, body, idEps).subscribe((resp:any) => {
      this.message.displayInfoMessage('Admin', 'Se ha agreagdo correctamente', IconType.info, ButtonType.Ok);
      this.http.closeBusy();
    }, error => {      
      console.error("Error Lugares de atención: "+error);
      this.message.displayInfoMessage('Admin', 'Error al intentar actualizar la información', IconType.error, ButtonType.Ok);
      this.http.closeBusy();
    })
  }


  onDeleteItem(i,element){
    console.log('quiero borrar una de estas', i, element);
    
  }

  onAddNew(){
    console.log('quiero agregar');
    
  }

}
