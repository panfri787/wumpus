package wumpus;

public class DialoguesManager {
	public final static String AT_HOLE = "Has caido por un pozo.";
	public final static String AT_EXIT_WITH_GOLD ="¡Enhorabuena! Encontraste el oro y saliste de la mazmorra sano y salvo.";
	public final static String AT_EXIT_WITHOUT_GOLD = "Aun no has encontrado el oro, salir ahora no es muy productivo.";
	public final static String NOT_AT_EXIT = "No estas en la salida ¡No puedes salir!";
	public final static String AT_EXIT = "Estas en la salida de la mazmorra";
	public final static String WITH_WUMPUS_ALIVE = "¡Te has encontrado con el Wumpus! Te asesta un golpe que te deja inconsciente y te empieza a devorar.";
	public final static String WITH_WUMPUS_DEAD = "¡Te has encontrado con el Wumpus! Por fortuna, le abatiste y ya no es una amenaza.";
	public final static String WUMPUS_NEAR = "¿Que es ese repugnante hedor? El Wumpus debe andar cerca, lleva cuidado.";
	public final static String HOLE_NEAR = "De repente, percibes una suave brisa. Debes de estar cerca de un pozo.";
	public final static String WALL_HIT = "Te has golpeado con un muro de la mazmorra.";
	public final static String AT_GOLD = "¿Que es ese brillo? ¡Si! Parece que has encontrado el oro.";
	public final static String WUMPUS_DEAD = "Se escucha un grito terrorifico de dolor. Debes de haber alcanzado con la flecha al Wumpus y le has matado.";
	public final static String GAME_OVER = "Has muerto, vuelve a intentarlo.";
	public final static String LOOKING_AT = "Estas orientado hacia el ";
	public final static String NORTH = "norte.";
	public final static String SOUTH = "sur.";
	public final static String EAST = "este.";
	public final static String WEST = "oeste.";
	public final static String COMMANDS_HELP = "Escribe avanzar para avanzar en la mazmorra, izquierda o derecha para girarte hacia ese lado, arco para lanzar una flecha"
			+ " o salir para salir de la mazmorra. Escribe ayuda en cualquier momento para mostrar este mensaje.";
	public final static String GO_COMMAND = "avanzar";
	public final static String LEFT_COMMAND = "izquierda";
	public final static String RIGHT_COMMAND = "derecha";
	public final static String EXIT_COMMAND = "salir";
	public final static String BOW_COMMAND = "arco";
	public final static String HELP_COMMAND = "ayuda";
	public final static String UNKNOWN_COMMAND = "El comando introducido no es correcto.";
	public final static String WELCOME = "Bienvenido a la mazmorra del Wumpus. Tu objetivo es encontrar el oro oculto y volver a salida. Ten cuidado porque existen pozos"
			+ " en los que podrias caerte y el Wumpus que siempre esta al azecho de los incautos.";
	public final static String COMMAND_CURSOR = ">> ";
	public final static String HOLES_NUMBER_ASK = "Introduce el numero de pozos que deseas (Numero positivo): ";
	public final static String ARROWS_NUMBER_ASK = "Introduce el numero de flechas que deseas (Numero positivo): ";
	public final static String MAP_SIZE_ASK = "Introduce el tamaño del mapa (Numero positivo mayor que 1): ";
	public final static String IMPOSSIBLE_MAP = "Es imposible generar un mapa con las caracteristicas solicitadas, pon menos pozos o haz el mapa mas grande.";
	
}
