package fabrica;

import interfaces.IFachadaSala;
import subsistema.sala.FachadaSalaMock;

public class FabricaFachada {
    
    // Bandera para cambiar del Mock (Hito 2A) al Subsistema Real (Hito 2B) fácilmente más adelante
    private static final boolean USAR_MOCK = true;

    public static IFachadaSala crearFachadaSala() {
        if (USAR_MOCK) {
            return new FachadaSalaMock();
        } else {
            // Aquí retornaremos la Fachada real en el Hito 2B sin alterar las vistas/controladores
            return null; 
        }
    }
}