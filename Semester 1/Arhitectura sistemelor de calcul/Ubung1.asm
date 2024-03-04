bits 32 ; assembling for the 32 bits architecture

; declare the EntryPoint (a label defining the very first instruction of the program)
global start        

; declare external functions needed by our program
extern exit,scanf,printf              ; tell nasm that exit exists even if we won't be defining it
import exit msvcrt.dll   
import scanf msvcrt.dll
import printf msvcrt.dll 
; exit is a function that ends the calling process. It is defined in msvcrt.dll
                          ; msvcrt.dll contains exit, printf and all the other important C-runtime specific functions

; our data is declared here (the variables needed by our program)
segment data use32 class=data
    ; ...
    A dd 0
    B dd 0
    format_introducere1 db "a=",0
    format_introducere2 db "b=",0
    format1 db "%d",0
    result db "%d",0
    
; our code starts here
segment code use32 class=code
    start:
        ; ...
        push dword format_introducere1 ;es wird auf den Bildschirm dargestellt 'a='
        call [printf]
        add esp,4
        
        push dword A
        push dword format1 ;wir lesen den A als eine dezimale zahl 
        call [scanf]
        add esp,4*2 ;dam clear la stack 
        
        push dword format_introducere2 ;es wird auf den Bildschirm dargestellt 'b='
        call [printf]
        add esp,4
        
        push dword B
        push dword format1 ;wir lesen den B als eine dezimale zahl 
        call [scanf]
        add esp, 4*2 ;dam clear la stack
        
        mov eax,[A] ;in eax wir haben den A
        mov edx,0 ;edx:eax=A
        mov ecx,[B] ;in ecx existiert den B
        div ecx ;edx:eax/ecx=eax
        
        push dword eax ;das Ergeniss wird in eax erschienen 
        push dword result ;es wird in daie variabe result getan
        call [printf] ;es wird auf den Bildschirm gezeigt 
        add esp,4*2
       
        ; exit(0)
        push    dword 0      ; push the parameter for exit onto the stack
        call    [exit]       ; call exit to terminate the program
