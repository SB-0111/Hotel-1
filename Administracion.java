
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;

class ENoElemento extends Exception{
  ENoElemento(){
    super("No se encuentra el elemento buscado");
  }
}

class NoExisteEmpleadoException extends Exception{
    public NoExisteEmpleadoException(){
        super("No se encontró el empleado");
    }
}

class NoExisteReserva extends Exception {
	public noExisteReserva() {
		super(" No Existe la reserva");
	}
}

class NoSeEncuentraReserva extends Exception {
	public noSeEncuentraReserva() {
		super(" No se encuentra la reserva");
	}
}

class YaExisteReserva extends Exception {
	public yaExisteReserva() {
		super(" Ya existe la reserva");
	}
}

public class Administracion{
  
  private Cliente[] clientes;
  private Empleado[] empleados;
  private Reserva reservas[];
	private Habitacion[] habitaciones;
	private double cuenta;
  
  public buscarHabitacion(String codigoHabitacion) throws ENoElemento{
    int n=0;
    boolean b=false;
    while(n<habitaciones.length&&!b){
      if(habitaciones[n].getCodigo.equals(codigoHabitacion))
        return habitaciones[n];}
	  n++;
    }
    throw new ENoElemento();
  }
  
  public boolean existeHabitacion(String codigoHabitacion) throws ENoElemento{
    if(buscarHabitacion(codigoHabitacion)==null)
      return false;
    else
      return true;
  }
	
	public void escribirObjeto(String direccion) throws IOException {
		for (int i = 0; i < clientes.length; i++) {
			clientes[i].escribirObjeto(direccion+"\\" + personas[i].getNombre+".clt");
		}
	}
	
	public void leerObjetos(File f) throws ClassNotFoundException, IOException {
		File[] listF=f.listFiles(new Filtro(".clt"));
		for (int i = 0; i < listF.length; i++) {
			clientes=Arrays.copyOf(clientes, clientes.lenght+1);
			clientes[clientes.length-1].leerObjeto(listF[i]);
		}
	}
	
	public boolean existeEmpleado(String cedula){
        	boolean existe = false;
        	int i = 0;
        	while(i < empleados.length - 1){
            		if (empleados[i].getCedula.equals(cedula)){
                		existe = true;
                		break;
            		}
            		i++;
        	}
        	return existe;
    	}
	
	public Empleado buscarEmpleado(String cedula) throws NoExisteEmpleadoException{
        	if(!existeEmpleado(cedula)){
            		throw new NoExisteEmpleadoException();
        	}
        	Empleado empleado = null;
        	int i = 0;
        	while(i < empleados.length - 1){
            		if (empleados[i].getCedula.equals(cedula)){
                		empleado = empleados[i];
                		break;
            		}
        	}
        	return empleado;
    	}
	
	public void eliminarEmpleado(String cedula) throws NoExisteEmpleadoException{
        	if(!existeEmpleado(cedula))   {
            		throw new NoExisteEmpleadoException();
        	}
        	Empleado[] restantes = Arrays.copyOf(empleados, empleados.length - 1);
        	for(int i = 0, j = 0; i < empleados.length; i++){
            		if(empleados[i].getCedula != cedula){
                	restantes[j] = empleados[i];
                	j++;
            		}
        	}
        	empleados = restantes;
    	}
	
	public boolean existeReserva(String codigoReserva) throws NoExisteReserva {
		int i = 0;
		boolean b=false;
		while (i < reservas.length && !b) {
			if(reservas.getCodigo.equals(codigoReserva))
				return true;
			i++;
		}
		throw new NoExisteRerserva();
		
	}
// REVISAR
	public Reserva buscarReserva(String codigoReserva) throws NoSeEncuentraReserva, NoExisteReserva {
		int i = 0;
		if (existeReserva(codigoReserva)) {
			while (i < reservas.length && !codigoReserva.equals(reservas[i].getCodigoReserva())) {
				i++;
			}
			return reservas[i];
		} else {
			throw new noSeEncuentraReserva();
		}
	}
//.
	public void addReserva(String codigo, String cedulaCliente, String plan, Date fechaInicio, Date fechaFinal,
			String codigoEmpleado) throws YaExisteReserva, NoExisteReserva {

		if (existeReserva(codigo)) {
			throw new YaExisteReserva();
		} else {
			reservas=Arrays.copyOf(reservas, reservas.length+1);
			reservas[reservas.length-1]=new Reserva(cedulaCliente, codigoEmpleado, fechaInicio, fechaFinal, plan);
		}
	}
//
	public void eliminarReserva(String codigoReserva) throws noExisteReserva {
		Reserva aux;
		int i = 0;
		while (i < reservas.length && !codigoReserva.equalsIgnoreCase(reservas[i].getCodigoReserva())) {
			i++;
		}
		if (codigoReserva.equalsIgnoreCase(reservas[i].getCodigoReserva())) {
			aux = reservas[i];
			reservas[i] = reservas[reservas.length];
			reservas[reservas.length] = aux;
			System.arraycopy(reservas, 0, reservas, 0, reservas.length - 1);
		} else {
			throw new noExisteReserva();
		}
	}
//
	public void imprimirReserva(String direccion, String codigo) throws IOException, noExisteReserva, noSeEncuentraReserva {
		
		FileWriter fw = new FileWriter(direccion);
		PrintWriter pw = new PrintWriter(fw);
		
		if(existeReserva(codigo)==true) {
			pw.print(buscarReserva(codigo));
		}
		pw.close();
	}
}
//

  
