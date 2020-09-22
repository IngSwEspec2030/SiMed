import { Component, OnInit, ViewChild } from '@angular/core';
import { MatDialog, MatDialogConfig } from '@angular/material/dialog';
import { MatPaginator } from '@angular/material/paginator';
import { MatTableDataSource } from '@angular/material/table'
import { Eps } from 'src/app/dto/eps';
import { ConfigService } from 'src/app/services/config.service';
import { UtilHttpService } from 'src/app/services/util-http.service';
import { AgregarEpsComponent } from '../admin-crud/agregar-eps/agregar-eps.component';
import { BorrarEpsComponent } from '../admin-crud/borrar-eps/borrar-eps.component';
import { EditarEpsComponent } from '../admin-crud/editar-eps/editar-eps.component';

@Component({
  selector: 'app-admin-eps',
  templateUrl: './admin-eps.component.html',
  styleUrls: ['./admin-eps.component.css']
})
export class AdminEpsComponent implements OnInit {
  @ViewChild(MatPaginator, { static: true }) paginator: MatPaginator;

  searchKey: string = "";

  displayedColumns: string[] = ['nombreEps', 'direccionEps', 'telefonoEps', 'estadoEps','actions'];
  data: Eps[] = [];
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
    this.onLoadEps();//TODO: Activar esto y borrar la línea anterior
  }

  onLoadEps() {
    this.data = this.data.splice(0, this.data.length);
    this.http.showBusy();
    this.http.get(this.config.prop.urllistAllEps, null).subscribe((resp: Eps[]) => {
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
      (data: Eps, filter: string) => data.nombreEps.toLowerCase().indexOf(filter) !== -1;
    this.datasource.paginator = this.paginator;
  }

  onClearSearchKey() {
    this.searchKey = "";
    this.applyFilter();
  }

  onEditItem(index: number, element: any) {
    this.index = index;
    this.id = element.idEps;
    const dialogRef = this.dialog.open(EditarEpsComponent, {
      data: element
    });
  }

  index: number;
  id: number;

  onDeleteItem(index: number, element: Eps) {
    this.index = index;
    this.id = element.idEps;
    const dialogRef = this.dialog.open(BorrarEpsComponent, {
      data: element
    });


    dialogRef.afterClosed().subscribe(result => {
      if (result === 1) {
        //this.datasource.data.splice(index, 1);
        this.onLoadEps();
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
    let dialogRef= this.dialog.open(AgregarEpsComponent, dialogConfig);
    dialogRef.afterClosed().subscribe(result => {
      if (result === 1) {
        //this.datasource.data.splice(index, 1);
        this.onLoadEps();
        this.refreshTable();
      }
    });
    
    
  }

}

