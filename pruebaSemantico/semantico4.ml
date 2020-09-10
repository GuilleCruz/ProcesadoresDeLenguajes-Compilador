%----------------------------------
programa semantico4; 
%----------------------------------
	entero a, b, c;
	booleano d, foo, bar;
	caracter e;

%----------------------------------
accion funcion1 (ref entero m; val entero j);
%----------------------------------
	entero a, b, c, a; %ERROR
	booleano d;
	caracter e;

principio
	si (b <> 5) OR (2*2) ent	%ERROR
		escribir(1);
	fsi
	
	c := e*b; %ERROR
fin

%----------------------------------
accion funcion2 (val entero m; ref entero j);
%----------------------------------
principio
	d := false;
	j := 40000; %ERROR
	escribir(entacar(1000)); %ERROR
	a := caraent("J");
	a := caraent(5); %ERROR
	a := noDeclarado * 5; %ERROR
fin

%-----------------------------------------------------------
principio
	d := a = b;
	c := 2*9-5/8-9*582-1;
	c := 2*9-5/0-9*582-1;	%ERROR
	c := (b-a*b-c)/(2+3-3-2); %ERROR
	c := (b-a*b-c)/(2+3-b-2);
	
	si d ent
		escribir("Son iguales");
	si_no
		escribir("a y b no son iguales. Valor a: ", a, ".Valor b: ", b);
	fsi
	
	escribir(25000*2); %ERROR
fin