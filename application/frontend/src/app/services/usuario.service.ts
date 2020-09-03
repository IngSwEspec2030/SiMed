import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Usuario } from '../dto/usuario';


@Injectable({ providedIn: 'root' })
export class UserService {
    constructor(private http: HttpClient) { }

    getAll() {
        return this.http.get<Usuario[]>('http://localhost:4000/usuarios');
    }

    register(user: Usuario) {
        console.log(user)
        return this.http.post('http://localhost:4000/usuarios/registrar', user);
    }

    delete(id: number) {
        return this.http.delete('http://localhost:4000/usuarios/${id}');
    }
}