%----------------------------------
programa semantico2; 
%----------------------------------
	entero a, b, c;
	booleano d;
	caracter e;

%----------------------------------
accion funcion1 (ref entero m; val entero j);
%----------------------------------
	entero a, b, c;
	booleano d;
	caracter e;
	
	%----------------------------------
	accion m;	%ERROR
	%----------------------------------
		principio
			escribir(1);
		fin

principio
	escribir(1);
fin

%-----------------------------------------------------------
principio
	leer(a, b);
	a := 2;
	b := 4;
	e := "K";
	d := true;
	
	funcion1(a, b);
	funcion1();	%ERROR
	funcion1(d, b);	%ERROR
	
	funcion2(a, b, c, d); %ERROR
	
	funcion1(a);	%ERROR
fin