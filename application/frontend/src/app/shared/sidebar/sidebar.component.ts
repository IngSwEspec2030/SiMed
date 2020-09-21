import { Component, OnInit } from '@angular/core';
import { Permisos, PermissionCheckerService } from 'src/app/services/auth/permission-checker.service';

@Component({
  selector: 'app-sidebar',
  templateUrl: './sidebar.component.html',
  styleUrls: ['./sidebar.component.css']
})
export class SidebarComponent implements OnInit {

  permisos=Permisos;
  constructor(public pc:PermissionCheckerService) {}

  ngOnInit(): void {
  } 

}
