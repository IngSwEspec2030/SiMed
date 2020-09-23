import { Component, OnInit, ViewChild } from '@angular/core';
import { MatDialog, MatDialogConfig } from '@angular/material/dialog';
import { MatPaginator } from '@angular/material/paginator';
import { MatTableDataSource } from '@angular/material/table'
import { LugaresAtencion } from 'src/app/dto/lugarAtencion';
import { ConfigService } from 'src/app/services/config.service';
import { UtilHttpService } from 'src/app/services/util-http.service';
import { AgregarLugarAtencionComponent } from '../admin-crud/agregar-lugarAtencion/agregar-lugarAtencion.component';
import { BorrarLugarAtencionComponent } from '../admin-crud/borrar-lugarAtencion/borrar-lugarAtencion.component';
import { EditarLugarAtencionComponent } from '../admin-crud/editar-lugarAtencion/editar-lugarAtencion.component';

@Component({
  selector: 'app-admin-lugarAtencion',
  templateUrl: './admin-lugarAtencion.component.html',
  styleUrls: ['./admin-lugarAtencion.component.css']
})
export class AdminLugarAtencionComponent implements OnInit {
  @ViewChild(MatPaginator, { static: true }) paginator: MatPaginator;

  searchKey: string = "";

  displayedColumns: string[] = ['nombreLugarAtencion', 'direccionLugarAtencion', 'telefonoLugarAtencion',  'sitioWebLugarAtencion', 'latitudLugarAtencion', 'longitudLugarAtencion', 'estadoLugarAtencion','actions'];
  data: LugaresAtencion[] = [];
  datasource = new MatTableDataSource<any>();

  constructor(
    public dialog: MatDialog,
    public http: UtilHttpService,
    public config: ConfigService
  ) {
    this.datasource = new MatTableDataSource<any>();
  }

  ngOnInit(): void {
    //this.onConfigInit();
    this.onLoadLugarAtencion();//TODO: Activar esto y borrar la línea anterior
  }

  onLoadLugarAtencion() {
    this.data = this.data.splice(0, this.data.length);
    this.http.showBusy();
    this.http.get(this.config.prop.urllistAllLugarAtencion, null).subscribe((resp: LugaresAtencion[]) => {
      this.data = resp;
      this.http.closeBusy();
      this.onConfigInit();
    }, error => {
      console.error(error.error);
      this.http.closeBusy();
    })
  }

  onConfigInit() {
    this.datasource = new MatTableDataSource<any>(this.data);
    this.datasource.filterPredicate =
      (data: LugaresAtencion, filter: string) => data.nombreLugarAtencion.toLowerCase().indexOf(filter) !== -1;
    this.datasource.paginator = this.paginator;
  }

  onClearSearchKey() {
    this.searchKey = "";
    this.applyFilter();
  }

  onEditItem(index: number, element: any) {
    this.index = index;
    this.id = element.idLugarAtencion;
    const dialogRef = this.dialog.open(EditarLugarAtencionComponent, {
      data: element
    });
  }

  index: number;
  id: number;

  onDeleteItem(index: number, element: LugaresAtencion) {
    this.index = index;
    this.id = element.idLugaresAtencion;
    const dialogRef = this.dialog.open(BorrarLugarAtencionComponent, {
      data: element
    });


    dialogRef.afterClosed().subscribe(result => {
      if (result === 1) {
        //this.datasource.data.splice(index, 1);
        this.onLoadLugarAtencion();
        this.refreshTable();
      }
    });
  }

  private refreshTable() {
    this.paginator._changePageSize(this.paginator.pageSize);
  }

  applyFilter() {
    this.datasource.filter = this.searchKey.trim().toLowerCase();
  }

  onAddNew() {
    const dialogConfig = new MatDialogConfig();
    dialogConfig.width = '80vw';
    let dialogRef= this.dialog.open(AgregarLugarAtencionComponent, dialogConfig);
    dialogRef.afterClosed().subscribe(result => {
      if (result === 1) {
        //this.datasource.data.splice(index, 1);
        this.onLoadLugarAtencion();
        this.refreshTable();
      }
    });
    
    
  }

}

