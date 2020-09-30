import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule, ReactiveFormsModule } from '@angular/forms'
import { ChartsModule } from 'ng2-charts';
import { DonaComponent } from './graphs/dona/dona.component';
import { MapsComponent } from './maps/maps.component';

import { AgmCoreModule } from '@agm/core';
import { IpsListComponent } from './modals/ips-list/ips-list.component';



@NgModule({
  declarations: [DonaComponent, MapsComponent, IpsListComponent],
  exports:[
    DonaComponent,
    MapsComponent,
    IpsListComponent
  ],
  imports: [
    CommonModule,
    FormsModule,ReactiveFormsModule,
    ChartsModule,
    AgmCoreModule.forRoot({
      apiKey: 'AIzaSyCCC4V8daeyYl0GX396AVedU0mI94aYZIs'
    })
  ]
})
export class ComponentsModule { }
