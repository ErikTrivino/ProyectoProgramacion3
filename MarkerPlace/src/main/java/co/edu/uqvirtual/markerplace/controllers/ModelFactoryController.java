package co.edu.uqvirtual.markerplace.controllers;

import co.edu.uqvirtual.markerplace.exceptions.DatosNulosException;
import co.edu.uqvirtual.markerplace.exceptions.VendedorNoExisteException;
import co.edu.uqvirtual.markerplace.modelo.*;
import co.edu.uqvirtual.markerplace.persistence.Persistencia;
import co.edu.uqvirtual.markerplace.services.IModelFactoryService;
import javafx.scene.control.Alert;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;

public class ModelFactoryController implements IModelFactoryService, Runnable {
    MarketPlace marketPlace;
    Vendedor vendedorActual;

    Thread hiloServicio1_guardarResourceXml;
    Thread hiloServicio2_guardarRegistroLog;

    String mensaje;
    int nivel;
    String accion;


    Vendedor[] vendedores = new Vendedor[11];
    private Vendedor vendedorAutenticado;

    public Vendedor getVendedorActual() {
        return vendedorActual;
    }

    public void setVendedorActual(Vendedor vendedorActual) {
        this.vendedorActual = vendedorActual;
    }

    @Override
    public void run() {
        Thread hiloActual = Thread.currentThread();


        if(hiloActual == hiloServicio1_guardarResourceXml){
            Persistencia.guardarRecursoMarkBinario(marketPlace);
        }

        if(hiloActual == hiloServicio2_guardarRegistroLog){
            Persistencia.guardaRegistroLog(mensaje, nivel, accion);
        }


    }

    public Vendedor getVendedorAutenticado() {
        return vendedorAutenticado;
    }

    public void setVendedorAutenticado(Vendedor vendedorAutenticado) {
        this.vendedorAutenticado = vendedorAutenticado;
    }

    public ArrayList<Producto> obtenerProductosVendedor() {
        if(vendedorActual != null){
            return vendedorActual.getListaProductos();
        }else {
        return new ArrayList<>();
    }

    }

    private static class SingletonHolder {
        // El constructor de Singleton puede ser llamado desde aquí al ser protected
        private final static ModelFactoryController eINSTANCE = new ModelFactoryController();
    }
    public static ModelFactoryController getInstance() {
        return SingletonHolder.eINSTANCE;
    }


    public void asignarDatosArregloVendedores(){

        ArrayList<Vendedor> vendedorArrayList = obtenerVendedores();
        for (Vendedor v:vendedorArrayList
        ) {
            boolean bandera= true;
            if(!vendedorRepetido(v)){
                for(int i = 1; i < vendedores.length && bandera;i++){
                    if(vendedores[i] == null){
                        vendedores[i] = v;
                        bandera = false;
                    }
                }
            }
        }

    }
    public boolean vendedorRepetido(Vendedor vendedor) {

        for (Vendedor v : vendedores) {
            if(v != null){
                if (v.getNombre().equals(vendedor.getNombre())) {
                    return true;
                }
            }

        }
        return false;
    }

    public void eliminarVendedorArreglo(String cedula){
        for(int i = 0; i< vendedores.length;i++){
            if(vendedores[i] != null){
                if(vendedores[i].getCedula().equals(cedula)){
                    vendedores[i] = null;
                }
            }
        }
    }




    public int obtenerPosicionVendedorLog(){
        int r = 1;
        for(int i = 1;i < vendedores.length;i++){
            if(vendedores[i] != null){
                if(vendedores[i].getUsuario().getEstadoLogin()){
                    return r;
                }else{
                    r+=1;
                }
            }
        }

        if(marketPlace.getAdmin().isLogin()){
            r = 0;
        }


        return r;
    }
    public Optional<Vendedor> buscarVendedorLog() {
        return getMarketPlace().buscarVendedorLog();
    }

    public int obtenerPosicionLog() {
        return obtenerPosicionVendedorLog();
    }

    public ModelFactoryController() {
        //Respaldos

        guardarRespaldosArchivos();



        //1. inicializar datos y luego guardarlo en archivos
		inicializarSalvarDatos();


        //2. Cargar los datos de los archivos
   	    //cargarDatosDesdeArchivos();

        //3. Guardar y Cargar el recurso serializable binario
        //inicializarDatos();
        //guardarResourceBinario();
		//cargarResourceBinario();


        //4. Guardar y Cargar el recurso serializable XML
	    // guardarResourceXML();
        //cargarResourceXML();

        //Siempre se debe verificar si la raiz del recurso es null

        asignarDatosArregloVendedores();
        if(marketPlace == null){
            inicializarDatos();
            guardarResourceXML();
        }
        //registrarAccionesSistema("Datos cargados", 1, "Cargar datos");
        //inicializarDatos();
    }
    public void guardarRespaldosArchivos(){
        Persistencia.copiarArchivoRespaldoXml();
        Persistencia.copiarArchivoRespaldoBinario();
        Persistencia.copiarArchivoRespaldoVendedor();
    }
    public void guardarDatosArchivos() throws IOException {

        Persistencia.guardarVendedorTxt(getMarketPlace().getListVendedores());
        Persistencia.copiarArchivoRespaldoVendedor();
        Persistencia.guardarRecursoMarketplaceXML(marketPlace);
        Persistencia.guardarRecursoMarkBinario(marketPlace);

        asignarDatosArregloVendedores();




    }
    public void inicializarDatos() {

        marketPlace = new MarketPlace();

        Vendedor v1 = new Vendedor("Diego", "Jimenez", "1234", new Usuario("1234", "1234"));
        Vendedor v2 = new Vendedor("Kevin", "Payanene", "321", new Usuario("321", "321"));
        v2.getListaProductos().add(new Producto("Soda L", "Null", "2000", Estado.PUBLICADO));
        v1.getListaProductos().add(new Producto("Menta L", "Null", "120", Estado.PUBLICADO));
        Vendedor v3= new Vendedor("Valeria", "Mendoza", "4321", new Usuario("4321", "4321"));



        Solicitud s1 =  new Solicitud("Kevin", "1005134113", false);
        v1.getListaSolicitudes().add(s1);

        v1.getListaAliados().add(v2);


        marketPlace.getListVendedores().add(v1);
        marketPlace.getListVendedores().add(v2);
        marketPlace.getListVendedores().add(v3);
        registrarAccionesSistema("incio sesion", 1, "inicializar Datos");

    }
    private void cargarResourceXML(){
        marketPlace = Persistencia.cargarRecursoMarketplaceXML();
    }
    private void guardarResourceXML() {
        hiloServicio1_guardarResourceXml = new Thread(this);
        hiloServicio1_guardarResourceXml.start();

        //Quitar
        //Persistencia.guardarRecursoMarketplaceXML(marketPlace);

    }

    private void cargarResourceBinario() {
        marketPlace = Persistencia.cargarRecursomarkBinario();

    }

    private void guardarResourceBinario() {

        inicializarDatos();
        Persistencia.guardarRecursoMarkBinario(marketPlace);

    }

    private void inicializarSalvarDatos() {
        inicializarDatos();
        try {
            Persistencia.guardarVendedorTxt(getMarketPlace().getListVendedores());
            Persistencia.copiarArchivoRespaldoVendedor();

            //Persistencia.guardarProductoTxt(getBanco().getListaEmpleados());
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
    private void cargarDatosDesdeArchivos() {
        this.marketPlace = new MarketPlace();
        try {
            ArrayList<Vendedor> listaVendedores = new ArrayList<>();
            listaVendedores = Persistencia.cargarVendedores();
            getMarketPlace().getListVendedores().addAll(listaVendedores);

        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }


    public void registrarAccionesSistema(String mensaje, int nivel, String accion) {
        this.mensaje=mensaje;
        this.nivel=nivel;
        this.accion=accion;


        hiloServicio2_guardarRegistroLog = new Thread(this);
        hiloServicio2_guardarRegistroLog.start();



        //Persistencia.guardaRegistroLog(mensaje, nivel, accion);
    }







    public MarketPlace getMarketPlace() {
        return this.marketPlace;
    }

    public void setMarketPlace(MarketPlace marketPlace) {
        this.marketPlace = marketPlace;
    }



    /**
     *
     * CRUD VENDEDORES
     */
    public boolean verificarLogin(String usuario, String contrasenia) {
        return this.getMarketPlace().verificarLogin(usuario, contrasenia);
    }
    public Vendedor loginVendedor(String nombre) throws VendedorNoExisteException {
        return this.getMarketPlace().obtenerVendedorLogin(nombre);
    }

    public void eliminarSolicitud(String cedula)  {
        try {
            this.getMarketPlace().eliminarSolicitud(cedula, vendedorAutenticado);
            guardarDatosArchivos();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void eliminarVendedor(String cedula)  {

        try {
            this.getMarketPlace().eliminarVendedor(cedula);
            eliminarVendedorArreglo(cedula);
            guardarDatosArchivos();
        }catch (Exception e){
            e.printStackTrace();
        }

    }


    @Override
    public boolean verificarVendedorExistente(String cedula) {
        try {
            return getMarketPlace().verificarVendedorExistente(cedula);

        }catch (Exception e){
            e.printStackTrace();
            return false;
        }

    }
    @Override
    public Vendedor obtenerVendedor(String nombreUsuario) {
        try {
            vendedorAutenticado = getMarketPlace().obtenerVendedor(nombreUsuario);
            return vendedorAutenticado;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }

    }

    @Override
    public Vendedor crearVendedor(String nombre, String apellido, String cedula, String usuario, String contrasenia) {
        Vendedor vendedor = null;

        try {
            vendedor = marketPlace.crearVendedor(nombre, apellido, cedula, usuario, contrasenia);
            /*Persistencia.guardarVendedorTxt(getMarketPlace().getListVendedores());
            Persistencia.guardarRecursoMarketplaceXML(marketPlace);
            Persistencia.guardarRecursoMarkBinario(marketPlace);*/
            guardarDatosArchivos();

        } catch (DatosNulosException | IOException e1) {
            e1.printStackTrace();
        }

        return vendedor;
    }

    @Override
    public void actualizarVendedor(String nombre, String apellido, String cedula, String usuario, String contrasenia)  {

        try{
            this.getMarketPlace().actualizarVendedor(nombre,apellido,cedula,usuario,contrasenia);
            guardarDatosArchivos();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     *
     * CRUD PRODUCTOS
     */


    @Override
    public ArrayList<Vendedor> obtenerVendedores() {
        return marketPlace.obtenerEmpleados();
    }


    /**
     * CRUD PRODUCTOS
     * */

    @Override
    public Producto crearProducto(String nombre, String imagen, String precio, Estado estado,String cedulaVendedor) {
        try{
           Producto p1 =  this.getMarketPlace().crearProducto(nombre,imagen, precio, estado,cedulaVendedor);
            guardarDatosArchivos();
            return p1;

        }catch (Exception e){
            e.printStackTrace();
            return null;

        }

    }

    @Override
    public void actualizarProducto(String nombre, String imagen, String precio, Estado estado,String cedulaVendedor) {
        try{
            this.getMarketPlace().actualizarProducto(nombre, imagen, precio, estado, cedulaVendedor);
            guardarDatosArchivos();
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    @Override
    public void eliminarProducto(String nombre,String cedulaVendedor) {
        try{
            this.getMarketPlace().eliminarProducto(nombre, cedulaVendedor);
            guardarDatosArchivos();

        }catch (Exception e){
            e.printStackTrace();
        }
    }
    /*
    public void eliminarSolicitud(String nombre,String cedulaVendedor) {
        try{
            this.getMarketPlace().eliminarSolicitud(nombre, cedulaVendedor);
            guardarDatosArchivos();

        }catch (Exception e){
            e.printStackTrace();
        }
    }
     */


    @Override
    public Producto obtenerProducto(String nombre,String cedulaVendedor) {
        try{
            return this.getMarketPlace().obtenerProducto(nombre, cedulaVendedor);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }

    }

    @Override
    public boolean verificarProductoExistente(String nombre,String cedulaVendedor) {
        try{
            return this.getMarketPlace().verificarProductoExistente(nombre, cedulaVendedor);
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }
    /*
    public boolean verificarSolicitudExiste(String nombre,String cedulaVendedor) {
        try{
            return this.getMarketPlace().verificarSolicitudExistente(nombre, cedulaVendedor);
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }
     */

    @Override
    public ArrayList<Producto> obtenerProductos(String cedulaVendedor) {
        //ArrayList<Producto> pl = this.getMarketPlace().obtenerProductos(cedulaVendedor);
        //pl.forEach(System.out::println);
        return this.getMarketPlace().obtenerProductos(cedulaVendedor);
    }
    public ArrayList<Solicitud> obtenerSolicitud(String cedulaVendedor){
        return  this.getMarketPlace().obtenerSolicitudes(cedulaVendedor);
    }





    void mostrarMensaje(String titulo, String header, String contenido, Alert.AlertType alertType) {

        Alert alerta = new Alert(alertType);
        alerta.setTitle(titulo);
        alerta.setHeaderText(header);
        alerta.setContentText(contenido);
        alerta.showAndWait();

    }
    //Chat
    public void iniciarChat(String texto) {
        //marketPlace.iniciarChat(vendedorSeleccionado,vendedorActual,chat);
    }

    void agregarMegustaProducto(Producto productoSeleccionado) {
        this.getMarketPlace().agregarMeGustaProducto(productoSeleccionado, vendedorActual);

        //Colocarlo
        //guardarXML= new Thread(this);
        //guardarXML.start();



//		guardarBIN= new Thread(this);
//		guardarBIN.start();

    }
    public ArrayList<Producto> obtenerProductos() {
        ArrayList<Producto> listaProductos = new ArrayList<>();
        if(vendedorActual != null){
            ArrayList<Vendedor> obtenerVendedores = vendedorActual.getListaAliados();


            //ArrayList<Producto> listaProductos = new ArrayList<>();
            //listaProductos.addAll(vendedorActual.getListaProductos());
            for (Vendedor vendedor : obtenerVendedores) {
//            if(vendedor.getListaProductos().get(0) != null){
//                System.out.println(vendedor.getListaProductos().get(0));
//            }
                listaProductos.addAll(vendedor.getListaProductos());
            }
            //System.out.println(listaProductos.toString());
            return listaProductos;
        }else{
            return listaProductos;
        }

    }

    public ArrayList<Vendedor> obtenerVendedorAliado() {
        if(vendedorActual != null){
            return vendedorActual.getListaAliados();
        }else {
            return new ArrayList<>();
        }

    }
}
