export class Marcador{
    public lat:number;
    public lng:number;
    public titulo:string='Sitio';
    public descripcion:string='Descripción General';
    public valido:boolean=false;


    constructor(lat:number, lng:number) {
        this.lat=lat;
        this.lng=lng;
    }
}