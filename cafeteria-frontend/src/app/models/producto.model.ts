
export interface Producto {
  id: number;
  nombre: string;
  categoria: string;
  precio: number;
  estado: string;
}

export const CATEGORIAS_PRODUCTO = [
  'Bebidas Calientes',
  'Bebidas Frías',
  'Bebidas Especiales',
  'Cafés Premium',
  'Tés e Infusiones',
  'Panadería',
  'Repostería',
  'Postres',
  'Snacks Dulces',
  'Snacks Salados',
  'Sándwiches',
  'Desayunos',
  'Almuerzos',
  'Otros'
] as const;
