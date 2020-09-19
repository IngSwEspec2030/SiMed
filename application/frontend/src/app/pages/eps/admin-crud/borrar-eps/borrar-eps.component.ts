import { Component, Inject, OnInit } from '@angular/core';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { Eps } from 'src/app/dto/eps';
import { ConfigService } from 'src/app/services/config.service';
import { UtilHttpService } from 'src/app/services/util-http.service';
import { ButtonType, DisplayMessage, IconType } from 'src/app/utils/messageSweet';

@Component({
  selector: 'app-borrar-eps',
  templateUrl: './borrar-eps.component.html',
  styleUrls: ['./borrar-eps.component.css']
})
export class BorrarEpsComponent implements OnInit {

  message=new DisplayMessage();
  constructor(
    public dialogRef: MatDialogRef<BorrarEpsComponent>,
    @Inject(MAT_DIALOG_DATA) public eps: Eps,
    public http:UtilHttpService,
    private config: ConfigService

    ) { }

  ngOnInit(): void {
  }

  onNoClick(): void {
    this.dialogRef.close();
  }

  confirmDelete(): void {
    this.http.showBusy();
    this.http.delete(this.config.prop.urlEps, this.eps.idEps)
    .subscribe(resp=>{
      this.http.closeBusy();
      this.message.displayInfoMessage("Admin","EPS borrada satisfactoriamente", IconType.info, ButtonType.Ok);      
    }, err=>{
      this.http.closeBusy();
      this.message.displayInfoMessage("Admin","No se pudo borrar el recurso", IconType.error, ButtonType.Ok);  
    });
  }

}
