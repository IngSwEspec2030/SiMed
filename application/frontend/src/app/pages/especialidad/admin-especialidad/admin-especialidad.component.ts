import { Component, OnInit, ViewChild } from '@angular/core';
import { MatDialog, MatDialogConfig } from '@angular/material/dialog';
import { MatPaginator } from '@angular/material/paginator';
import { MatTableDataSource } from '@angular/material/table'
import { Especialidad } from 'src/app/dto/Especialidad';
import { ConfigService } from 'src/app/services/config.service';
import { UtilHttpService } from 'src/app/services/util-http.service';
import { AgregarEspecialidadComponent } from '../admin-crud/agregar-especialidad/agregar-especialidad.component';
import { BorrarEspecialidadComponent } from '../admin-crud/borrar-especialidad/borrar-especialidad.component';
import { EditarEspecialidadComponent } from '../admin-crud/editar-especialidad/editar-especialidad.component';

@Component({
  selector: 'app-admin-especialidad',
  templateUrl: './admin-especialidad.component.html',
  styleUrls: ['./admin-especialidad.component.css']
})
export class AdminEspecialidadComponent implements OnInit {
  @ViewChild(MatPaginator, { static: true }) paginator: MatPaginator;

  searchKey: string = "";

  displayedColumns: string[] = ['nombreEspecialidad', 'estadoEspecialidad','actions'];
  data: Especialidad[] = [];
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
    this.onLoadEspecialidad();//TODO: Activar esto y borrar la línea anterior
  }

  onLoadEspecialidad() {
    this.data = this.data.splice(0, this.data.length);
    this.http.showBusy();
    this.http.get(this.config.prop.urllistAllEspecialidad, null).subscribe((resp: Especialidad[]) => {
      this.data = resp;
      this.http.closeBusy();
      this.onConfigInit();
    }, error => {
      console.error(error);
      this.http.closeBusy();
    })
  }

  onConfigInit() {
    this.datasource = new MatTableDataSource<any>(this.data);
    this.datasource.filterPredicate =
      (data: Especialidad, filter: string) => data.nombreEspecialidad.toLowerCase().indexOf(filter) !== -1;
    this.datasource.paginator = this.paginator;
  }

  onClearSearchKey() {
    this.searchKey = "";
    this.applyFilter();
  }

  onEditItem(index: number, element: any) {
    this.index = index;
    this.id = element.idEspecialidad;
    const dialogRef = this.dialog.open(EditarEspecialidadComponent, {
      data: element
    });
  }

  index: number;
  id: number;

  onDeleteItem(index: number, element: Especialidad) {
    this.index = index;
    this.id = element.idEspecialidad;
    const dialogRef = this.dialog.open(BorrarEspecialidadComponent, {
      data: element
    });


    dialogRef.afterClosed().subscribe(result => {
      if (result === 1) {
        //this.datasource.data.splice(index, 1);
        this.onLoadEspecialidad();
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
    let dialogRef= this.dialog.open(AgregarEspecialidadComponent, dialogConfig);
    dialogRef.afterClosed().subscribe(result => {
      if (result === 1) {
        //this.datasource.data.splice(index, 1);
        this.onLoadEspecialidad();
        this.refreshTable();
      }
    });
    
    
  }

}

