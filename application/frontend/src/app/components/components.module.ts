import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { ChartsModule } from 'ng2-charts';
import { DonaComponent } from './graphs/dona/dona.component';
import { MapsComponent } from './maps/maps.component';

import { AgmCoreModule } from '@agm/core';



@NgModule({
  declarations: [DonaComponent, MapsComponent],
  exports:[
    DonaComponent,
    MapsComponent
  ],
  imports: [
    CommonModule,
    FormsModule,
    ChartsModule,
    AgmCoreModule.forRoot({
      apiKey: 'AIzaSyCCC4V8daeyYl0GX396AVedU0mI94aYZIs'
    })
  ]
})
export class ComponentsModule { }
