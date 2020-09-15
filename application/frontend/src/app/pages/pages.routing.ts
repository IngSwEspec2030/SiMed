import { Routes, RouterModule } from '@angular/router';
import { NgModule } from '@angular/core';

import { PagesComponent } from './pages.component';
import { DashboardComponent } from './dashboard/dashboard.component';
import { AdminUsuarioComponent } from './usuario/admin-usuario/admin-usuario.component';
import { AdminEpsComponent } from './eps/admin-eps/admin-eps.component';
import { AdminEspecialidadComponent } from './especialidad/admin-especialidad/admin-especialidad.component';
import { AdminLugarComponent } from './lugarAtencion/admin-lugar/admin-lugar.component';
import { UbicarLugaresComponent } from './lugarAtencion/ubicar-lugares/ubicar-lugares.component';


const routes: Routes = [
    { 
        path: 'site', 
        component: PagesComponent,
        children: [
            { path: '', component: UbicarLugaresComponent },
            // { path: 'pages/find-sites', component: UbicarLugaresComponent },
            { path: 'pages/dashboard', component: DashboardComponent },
            { path: 'pages/admin-user', component: AdminUsuarioComponent },
            { path: 'pages/admin-eps', component: AdminEpsComponent },
            { path: 'pages/admin-specialties', component: AdminEspecialidadComponent },
            { path: 'pages/admin-site', component: AdminLugarComponent },
        ]
    },
];

@NgModule({
    imports: [ RouterModule.forChild(routes) ],
    exports: [ RouterModule ]
})
export class PagesRoutingModule {}


