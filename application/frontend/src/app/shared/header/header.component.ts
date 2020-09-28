import { Component, OnInit } from '@angular/core';
import { MatDialog, MatDialogConfig } from '@angular/material/dialog';
import { BorrarUsuarioComponent } from 'src/app/pages/usuario/borrar/borrar-usuario.component';
import { AuthService } from 'src/app/services/auth/auth.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

  userName:string;
  constructor(private authService:AuthService,  public dialog: MatDialog) {
    if(!this.authService.getUserRegistered()){
      this.onLogout();
      return;
    }
    this.userName=authService.getUserName();
  }

  ngOnInit(): void {
  }

  onLogout(){
    this.authService.logout();
  }

  onDeleteUser(){
    const dialogConfig = new MatDialogConfig();
    dialogConfig.width = '80vw';
    this.dialog.open(BorrarUsuarioComponent, dialogConfig);
  }

}
