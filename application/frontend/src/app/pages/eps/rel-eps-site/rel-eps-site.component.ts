import { ScrollStrategyOptions } from '@angular/cdk/overlay';
import { Component, OnInit, ViewChild } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatDialog, MatDialogConfig } from '@angular/material/dialog';
import { MatPaginator } from '@angular/material/paginator';
import { MatTableDataSource } from '@angular/material/table';
import { Subscription } from 'rxjs';
import { IpsListComponent } from 'src/app/components/modals/ips-list/ips-list.component';
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
    public dialog: MatDialog,
    private formBuilder:FormBuilder,
    private http:UtilHttpService, 
    private config:ConfigService
    ) { 
      this.datasource = new MatTableDataSource<LugaresAtencion>();
    }

  ngOnInit(): void {
    this.onBuildForm();
    this.getEps();
    this.getLugares();
  }

  onBuildForm(){
    this.editForm = this.formBuilder.group({
      idEps: ['', [Validators.required]],  
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
      this.lugaresAtencionEPS = resp.payLoad;
      this.datasource = new MatTableDataSource<any>(this.lugaresAtencionEPS);
      this.datasource.paginator = this.paginator;

      }

      this.http.closeBusy();
    }, error => { 
      if(error.error.status==500){
      this.datasource = new MatTableDataSource<any>(this.lugaresAtencionEPS);
      }  
      console.error("Error cargando lugares de atención de eps: ", error);
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
    if(this.editForm.invalid){
      return;
    }

    let myArr:number[]=[];
    this.datasource.data.forEach((element:LugaresAtencion) => {
      myArr.push(element.idLugaresAtencion);    
    });
    let epsSelected = this.editForm.get('idEps').value;
    this.onSave(epsSelected,{"lugarAtencionCollection":myArr});
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
    let ssd = this.datasource.data;
    ssd.splice(i,1);
    this.datasource.data = ssd;
    this.datasource.connect();    
  }

  onAddNew() {
    const dialogConfig = new MatDialogConfig();
    dialogConfig.width = '40vw';
    dialogConfig.height= '30vw';
    let dialogRef= this.dialog.open(IpsListComponent, dialogConfig);
    dialogRef.componentInstance.valueSelected.subscribe((res:LugaresAtencion)=>{
      if(res!=null && res!=undefined){
        let ssd:LugaresAtencion[] = this.datasource.data; 
        let rrt = ssd.find(x=>x.idLugaresAtencion===res.idLugaresAtencion);
        console.log(rrt);        
        if(!rrt){
          ssd.push(res);
          this.datasource.data = ssd;
        }
        this.datasource.connect();       
      }
    } )
  }

}
