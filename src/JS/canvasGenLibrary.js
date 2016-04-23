!This is the code used by the compiler to generate a javascript canvas code file
! !  indicates a comment
! #  pound sign indicates a code block
! $$ enclosing dollar signs indicates a variable that may need to be replaced to avoid duplicated variables
! "" empty parentheses indicates a value that needs to be set by compiler
! empty lines will be skipped

#onload
window.onload = function() {
	var canvasId     = "";
	var canvasName   = "";
	var canvasWidth  = "";
	var canvasHeight = "";
	var canvas = document.createElement('canvas');
	canvas.id  = canvasId;
	canvas.setAttribute("height",canvasWidth);
	canvas.setAttribute("width",canvasHeight);
	document.body.appendChild(canvas);
	var context = canvas.getContext('2d');

!begin free draw code
#moveContext
	var $x$ = "";
	var $y$ = "";
	context.beginPath();
	context.MoveTo($x$,$y$);
#lineTo
	var $px$ = 105;
	var $py$ = 105;
	context.lineTo($px$,$py$);
#stroke
	var $freeDrawStroke$ = "";
	var $freeDrawColor$  = "";
	context.lineWidth = $freeDrawStroke$;
    context.strokeStyle = $freeDrawColor$;
	context.stroke();	
!end free draw

#circle

#rectangle

!no code blocks should come after closeOnload
#closeOnload
}
