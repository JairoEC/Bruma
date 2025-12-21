export interface Rol {
  id: number;
  nombre: string;
  estado: string
}


export interface Usuario {
  id: number;              // Java Long -> TS number
  nombre: string;
  apellidos: string;
  dni: string;
  email: string;
  celular: string;
  fechaNacimiento: string; // Java LocalDate llega como Texto ("2025-05-09") en JSON
  estado: string;
  roles: Rol[];            // Java List<Rol> -> TS Rol[]
}
