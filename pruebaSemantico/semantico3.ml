%----------------------------------
programa semantico3; 
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
	accion funcion2 (ref entero pam1);
	%----------------------------------
		principio
			escribir(1);
		fin

principio
	escribir(1);
	funcion2(j); %ERROR
fin

%-----------------------------------------------------------
principio
	mq 2*2	%ERROR
		escribir("mal");
	fmq
		
	a := 1;
	mq a<10
		escribir("bien");
		a := a+1;
	fmq
	
	funcion1(2, 3); %ERROR
	
	escribir("cadena1"<"cadena2"); %ERROR
fin