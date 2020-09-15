import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Usuario } from '../dto/usuario';
import { environment } from 'src/environments/environment';

const httpOptions = {
    headers: new HttpHeaders({ 
      'Access-Control-Allow-Origin':'*',
      'Access-Control-Allow-Methods': 'GET, POST, PATCH, PUT, DELETE, OPTIONS',
      'Access-Control-Allow-Headers': 'X-API-KEY, Origin, X-Requested-With, Content-Type, Accept, Access-Control-Request-Method',
      'Allow': '*',
      'Content-Type': 'application/json'
    })
  };
@Injectable({ providedIn: 'root' })
export class UserService {
      
     
      apiEndPoint:string="";
 
    constructor(private http: HttpClient) {
        this.apiEndPoint = environment.apiEndPoint;

     }

    getAll() {
        return this.http.get<Usuario[]>(`${this.apiEndPoint}/usuarios`);
    }

    register(user: Usuario) {
    
        console.log(user)
        return this.http.post(`${this.apiEndPoint}/usuario/create`,  user, httpOptions);
    }

    delete(id: number) {
        return this.http.delete('http://localhost:4000/usuarios/${id}');
    }
}