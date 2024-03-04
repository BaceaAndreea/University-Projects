bits 32 ; assembling for the 32 bits architecture

; declare the EntryPoint (a label defining the very first instruction of the program)
global start        

extern exit,printf ,fopen,fclose,fread               ;
import exit msvcrt.dll 
import printf msvcrt.dll 
import fopen msvcrt.dll 
import fclose msvcrt.dll 
import fread msvcrt.dll 
segment data use32 class=data
    ; ...
    file_name db "exercitiu_1.txt",0
    acces_mode db "r",0
    file_descriptor dd -1
    len equ 100
    text times len db 0
    format db "textul contine %d numere impare",0
    ct dd 0
    
    

; our code starts here
segment code use32 class=code
    start:
        ; ...
        push dword acces_mode
        push dword file_name
        call [fopen]
        add esp,4*2
        
        mov [file_descriptor],eax
        cmp eax,0
        je final
        
        push dword [file_descriptor]
        push dword len 
        push dword 1
        push dword text
        call [fread]
        add esp,4*4
        
        mov ecx, eax ;lungimea sirului
        mov esi, text
        mov edx, 0
        
        Parcurgere:
            lodsb
            cmp al, '0' ;<
            jb par
            cmp al, '9' ;>
            ja par
            sub al, '0'
            test al, 1 ;the ZF is set to 1 if the result of an arithmetic 
            jz par  ;or logical operation (like test) are 0
            add dl, 1
            par:
        loop Parcurgere
        
        mov [ct],edx
        
        push dword [ct]
        push dword format
        call [printf]
        add esp,4*2
        
        push dword [file_descriptor]
        call [fclose]
        add esp,4
        final:
        
        
        
    
        ; exit(0)
        push    dword 0      ; push the parameter for exit onto the stack
        call    [exit]       ; call exit to terminate the program
