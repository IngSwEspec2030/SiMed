import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { PagesComponent } from './pages.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms'
import { SharedModule } from '../shared/shared.module';
import { RouterModule } from '@angular/router';
import { DashboardComponent } from './dashboard/dashboard.component';
import { LoginComponent } from '../auth/login/login.component';
import { RegisterComponent } from '../auth/register/register.component';
import { AdminEpsComponent } from './eps/admin-eps/admin-eps.component';
import { AdminLugarComponent } from './lugarAtencion/admin-lugar/admin-lugar.component';
import { AdminUsuarioComponent } from './usuario/admin-usuario/admin-usuario.component';
import { AdminEspecialidadComponent } from './especialidad/admin-especialidad/admin-especialidad.component';
import { ComponentsModule } from '../components/components.module';



@NgModule({
  declarations: [
    PagesComponent,
    DashboardComponent,
    AdminEpsComponent,
    AdminLugarComponent,
    AdminEspecialidadComponent,
    AdminUsuarioComponent
  ],
  imports: [
    CommonModule,
    SharedModule,
    RouterModule,
    ReactiveFormsModule,
    ComponentsModule
  ]
})
export class PagesModule { }
