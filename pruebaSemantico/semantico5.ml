%----------------------------------
programa semantico5;
%----------------------------------
	entero var1,var2,var3;
	caracter y;

%----------------------------------
principio
	
	escribir("var1: ");
	leer(var1);
	escribir(entacar(10), entacar(13));
	escribir("var1:", entacar(var1), entacar(10), entacar(13));

	escribir("var2 var3:");
	leer(var2,var3);
	escribir(entacar(10), entacar(13));
	escribir("var2:", entacar(var2), "  var3:", entacar(var3),entacar(10), entacar(13));

	y := entacar(var1);
	escribir("y=var1: ", y, entacar(10), entacar(13));

	si (y = entacar(var1)) ent
		escribir("correcto", entacar(10), entacar(13));
	fsi

fin