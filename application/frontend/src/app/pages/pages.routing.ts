import { Routes, RouterModule } from '@angular/router';
import { NgModule } from '@angular/core';

import { PagesComponent } from './pages.component';
import { DashboardComponent } from './dashboard/dashboard.component';
import { AdminUsuarioComponent } from './usuario/admin-usuario/admin-usuario.component';
import { AdminEpsComponent } from './eps/admin-eps/admin-eps.component';
import { AdminEspecialidadComponent } from './especialidad/admin-especialidad/admin-especialidad.component';
import { AdminLugarComponent } from './lugarAtencion/admin-lugar/admin-lugar.component';
import { UbicarLugaresComponent } from './lugarAtencion/ubicar-lugares/ubicar-lugares.component';
import { AuthRouteGuard } from '../services/auth/auth-route.guard';
import { Permisos } from '../services/auth/permission-checker.service';
import { OpcionesVisualizacionComponent } from './usuario/opciones-visualizacion/opciones-visualizacion.component';


const routes: Routes = [
    { 
        path: 'site', 
        component: PagesComponent,
        children: [
            { path: '', component: UbicarLugaresComponent },
            // { path: 'pages/find-sites', component: UbicarLugaresComponent },
            { path: 'pages/dashboard', component: DashboardComponent, canActivate: [AuthRouteGuard], data:{permission:Permisos.Dashboard} },
            { path: 'pages/admin-user', component: AdminUsuarioComponent, canActivate: [AuthRouteGuard],data:{permission:Permisos.AdminUsuario} },
            { path: 'pages/settings-user', component: OpcionesVisualizacionComponent, canActivate: [AuthRouteGuard],data:{permission:Permisos.AdminUsuario} },
            { path: 'pages/admin-eps', component: AdminEpsComponent, canActivate: [AuthRouteGuard],data:{permission:Permisos.AdminEps}  },
            { path: 'pages/admin-specialties', component: AdminEspecialidadComponent, canActivate: [AuthRouteGuard],data:{permission:Permisos.AdminEspecialidad}   },
            { path: 'pages/admin-site', component: AdminLugarComponent, canActivate: [AuthRouteGuard], data:{permission:Permisos.AdminLugares}   },
        ]
    },
];

@NgModule({
    imports: [ RouterModule.forChild(routes) ],
    exports: [ RouterModule ]
})
export class PagesRoutingModule {}


