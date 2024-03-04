bits 32 ; assembling for the 32 bits architecture

; declare the EntryPoint (a label defining the very first instruction of the program)
global start        

; declare external functions needed by our program
extern exit               ; tell nasm that exit exists even if we won't be defining it
import exit msvcrt.dll    ; exit is a function that ends the calling process. It is defined in msvcrt.dll
                          ; msvcrt.dll contains exit, printf and all the other important C-runtime specific functions

; our data is declared here (the variables needed by our program)
segment data use32 class=data
    ; ...
    ;OHNE vorzeichen ubung 29
    ;(a+b)/(c-2)-d+2-x
    ;a,b,c=byte
    ;d=doubleword
    ;x=quadword
    a db 50
    b db 20
    c db 7
    d dd 3
    x dq 7
    

; our code starts here
segment code use32 class=code
    start:
        ;(a+b)/(c-2)-d+2-x
        mov al,[a] ; wir haben a=byte in al getan 
        add al,[b] ; wir addieren zu al den b: al=a+b                                                  al=50+20=70
        mov ah,0 ;vorzeichenlose Umwandlung von al in ax weil wir mussen durch ein byte teile          ax=70
        mov cl,[c] ;wir tuen in cl auf 8 bits den c
        sub cl,2 ; wir subtrahieren 2                                                                  cl=7-2=5
        div cl ;vorzeichenlose Division ax durch c-2=>(a+b)/(c-2)...word/byte                          ax=70/5=14
        mov dx,0 ;vorzeichenlose Umwandlung von ax in dx:ax, dx:ax=(a+b)/(c-2)                         dx:ax=14
        mov bx,word[d] 
        mov cx,word[d+2] ;cx:bx=d(doppelword)
        sub ax,bx ;man subtrahiert von ax den bx 
        sbb dx,cx ;man subtrahiert von dx den cx 
        ;dx:ax=(a+b)/(c-2)-d                                                                           dx:ax=14-3=11
        push dx
        push ax
        pop eax; eax=(a+b)/(c-2)-d                                                                     eax=11
        add eax,2 ;man addiert zu eax eine 2                                                           eax=11+2=13
        mov edx,0 ;edx:eax=(a+b)/(c-2)-d+2                                                             edx:eax=13
        sub eax,dword[x]                                                                            
        sbb edx,dword[x+4] ;edx:eax=(a+b)/(c-2)-d+2-x                                                  edx:eax=13-7=6
        ;x und der Inhalt des Carryflags werden von edx subtrahiert 
        ; exit(0)
        push    dword 0      ; push the parameter for exit onto the stack
        call    [exit]       ; call exit to terminate the program
