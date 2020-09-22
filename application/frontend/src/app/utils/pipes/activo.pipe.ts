import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'boolTranslate'
})
export class ActivoPipe implements PipeTransform {

  transform(value: boolean, args?: any): string {
    return  value?'Activo':'Inactivo';
  }
}