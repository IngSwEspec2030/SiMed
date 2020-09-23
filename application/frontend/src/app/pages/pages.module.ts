import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { PagesComponent } from './pages.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms'
import { SharedModule } from '../shared/shared.module';
import { RouterModule } from '@angular/router';
import { DashboardComponent } from './dashboard/dashboard.component';
import { AdminLugarComponent } from './lugarAtencion/admin-lugar/admin-lugar.component';
import { AdminUsuarioComponent } from './usuario/admin-usuario/admin-usuario.component';
import { AdminEspecialidadComponent } from './especialidad/admin-especialidad/admin-especialidad.component';
import { ComponentsModule } from '../components/components.module';
import { UbicarLugaresComponent } from './lugarAtencion/ubicar-lugares/ubicar-lugares.component';

//EPS
import { AdminEpsComponent } from './eps/admin-eps/admin-eps.component';
import { AgregarEpsComponent } from './eps/admin-crud/agregar-eps/agregar-eps.component';
import { BorrarEpsComponent } from './eps/admin-crud/borrar-eps/borrar-eps.component';
import { EditarEpsComponent } from './eps/admin-crud/editar-eps/editar-eps.component';


//import material
import {MatCardModule} from '@angular/material/card';
import {MatTableModule} from '@angular/material/table';
import {MatIconModule} from '@angular/material/icon';
import { MatDialogModule } from '@angular/material/dialog';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatPaginatorModule } from '@angular/material/paginator';
import { ActivoPipe } from '../utils/pipes/activo.pipe';
import { OpcionesVisualizacionComponent } from './usuario/opciones-visualizacion/opciones-visualizacion.component';


@NgModule({
  declarations: [
    PagesComponent,
    DashboardComponent,
    AdminLugarComponent,
    AdminEspecialidadComponent,
    AdminUsuarioComponent,
    UbicarLugaresComponent,
    AdminEpsComponent,AgregarEpsComponent,BorrarEpsComponent,EditarEpsComponent,
    ActivoPipe,
    OpcionesVisualizacionComponent
  ],
  imports: [
    CommonModule,
    SharedModule,
    RouterModule,
    ReactiveFormsModule,
    ComponentsModule,
    FormsModule,
    MatTableModule,MatIconModule, MatCardModule, MatDialogModule, MatFormFieldModule,MatPaginatorModule
  ],
  exports:[ActivoPipe]
})
export class PagesModule { }
