import { Component, OnInit } from '@angular/core';
import { AuthService } from 'src/app/services/auth/auth.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

  userName:string;
  constructor(private authService:AuthService) { 
    this.userName=authService.getUserName();
  }

  ngOnInit(): void {
  }

  onLogout(){
    this.authService.logout();
  }

}
