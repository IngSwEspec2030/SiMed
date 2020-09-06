import { Component, OnInit } from '@angular/core';
import { FormGroup } from '@angular/forms';

@Component({
  selector: 'app-admin-usuario',
  templateUrl: './admin-usuario.component.html',
  styleUrls: ['./admin-usuario.component.css']
})
export class AdminUsuarioComponent implements OnInit {

  registerForm:FormGroup;
  submitted:boolean;
  loading:boolean;
  constructor() { }

  ngOnInit(): void {
  }

  onSubmit(){
  }

}
