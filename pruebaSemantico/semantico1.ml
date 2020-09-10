%----------------------------------
programa semantico1; 
%----------------------------------
	entero a, b, c;
	booleano d;

%----------------------------------
accion funcion1 (ref entero m; val entero j);
%----------------------------------

principio
	escribir(1);
fin

%-----------------------------------------------------------
principio
	leer(d, funcion1); %ERROR
	a := 2;
	b := 4;
	funcion1 := "K"; %ERROR
	c := true; %ERROR
fin