%----------------------------------
programa sintactico2; 
%----------------------------------
%% Declaraci�n acciones err�nea %%
%% Falta ; %%
%% Leer no puede recibir una constante como parametro %%

	entero a, b, c, d;
	booleano adios, hola;
	caracter y, f;

%----------------------------------
accion fallo (ref entero m; caracter u);
%----------------------------------
principio
	escribir(1);
	escribir(1);
	escribir(1);
	escribir(1);
fin

principio
	b := 2;
	b := 3
	b := 4;
	leer(2);
	b := 5;
fin