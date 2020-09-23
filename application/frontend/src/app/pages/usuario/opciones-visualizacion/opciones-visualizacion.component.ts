import { Component, OnInit } from '@angular/core';
import { MatDialogRef } from '@angular/material/dialog';
import { SettingsService } from 'src/app/services/settings.service';

@Component({
  selector: 'app-opciones-visualizacion',
  templateUrl: './opciones-visualizacion.component.html',
  styleUrls: ['./opciones-visualizacion.component.css']
})
export class OpcionesVisualizacionComponent implements OnInit {

  constructor(private settingsService: SettingsService,
    public dialogRef: MatDialogRef<any>, ) { }

  ngOnInit(): void {
    this.settingsService.checkCurrentTheme();
  }

  changeTheme( theme: string ) {        
    this.settingsService.changeTheme( theme );    
  }

  onClick(){
    this.dialogRef.close();
  }

}
