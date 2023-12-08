public class ArbolBinario {
    Node raiz;

    // Constructor para crear el árbol con una raíz inicial
    public ArbolBinario(String dato) {
        // Crear raíz
        this.raiz = new Node();
        this.raiz.name = dato;
        this.raiz.izquierdo = null;
        this.raiz.derecho = null;
    }

    // Método para agregar un hijo izquierdo a un nodo específico
    public void agregarHijoIzquierdo(String dato, Node arbol) {
        // Crear nodo hijo
        Node hijo = new Node();
        hijo.name = dato;
        hijo.izquierdo = null;
        hijo.derecho = null;

        // Agregar como hijo izquierdo
        arbol.izquierdo = hijo;

        // Liberar la referencia auxiliar
        hijo = null;
    }

    // Método para agregar un hijo derecho a un nodo específico
    public void agregarHijoDerecho(String dato, Node arbol) {
        // Crear nodo hijo
        Node hijo = new Node();
        hijo.name = dato;
        hijo.izquierdo = null;
        hijo.derecho = null;

        // Agregar como hijo derecho
        arbol.derecho = hijo;

        // Liberar la referencia auxiliar
        hijo = null;
    }

    // Método para imprimir el árbol de manera horizontal
    public void imprimirHorizontal() {
        imprimirHorizontalUtil(raiz, 0);
    }

    private void imprimirHorizontalUtil(Node nodo, int nivel) {
        if (nodo != null) {
            imprimirHorizontalUtil(nodo.derecho, nivel + 1);

            for (int i = 0; i < nivel; i++) {
                System.out.print("    ");
            }

            System.out.println(nodo.name);

            imprimirHorizontalUtil(nodo.izquierdo, nivel + 1);
        }
    }

    // Clase interna para almacenar el resultado de la búsqueda
    public static class ResultadoBusqueda {
        Node nodoEncontrado;
        String posicion;

        public ResultadoBusqueda(Node nodoEncontrado, String posicion) {
            this.nodoEncontrado = nodoEncontrado;
            this.posicion = posicion;
        }
    }

    // Método para buscar un nodo por su valor
    public ResultadoBusqueda buscarNodo(String valor) {
        return buscarNodoRecursivo(raiz, valor, "");
    }

    private ResultadoBusqueda buscarNodoRecursivo(Node nodo, String valor, String posicion) {
        if (nodo == null) {
            return null;
        }

        if (nodo.name.equals(valor)) {
            return new ResultadoBusqueda(nodo, posicion);
        }

        // Buscar en el subárbol izquierdo
        ResultadoBusqueda resultadoIzquierdo = buscarNodoRecursivo(nodo.izquierdo, valor, posicion + "I");
        if (resultadoIzquierdo != null) {
            return resultadoIzquierdo;
        }

        // Buscar en el subárbol derecho
        ResultadoBusqueda resultadoDerecho = buscarNodoRecursivo(nodo.derecho, valor, posicion + "D");
        return resultadoDerecho;
    }

    // Método para recorrer el árbol en preorden
    public void recorrerPreorden() {
        recorrerPreordenUtil(raiz);
    }

    private void recorrerPreordenUtil(Node nodo) {
        if (nodo != null) {
            System.out.println(nodo.name + " "); // Imprimir el nodo actual
            recorrerPreordenUtil(nodo.izquierdo); // Recorrer el subárbol izquierdo
            recorrerPreordenUtil(nodo.derecho);   // Recorrer el subárbol derecho
        }
    }

    // Método para recorrer el árbol en inorden
    public void recorrerInorden() {
        recorrerInordenUtil(raiz);
    }

    private void recorrerInordenUtil(Node nodo) {
        if (nodo != null) {
            recorrerInordenUtil(nodo.izquierdo); // Recorrer el subárbol izquierdo
            System.out.println(nodo.name + " ");  // Imprimir el nodo actual
            recorrerInordenUtil(nodo.derecho);    // Recorrer el subárbol derecho
        }
    }

    // Método para recorrer el árbol en postorden
    public void recorrerPostorden() {
        recorrerPostordenUtil(raiz);
    }

    private void recorrerPostordenUtil(Node nodo) {
        if (nodo != null) {
            recorrerPostordenUtil(nodo.izquierdo); // Recorrer el subárbol izquierdo
            recorrerPostordenUtil(nodo.derecho);   // Recorrer el subárbol derecho
            System.out.println(nodo.name + " ");   // Imprimir el nodo actual
        }
    }

    // Método para eliminar un nodo por su valor
    public void eliminarNodo(String valor) {
        raiz = eliminarNodoRecursivo(raiz, valor);
    }

    private Node eliminarNodoRecursivo(Node nodo, String valor) {
        if (nodo == null) {
            return null;
        }

        // Buscar el nodo a eliminar en el subárbol izquierdo
        if (valor.compareTo(nodo.name) < 0) {
            nodo.izquierdo = eliminarNodoRecursivo(nodo.izquierdo, valor);
        }
        // Buscar el nodo a eliminar en el subárbol derecho
        else if (valor.compareTo(nodo.name) > 0) {
            nodo.derecho = eliminarNodoRecursivo(nodo.derecho, valor);
        }
        // Nodo encontrado, proceder a la eliminación
        else {
            // Caso 1: Nodo es una hoja (sin hijos)
            if (nodo.izquierdo == null && nodo.derecho == null) {
                return null;
            }
            // Caso 2: Nodo tiene un solo hijo
            else if (nodo.izquierdo == null) {
                return nodo.derecho;
            } else if (nodo.derecho == null) {
                return nodo.izquierdo;
            }
            // Caso 3: Nodo tiene dos hijos
            else {
                // Encontrar el sucesor en orden en el subárbol derecho
                Node sucesor = encontrarNodoSucesor(nodo.derecho);
                // Copiar el valor del sucesor al nodo actual
                nodo.name = sucesor.name;
                // Eliminar el sucesor en el subárbol derecho
                nodo.derecho = eliminarNodoRecursivo(nodo.derecho, sucesor.name);
            }
        }

        return nodo;  // Devolver el nodo actualizado después de la eliminación
    }

    // Método auxiliar para encontrar el sucesor en orden
    private Node encontrarNodoSucesor(Node nodo) {
        while (nodo.izquierdo != null) {
            nodo = nodo.izquierdo;
        }
        return nodo;
    }
}
