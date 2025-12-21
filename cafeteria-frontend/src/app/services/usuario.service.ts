import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Usuario } from '../models/usuario.model';
import { Router } from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class UsuarioService {

  private authUrl='http://localhost:8080/auth';
  private apiUrl = 'http://localhost:8080/api/empleados';

  constructor(private http: HttpClient) { }

  getAllEmpleados(): Observable<Usuario[]> {
    return this.http.get<Usuario[]>(this.apiUrl);
  }

  registrarEmpleado(usuario:any): Observable<any>{
    return this.http.post(`${this.authUrl}/registrar`, usuario)
  }

  getEmpleadoById(id: number): Observable<Usuario> {
    return this.http.get<Usuario>(`${this.apiUrl}/${id}`);
  }

  actualizarEmpleado(id: number, usuario: any): Observable<any> {
    return this.http.put(`${this.apiUrl}/${id}`, usuario);
  }

  login(credentials: any): Observable<any> {
    // Esto hará POST a http://localhost:8080/auth/login
    return this.http.post(`${this.authUrl}/login`, credentials);
  }
  logout(){
    localStorage.clear();
  }

  estarlogueado():boolean{
    return !!localStorage.getItem('token');
  }


}
