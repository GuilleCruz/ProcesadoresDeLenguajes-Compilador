%----------------------------------
programa sintactico5; 
%----------------------------------
%% Expresi�n no v�lida en el mq (no hay expresion) %%
%% Expresi�n incorrecta en la asignaci�n %%
%% No se permite declarar una accion con nombre programa (ni ninguna palabra reservada) %%

	entero a, b, c, d;
	booleano adios, hola;
	caracter y, f;

%----------------------------------
accion fallo (ref entero m; ref caracter u);
%----------------------------------
	entero foo;
principio
	foo := 2;
	mq 
		escribir("dentro");
	fmq 
	
	foo := 2+;
	foo := 5;
fin

%----------------------------------
accion programa;
%----------------------------------
principio
	escribir(entacar(13), entacar(10));
fin

principio
	b := 5;
fin