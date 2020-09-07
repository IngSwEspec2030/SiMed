import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Usuario } from '../dto/usuario';

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

 
    constructor(private http: HttpClient) { }

    getAll() {
        return this.http.get<Usuario[]>('http://localhost:4000/usuarios');
    }

    register(user: Usuario) {
        
        console.log(user)
        return this.http.post('usuario/create', user, httpOptions);
    }

    delete(id: number) {
        return this.http.delete('http://localhost:4000/usuarios/${id}');
    }
}