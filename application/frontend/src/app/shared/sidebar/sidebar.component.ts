import { Component, OnInit } from '@angular/core';
import { MatDialog, MatDialogConfig } from '@angular/material/dialog';
import { OpcionesVisualizacionComponent } from 'src/app/pages/usuario/opciones-visualizacion/opciones-visualizacion.component';
import { Permisos, PermissionCheckerService } from 'src/app/services/auth/permission-checker.service';

@Component({
  selector: 'app-sidebar',
  templateUrl: './sidebar.component.html',
  styleUrls: ['./sidebar.component.css']
})
export class SidebarComponent implements OnInit {

  permisos=Permisos;
  constructor(public pc:PermissionCheckerService, public dialog: MatDialog) {}

  ngOnInit(): void {
  } 

  onOpenThema(){
    const dialogConfig = new MatDialogConfig();
    dialogConfig.width = '80vw';
    this.dialog.open(OpcionesVisualizacionComponent, dialogConfig);

  }

}
